package com.bitworkshop.litebookscholar.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bitworkshop.litebookscholar.R;
import com.bitworkshop.litebookscholar.adapter.BookInfoFragmnetAdapter;
import com.bitworkshop.litebookscholar.asynctask.DownloadIImageFromHttp;
import com.bitworkshop.litebookscholar.entity.BookInfo;
import com.bitworkshop.litebookscholar.entity.DoubanBookInfo;
import com.bitworkshop.litebookscholar.presenter.BookInfoPresenter;
import com.bitworkshop.litebookscholar.ui.fragment.BookHoldingInfoFragment;
import com.bitworkshop.litebookscholar.ui.fragment.BookSummaryInfoFragment;
import com.bitworkshop.litebookscholar.ui.view.IBookInfoView;
import com.bitworkshop.litebookscholar.util.BlurBitmap;
import com.bitworkshop.litebookscholar.util.MyToastUtils;
import com.bitworkshop.litebookscholar.util.Utils;
import com.bumptech.glide.Glide;
import com.getbase.floatingactionbutton.FloatingActionButton;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BookInfoActivity extends BaseActivity implements IBookInfoView {
    private final static String BOOK_ID = "bookid";
    private final static String ISBN = "isbn";
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.table_layout)
    TabLayout tableLayout;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.image_book)
    ImageView imageBook;
    @BindView(R.id.tv_book_title)
    TextView tvBookTitle;
    @BindView(R.id.tv_book_author)
    TextView tvBookAuthor;
    @BindView(R.id.tv_book_publish)
    TextView tvBookPublish;
    @BindView(R.id.rating_bar_of_book)
    RatingBar ratingBarOfBook;
    @BindView(R.id.tv_book_average)
    TextView tvBookAverage;
    @BindView(R.id.relative_book_base_info)
    RelativeLayout relativeBookBaseInfo;
    @BindView(R.id.float_add_to_bookshelf)
    FloatingActionButton floatAddToBookshelf;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    @BindView(R.id.activity_book_shelf)
    RelativeLayout activityBookShelf;
    @BindView(R.id.iamge_book_large)
    ImageView iamgeBookLarge;

    private BookInfoPresenter bookInfoPresenter;
    private BookInfo bookInfo;

    private String keyWors;
    private BookInfoFragmnetAdapter adapter;

    public static void startActivity(Context context, String bookId, String isbn) {
        Intent intent = new Intent(context, BookInfoActivity.class);
        intent.putExtra(BOOK_ID, bookId);
        intent.putExtra(ISBN, isbn);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_info);
        ButterKnife.bind(this);
        setupToolbar(toolbar, "图书详情", true);
        setupviews();
        bookInfoPresenter = new BookInfoPresenter(this);
        loadDatas();

    }

    /**
     * 根据不同的值获取不同的结果
     */
    private void loadDatas() {
        Intent intent = getIntent();
        if (intent.getStringExtra(BOOK_ID) != null) {
            keyWors = intent.getStringExtra(BOOK_ID);
            if (Utils.isOnline(this)) {
                bookInfoPresenter.getBookInfoFromLib(keyWors);
            } else {
                MyToastUtils.showToast(this, "哎呀,网络有问题");
            }
        } else {
            keyWors = intent.getStringExtra(ISBN);
            bookInfoPresenter.getBookInfoFromDatabase(keyWors);
        }
    }

    private void setupviews() {
        swipeRefresh.setColorSchemeColors(Utils.getRandomColors());
        swipeRefresh.setEnabled(false);
        setupTables();
    }

    private void setupTables() {
        adapter = new BookInfoFragmnetAdapter(getSupportFragmentManager(), this);
        viewpager.setAdapter(adapter);
        tableLayout.setupWithViewPager(viewpager);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.float_add_to_bookshelf)
    public void onClick() {
        if (bookInfo != null) {
            bookInfoPresenter.addBookToShelf(bookInfo);
            floatAddToBookshelf.setIconDrawable(getDrawable(R.drawable.ic_favorite_add));
        }
    }

    @Override
    public void setBookInfo(BookInfo bookInfo) {
        this.bookInfo = bookInfo;
        tvBookAuthor.setText(bookInfo.getAuthor());
        tvBookPublish.setText(bookInfo.getPublish());
        tvBookTitle.setText(bookInfo.getBookTitle());
        bookInfoPresenter.getBookInfoRromDouban(bookInfo.getIsbn());
        BookHoldingInfoFragment fragment = (BookHoldingInfoFragment) adapter.getRegisteredFragment(0);
        fragment.setHodingInfo(bookInfo.getHoldingInfos());

    }

    @Override
    public void setBookInfoFromDouban(DoubanBookInfo doubanBookInfo) {
        BookSummaryInfoFragment fragment = (BookSummaryInfoFragment) adapter.getRegisteredFragment(1);
        if (doubanBookInfo != null) {
            bookInfo.setSmalImge(doubanBookInfo.getImages().getSmall());
            bookInfo.setMidImge(doubanBookInfo.getImages().getMedium());
            bookInfo.setLargeImge(doubanBookInfo.getImages().getLarge());
            double average = Double.parseDouble(doubanBookInfo.getRating().getAverage());
            if (average > 0) {
                bookInfo.setAverage(average);
                ratingBarOfBook.setRating((float) average);
                ratingBarOfBook.setVisibility(View.VISIBLE);
            }
            ExecutorService exec = Executors.newSingleThreadExecutor();
            try {
                Bitmap bitmap = exec.submit(new DownloadIImageFromHttp(doubanBookInfo.getImages().getLarge())).get();
                iamgeBookLarge.setImageBitmap(BlurBitmap.blur(this, bitmap));
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            } finally {
                exec.shutdown();
            }
            tvBookAverage.setText(doubanBookInfo.getRating().getAverage());
            Glide.with(this)
                    .load(doubanBookInfo.getImages().getMedium())
                    .placeholder(Utils.getRandomColors())
                    .into(imageBook);
            if (doubanBookInfo.getSummary() != null) {
                fragment.setSummary(doubanBookInfo.getSummary());
            } else {
                fragment.setSummary("尚无简介");
            }
        } else {
            imageBook.setImageResource(Utils.getRandomColors());
            tvBookAverage.setVisibility(View.GONE);
            iamgeBookLarge.setImageResource(Utils.getRandomColors());
            fragment.setSummary("尚无简介");
        }


    }

    @Override
    protected void onStart() {
        super.onStart();
        Glide.with(this).pauseRequestsRecursive();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Glide.with(this).pauseRequests();
    }

    @Override
    public void showLoading() {
        swipeRefresh.setEnabled(true);
        swipeRefresh.setRefreshing(true);
    }

    @Override
    public void hideLoading() {
        swipeRefresh.setEnabled(false);
        swipeRefresh.setRefreshing(false);
    }

    @Override
    public void showError(String msg) {
        MyToastUtils.showToast(this, msg);
    }

}

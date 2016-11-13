package com.bitworkshop.litebookscholar.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
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

import com.bitworkshop.litebookscholar.App;
import com.bitworkshop.litebookscholar.R;
import com.bitworkshop.litebookscholar.adapter.BookInfoFragmnetAdapter;
import com.bitworkshop.litebookscholar.asynctask.DownloadIImageFromHttp;
import com.bitworkshop.litebookscholar.entity.BookHoldingInfo;
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
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BookInfoActivity extends BaseActivity implements IBookInfoView {
    private final static String BOOK_ID = "bookid";
    @BindView(R.id.iamge_book_large)
    ImageView iamgeBookLarge;
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
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.collaps_toolbar_layout)
    CollapsingToolbarLayout collapsToolbarLayout;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.table_layout)
    TabLayout tableLayout;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.float_add_to_bookshelf)
    FloatingActionButton floatAddToBookshelf;


    private BookInfoPresenter bookInfoPresenter;
    private BookInfo bookInfo;

    private String keyWors;
    private BookInfoFragmnetAdapter adapter;

    private static boolean isSave = false;
    private CollapsingToolbarLayoutState state;

    private enum CollapsingToolbarLayoutState {
        EXPANDED,
        COLLAPSED,
        INTERNEDIATE
    }

    public static void startActivity(Context context, String bookId) {
        Intent intent = new Intent(context, BookInfoActivity.class);
        intent.putExtra(BOOK_ID, bookId);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_info2);
        ButterKnife.bind(this);
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
            //首先从数据库拿
            // 没拿到从网上拿
            if (bookInfoPresenter.getBookInfoFromDatabase(keyWors) != null) {
                isSave = true;
                setBookInfoFromDataBase(bookInfoPresenter.getBookInfoFromDatabase(keyWors));
            } else {
                if (Utils.isOnline(this)) {
                    bookInfoPresenter.getBookInfoFromLib(keyWors);
                } else {
                    MyToastUtils.showToast(this, "哎呀,网络有问题");
                }
            }
        }
    }

    /**
     * 从数据库读取简介
     *
     * @param bookInfoFromDatabase
     */
    private void setBookInfoFromDataBase(final BookInfo bookInfoFromDatabase) {
        bookInfo = bookInfoFromDatabase;
        for (BookHoldingInfo bookHoldingInfo : bookInfoFromDatabase.getBookHoldingInfos(bookInfoFromDatabase.getId())) {
            System.out.println(bookHoldingInfo);
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                viewpager.post(new Runnable() {
                    @Override
                    public void run() {
                        BookHoldingInfoFragment fragment = (BookHoldingInfoFragment) adapter.getRegisteredFragment(0);
                        fragment.setHodingInfo(bookInfo.getBookHoldingInfos(bookInfo.getId()));
                        BookSummaryInfoFragment booksummaryinfofragment = (BookSummaryInfoFragment) adapter.getRegisteredFragment(1);
                        if (bookInfo.getSummary() != null) {
                            booksummaryinfofragment.setSummary(bookInfo.getSummary());
                        } else {
                            booksummaryinfofragment.setSummary("简介飞走了");
                        }
                    }
                });
            }
        }).start();
        isSave = true;
        floatAddToBookshelf.setIconDrawable(getDrawable(R.drawable.ic_favorite_add));
        tvBookAuthor.setText(bookInfoFromDatabase.getAuthor());
        tvBookPublish.setText(bookInfoFromDatabase.getPublish());
        tvBookTitle.setText(bookInfoFromDatabase.getBookTitle());
        double average = bookInfoFromDatabase.getAverage();
        if (average > 0) {
            ratingBarOfBook.setRating((float) average);
            ratingBarOfBook.setVisibility(View.VISIBLE);
            tvBookAverage.setText(String.valueOf(average));
            tvBookAverage.setVisibility(View.VISIBLE);
        }
        if (bookInfo.getLargeImge() != null) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    ExecutorService executorService = Executors.newSingleThreadExecutor();
                    try {
                        Bitmap bitmap = executorService.submit(new DownloadIImageFromHttp(bookInfo.getLargeImge())).get();
                        bookInfo.setLargeBitmap(BlurBitmap.blur(App.getContext(), bitmap));
                    } catch (InterruptedException | ExecutionException e) {
                        e.printStackTrace();
                    } finally {
                        executorService.shutdown();
                    }
                    iamgeBookLarge.post(new Runnable() {
                        @Override
                        public void run() {
                            iamgeBookLarge.setImageBitmap(bookInfo.getLargeBitmap());
                            Glide.with(App.getContext())
                                    .load(bookInfo.getMidImge())
                                    .placeholder(Utils.getRandomColors())
                                    .into(imageBook);
                        }
                    });
                }
            }).start();
        } else {
            iamgeBookLarge.setImageResource(Utils.getRandomColors());
            imageBook.setImageResource(Utils.getRandomColors());
        }

    }

    private void setupviews() {
        collapsToolbarLayout.setTitleEnabled(false);
        setupToolbar(toolbar, "图书详情", true);
        appbar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (verticalOffset == 0) {
                    if (state != CollapsingToolbarLayoutState.EXPANDED) {
                        state = CollapsingToolbarLayoutState.EXPANDED;
                        toolbar.setTitle("图书详情");
                        toolbar.setTitleTextColor(getColor(android.R.color.white));
                        toolbar.getNavigationIcon().setColorFilter(getColor(android.R.color.white), PorterDuff.Mode.SRC_ATOP);
                    }
                } else if (Math.abs(verticalOffset) >= appBarLayout.getTotalScrollRange()) {
                    if (state != CollapsingToolbarLayoutState.COLLAPSED) {
                        toolbar.setTitle(bookInfo.getBookTitle());
                        state = CollapsingToolbarLayoutState.COLLAPSED;
                        toolbar.setTitleTextAppearance(BookInfoActivity.this, R.style.Toolbar_TitleText);
                        toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.colorAccent), PorterDuff.Mode.SRC_ATOP);
                    }
                }
            }
        });
        setupTables();
    }

    private void setupTables() {
        adapter = new BookInfoFragmnetAdapter(getSupportFragmentManager(), this);
        viewpager.setAdapter(adapter);
        tableLayout.setupWithViewPager(viewpager);
        if (isSave) {
            floatAddToBookshelf.setIconDrawable(getDrawable(R.drawable.ic_favorite_add));
        }

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
        if (!isSave) {
            if (bookInfo != null) {
                bookInfo.setBookInfoId(keyWors);
                isSave = bookInfoPresenter.addBookToShelf(bookInfo, getUserAccount());
                System.out.println("save" + bookInfo.toString());
                if (isSave) {
                    floatAddToBookshelf.setIconDrawable(getDrawable(R.drawable.ic_favorite_add));
                }
            }

        } else {
            isSave = false;
            System.out.println("delete");
            bookInfoPresenter.deleteBookInfo(keyWors);
            floatAddToBookshelf.setIconDrawable(getDrawable(R.drawable.ic_favorite_not_add));
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
    public void setBookInfoFromDouban(final DoubanBookInfo doubanBookInfo) {
        BookSummaryInfoFragment fragment = (BookSummaryInfoFragment) adapter.getRegisteredFragment(1);
        if (doubanBookInfo != null) {
            bookInfo.setSmalImge(doubanBookInfo.getImages().getSmall());
            bookInfo.setMidImge(doubanBookInfo.getImages().getMedium());
            bookInfo.setLargeImge(doubanBookInfo.getImages().getLarge());
            bookInfo.setSummary(doubanBookInfo.getSummary());
            double average = Double.parseDouble(doubanBookInfo.getRating().getAverage());
            if (average > 0) {
                bookInfo.setAverage(average);
                ratingBarOfBook.setRating((float) average);
                ratingBarOfBook.setVisibility(View.VISIBLE);
                tvBookAverage.setText(doubanBookInfo.getRating().getAverage());
                tvBookAverage.setVisibility(View.VISIBLE);
            }
            new Thread(new Runnable() {
                @Override
                public void run() {
                    final ExecutorService exec = Executors.newSingleThreadExecutor();
                    Bitmap bitmap = null;
                    try {
                        bitmap = exec.submit(new DownloadIImageFromHttp(doubanBookInfo.getImages().getLarge())).get();
                        bookInfo.setLargeBitmap(bitmap);
                    } catch (InterruptedException | ExecutionException e) {
                        e.printStackTrace();
                    }
                    iamgeBookLarge.post(new Runnable() {
                                            @Override
                                            public void run() {
                                                iamgeBookLarge.setImageBitmap(BlurBitmap.blur(BookInfoActivity.this, bookInfo.getLargeBitmap()));
                                            }
                                        }
                    );
                }
            }).start();
            Glide.with(this)
                    .load(doubanBookInfo.getImages().getMedium())
                    .placeholder(Utils.getRandomColors())
                    .into(imageBook);
            if (doubanBookInfo.getSummary() != null) {
                fragment.setSummary(doubanBookInfo.getSummary());
            } else {
                fragment.setSummary("尚无简介");
            }
        } else

        {
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
        isSave = false;
    }

    @Override
    public void showLoading() {
    }

    @Override
    public void hideLoading() {
    }

    @Override
    public void showError(String msg) {
        MyToastUtils.showToast(this, msg);
    }

}

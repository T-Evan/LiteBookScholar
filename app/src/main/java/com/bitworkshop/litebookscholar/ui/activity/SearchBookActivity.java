package com.bitworkshop.litebookscholar.ui.activity;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bitworkshop.litebookscholar.R;
import com.bitworkshop.litebookscholar.adapter.CustomLoadingListItemCreator;
import com.bitworkshop.litebookscholar.adapter.SearchResultAdapter;
import com.bitworkshop.litebookscholar.entity.LibraryQueryListItm;
import com.bitworkshop.litebookscholar.presenter.SearchBooksPresenter;
import com.bitworkshop.litebookscholar.ui.view.ISearchView;
import com.bitworkshop.litebookscholar.util.MyToastUtils;
import com.bitworkshop.litebookscholar.util.Utils;
import com.bumptech.glide.Glide;
import com.paginate.Paginate;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.http.PATCH;

/**
 * 搜索图书类
 */
public class SearchBookActivity extends BaseActivity implements ISearchView, Paginate.Callbacks, SearchResultAdapter.OnItemClickListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recycler_search_result)
    RecyclerView recyclerSearchResult;
    @BindView(R.id.search_view)
    SearchView searchView;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    @BindView(R.id.relative_content)
    RelativeLayout relativeContent;
    @BindView(R.id.activity_search_book)
    RelativeLayout activitySearchBook;
    private SearchBooksPresenter presenter;
    private Paginate paginate;
    private boolean loadMoer = false;
    private boolean hasMoreData = true;
    private List<LibraryQueryListItm> itms = new ArrayList<>();
    private int pages = 1;
    private String query;
    private int pageNums = 1;
    private LinearLayoutManager linearLayoutManager;
    private SearchResultAdapter adapter;

    public static void startActivity(Context context, String isbn) {
        Intent i = new Intent(context, SearchBookActivity.class);
        i.putExtra("isbn", isbn);
        context.startActivity(i);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_book);
        ButterKnife.bind(this);
        setupToolbar(toolbar, "", true);
        initViewS();
        handIntent(getIntent());
    }

    @Override
    public void onNewIntent(Intent newIntent) {
        setIntent(newIntent);
        handIntent(newIntent);
    }

    private void handIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            query = intent.getStringExtra(SearchManager.QUERY);
            System.out.println("result" + query);

            doMysearch("title", query);
        }
        if (intent.getStringExtra("isbn") != null) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
            doMysearch("isbn", intent.getStringExtra("isbn"));
        }
    }

    /**
     * 执行查询
     *
     * @param query
     */
    private void doMysearch(String type, String query) {
        resetSearch();
        configLoadMore();
        recyclerSearchResult.setVisibility(View.GONE);
        if (Utils.isOnline(this)) {
            presenter.searchBooks(type, query, 1);
        } else {
            MyToastUtils.showToast(this, "哎呀,网络有问题!");
        }
    }

    /**
     * 如果键入了新的关键字
     * 重置搜索
     */
    private void resetSearch() {
        if (itms.size() > 0) {
            pages = 1;
            pageNums = 1;
            itms.clear();
            adapter.notifyDataSetChanged();
        }
    }

    /**
     * 配置加载更多
     */
    private void configLoadMore() {
        if (paginate != null) {
            paginate.unbind();
            paginate = null;
        }
        paginate = Paginate.with(recyclerSearchResult, this)
                .setLoadingTriggerThreshold(2)
                .addLoadingListItem(true)
                .setLoadingListItemCreator(new CustomLoadingListItemCreator())
                .build();
    }

    private void initViewS() {
        presenter = new SearchBooksPresenter(this);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerSearchResult.setLayoutManager(linearLayoutManager);
        adapter = new SearchResultAdapter(this, itms);
        recyclerSearchResult.setAdapter(adapter);
        adapter.setOnItemClickListner(this);
        setupSearchView();
    }

    private void setupSearchView() {
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(false);
        searchView.setIconified(false);
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void setBookList(List<LibraryQueryListItm> listItms) {
        if (listItms.size() != 0) {
            setVisibility(View.VISIBLE, View.GONE);
            int curSize = adapter.getItemCount();
            itms.addAll(listItms);
            adapter.notifyItemRangeInserted(curSize, listItms.size());
        } else {
            setVisibility(View.GONE, View.VISIBLE);
        }
    }

    private void setVisibility(int visible, int gone) {
        recyclerSearchResult.setVisibility(visible);
        relativeContent.setVisibility(gone);
    }

    @Override
    public void showFialed(String msg) {
        MyToastUtils.showToast(this, msg);
    }

    @Override
    public void setPages(int pages) {
        pageNums = pages;
        System.out.println("页数" + pageNums);
    }

    @Override
    public void addMore(List<LibraryQueryListItm> libraryQueryListItms) {
        int curSize = adapter.getItemCount();
        itms.addAll(libraryQueryListItms);
        adapter.notifyItemRangeInserted(curSize, libraryQueryListItms.size());
        loadMoer = false;
    }

    @Override
    public String getQueryTitle() {
        return null;
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.cancel();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.cancel();
    }


    @Override
    public void onLoadMore() {
        loadMoer = true;
        presenter.addMore(query, pages++);
    }

    @Override
    public boolean isLoading() {
        return loadMoer;
    }

    @Override
    public boolean hasLoadedAllItems() {
        return pageNums == pages;
    }

    @Override
    public void onItemClick(View itemview, int position) {
        BookInfoActivity.startActivity(this, itms.get(position).getBookInfoId());
        searchView.setFocusable(false);
    }
}

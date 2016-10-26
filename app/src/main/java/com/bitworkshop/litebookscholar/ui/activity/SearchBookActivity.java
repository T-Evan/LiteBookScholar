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
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.bitworkshop.litebookscholar.R;
import com.bitworkshop.litebookscholar.adapter.SearchResultAdapter;
import com.bitworkshop.litebookscholar.entity.LibraryQueryListItm;
import com.bitworkshop.litebookscholar.presenter.SearchBooksPresenter;
import com.bitworkshop.litebookscholar.ui.view.ISearchView;
import com.bitworkshop.litebookscholar.util.MyToastUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchBookActivity extends AppCompatActivity implements ISearchView {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_book);
        ButterKnife.bind(this);
        initToolBar();
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
            String query = intent.getStringExtra(SearchManager.QUERY);
            System.out.println("result" + query);
            presenter.searchBooks(query, 1);
        }
    }

    private void initViewS() {
        presenter = new SearchBooksPresenter(this);
        recyclerSearchResult.setLayoutManager(new LinearLayoutManager(this));
        setupSearchView();
    }

    private void setupSearchView() {
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(false);
        searchView.setIconified(false);
    }


    /**
     * 初始化toolbar
     */
    private void initToolBar() {
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
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
            SearchResultAdapter adapter = new SearchResultAdapter(this, listItms);
            recyclerSearchResult.setAdapter(adapter);
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


}

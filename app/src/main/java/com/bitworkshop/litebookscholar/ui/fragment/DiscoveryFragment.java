package com.bitworkshop.litebookscholar.ui.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.graphics.ColorUtils;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bitworkshop.litebookscholar.R;
import com.bitworkshop.litebookscholar.adapter.BorrowBookListAdapter;
import com.bitworkshop.litebookscholar.adapter.OneAdapter;
import com.bitworkshop.litebookscholar.entity.BookInfo;
import com.bitworkshop.litebookscholar.entity.One;
import com.bitworkshop.litebookscholar.presenter.DiscoveryPresenter;
import com.bitworkshop.litebookscholar.ui.activity.BookInfoActivity;
import com.bitworkshop.litebookscholar.ui.activity.ScannerActivity;
import com.bitworkshop.litebookscholar.ui.activity.SearchBookActivity;
import com.bitworkshop.litebookscholar.ui.view.IDiscoverView;
import com.bitworkshop.litebookscholar.util.MyToastUtils;
import com.bitworkshop.litebookscholar.util.Utils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 发现
 * Created by AidChow on 2016/10/16.
 */

public class DiscoveryFragment extends BaseFragment implements Toolbar.OnMenuItemClickListener, IDiscoverView, SwipeRefreshLayout.OnRefreshListener, BorrowBookListAdapter.OnItemClickListener {
    @BindView(R.id.tv_toolbar_title)
    TextView tvToolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recycler_vol)
    RecyclerView recyclerVol;
    @BindView(R.id.cardview_no_borrow_book_layout)
    CardView cardviewNoBorrowBookLayout;
    @BindView(R.id.recycler_to_borrow_list)
    RecyclerView recyclerToBorrowList;
    @BindView(R.id.relative_to_borrow_book_list_root)
    RelativeLayout relativeToBorrowBookListLayout;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    @BindView(R.id.tv_clear_book_list)
    TextView tvClearBookList;
    @BindView(R.id.relative_add_continue_layout)
    RelativeLayout relativeAddContinueLayout;
    private DiscoveryPresenter presenter;
    private OneAdapter adapter;
    private List<One.DataBean> oDataBeen = new ArrayList<>();

    private BorrowBookListAdapter borrowBookListAdapter;

    private List<BookInfo> bookInfos = new ArrayList<>();

    public static DiscoveryFragment getInstance() {
        return new DiscoveryFragment();
    }

    public DiscoveryFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_discovery, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews();
        presenter = new DiscoveryPresenter(this);
        initDates();

        loaddate();


    }

    private void loaddate() {
        if (Utils.isOnline(getActivity())) {
            swipeRefresh.post(new Runnable() {
                @Override
                public void run() {
                    swipeRefresh.setRefreshing(true);
                    presenter.getOne();
                }
            });
        } else {
            MyToastUtils.showToast(getActivity(), "请检查网络设置");
        }
    }

    private void initDates() {
        adapter = new OneAdapter(getContext(), oDataBeen);
        recyclerVol.setAdapter(adapter);
        borrowBookListAdapter = new BorrowBookListAdapter(getContext(), bookInfos);
        recyclerToBorrowList.setAdapter(borrowBookListAdapter);
        borrowBookListAdapter.setOnItemClickListner(this);
    }

    private void initViews() {
        initToolbarCustemerTitle(tvToolbarTitle);
        toolbar.inflateMenu(R.menu.dircovery_menu);
        toolbar.setOnMenuItemClickListener(this);
        recyclerVol.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        recyclerToBorrowList.setLayoutManager(new LinearLayoutManager(getActivity()));
        swipeRefresh.setColorSchemeColors(Utils.getRandomColors());
        swipeRefresh.setDistanceToTriggerSync(300);
        swipeRefresh.setOnRefreshListener(this);
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_scan:
                startActivity(new Intent(getActivity(), ScannerActivity.class));
                return true;
            case R.id.menu_search:
                startActivity(new Intent(getActivity(), SearchBookActivity.class));
                return true;
            default:
                return false;
        }
    }

    @OnClick({R.id.cardview_no_borrow_book_layout, R.id.tv_clear_book_list, R.id.relative_add_continue_layout})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cardview_no_borrow_book_layout:
                startActivity(new Intent(getActivity(), SearchBookActivity.class));
                break;
            case R.id.relative_add_continue_layout:
                startActivity(new Intent(getActivity(), SearchBookActivity.class));
                break;
            case R.id.tv_clear_book_list:
                presenter.deleteAll(getUserAccount());
                bookInfos.clear();
                borrowBookListAdapter.notifyDataSetChanged();
                cardviewNoBorrowBookLayout.setVisibility(View.VISIBLE);
                relativeToBorrowBookListLayout.setVisibility(View.GONE);
                break;
        }
    }

    @Override
    public void showRefresh() {
        swipeRefresh.setRefreshing(true);
    }

    @Override
    public void hideRefresh() {
        swipeRefresh.setRefreshing(false);
    }

    @Override
    public void setOne(List<One.DataBean> ones) {
        int currentSize = adapter.getItemCount();
        oDataBeen.addAll(ones);
        adapter.notifyItemRangeInserted(currentSize, ones.size());
    }

    @Override
    public void setToBorrowBook() {
        int currentSize = borrowBookListAdapter.getItemCount();
        bookInfos.addAll(presenter.loadBookInfos(getUserAccount()));
        borrowBookListAdapter.notifyItemRangeInserted(currentSize, presenter.loadBookInfos(getUserAccount()).size());
    }

    @Override
    public void showError(String errormsg) {
        MyToastUtils.showToast(getActivity(), errormsg);
    }

    @Override
    public void onRefresh() {
        if (Utils.isOnline(getContext())) {
            presenter.getOne();
            if (adapter != null) {
                adapter.clear();
            }
        } else {
            MyToastUtils.showToast(getContext(), "哎呀,网络有问题");
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (presenter.loadBookInfos(getUserAccount()).size() != 0) {
            cardviewNoBorrowBookLayout.setVisibility(View.GONE);
            relativeToBorrowBookListLayout.setVisibility(View.VISIBLE);
            bookInfos.clear();
            borrowBookListAdapter.notifyDataSetChanged();
            setToBorrowBook();
        } else {
            cardviewNoBorrowBookLayout.setVisibility(View.VISIBLE);
            relativeToBorrowBookListLayout.setVisibility(View.GONE);
        }
    }


    @Override
    public void onItemClick(View itemview, int position) {
        BookInfoActivity.startActivity(getContext(), bookInfos.get(position).getBookInfoId());
    }


}


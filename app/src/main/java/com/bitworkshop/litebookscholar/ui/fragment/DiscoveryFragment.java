package com.bitworkshop.litebookscholar.ui.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bitworkshop.litebookscholar.R;
import com.bitworkshop.litebookscholar.adapter.OneAdapter;
import com.bitworkshop.litebookscholar.entity.One;
import com.bitworkshop.litebookscholar.presenter.DiscoveryPresenter;
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

public class DiscoveryFragment extends Fragment implements Toolbar.OnMenuItemClickListener, IDiscoverView, SwipeRefreshLayout.OnRefreshListener {
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
    @BindView(R.id.cardview_to_borrow_book_list_layout)
    CardView cardviewToBorrowBookListLayout;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    private DiscoveryPresenter presenter;
    private OneAdapter adapter;
    private List<One.DataBean> oDataBeen = new ArrayList<>();

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
        initToolbar();
        presenter = new DiscoveryPresenter(this);
        adapter = new OneAdapter(getContext(), oDataBeen);
        recyclerVol.setAdapter(adapter);
        swipeRefresh.setColorSchemeColors(Color.YELLOW, Color.RED, Color.BLUE);
        swipeRefresh.setDistanceToTriggerSync(300);
        swipeRefresh.setOnRefreshListener(this);
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

    private void initToolbar() {
        tvToolbarTitle.setVisibility(View.VISIBLE);
        tvToolbarTitle.setText(R.string.app_name);
        toolbar.inflateMenu(R.menu.dircovery_menu);
        toolbar.setOnMenuItemClickListener(this);
        recyclerVol.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        recyclerToBorrowList.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_scan:
                Toast.makeText(getActivity(), "扫一扫", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menu_search:
                startActivity(new Intent(getActivity(), SearchBookActivity.class));
                return true;
            default:
                return false;
        }
    }

    @OnClick({R.id.cardview_no_borrow_book_layout, R.id.cardview_to_borrow_book_list_layout})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cardview_no_borrow_book_layout:
                break;
            case R.id.cardview_to_borrow_book_list_layout:
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
}


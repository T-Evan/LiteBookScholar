package com.bitworkshop.litebookscholar.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bitworkshop.litebookscholar.R;
import com.bitworkshop.litebookscholar.ui.activity.SearchBookActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 发现
 * Created by AidChow on 2016/10/16.
 */

public class DiscoveryFragment extends Fragment implements Toolbar.OnMenuItemClickListener {
    @BindView(R.id.tv_toolbar_title)
    TextView tvToolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

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
    }

    private void initToolbar() {
        tvToolbarTitle.setVisibility(View.VISIBLE);
        tvToolbarTitle.setText(R.string.app_name);
        toolbar.inflateMenu(R.menu.dircovery_menu);
        toolbar.setOnMenuItemClickListener(this);
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
}


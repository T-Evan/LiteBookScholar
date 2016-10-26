package com.bitworkshop.litebookscholar.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bitworkshop.litebookscholar.R;
import com.bitworkshop.litebookscholar.ui.activity.EditInfoActivity;
import com.bitworkshop.litebookscholar.ui.view.CircleImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by aidChow on 2016/10/16.
 */

public class MineFragment extends Fragment implements Toolbar.OnMenuItemClickListener {
    @BindView(R.id.tv_toolbar_title)
    TextView tvToolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.image_user_icon)
    CircleImageView imageUserIcon;
    @BindView(R.id.tv_user_nickname)
    TextView tvUserNickname;
    @BindView(R.id.card_view_change_user_info)
    CardView cardViewChangeUserInfo;
    @BindView(R.id.card_view_borrow_rule)
    CardView cardViewBorrowRule;
    @BindView(R.id.card_view_setting)
    CardView cardViewSetting;
    @BindView(R.id.card_view_about)
    CardView cardViewAbout;

    public static MineFragment getInstance() {
        return new MineFragment();
    }

    public MineFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, container, false);
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
        toolbar.inflateMenu(R.menu.mine_menu);
        toolbar.setOnMenuItemClickListener(this);
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_message:
                Toast.makeText(getActivity(), "消息", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return false;
        }
    }

    @OnClick({R.id.card_view_change_user_info, R.id.card_view_borrow_rule, R.id.card_view_setting, R.id.card_view_about})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.card_view_change_user_info:
                goToActivity(EditInfoActivity.class);
                break;
            case R.id.card_view_borrow_rule:
                break;
            case R.id.card_view_setting:
                break;
            case R.id.card_view_about:
                break;
        }
    }

    private void goToActivity(Class activity) {
        startActivity(new Intent(getActivity(), activity));
    }
}


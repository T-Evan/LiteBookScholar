package com.bitworkshop.litebookscholar.ui.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bitworkshop.litebookscholar.R;
import com.bitworkshop.litebookscholar.entity.User;
import com.bitworkshop.litebookscholar.presenter.MinePresenter;
import com.bitworkshop.litebookscholar.ui.activity.AboutUsActivity;
import com.bitworkshop.litebookscholar.ui.activity.EditInfoActivity;
import com.bitworkshop.litebookscholar.ui.activity.LoginActivity;
import com.bitworkshop.litebookscholar.ui.activity.SettingsActivity;
import com.bitworkshop.litebookscholar.ui.activity.SplashActivity;
import com.bitworkshop.litebookscholar.ui.view.CircleImageView;
import com.bitworkshop.litebookscholar.ui.view.IMineView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by aidChow on 2016/10/16.
 */

public class MineFragment extends Fragment implements Toolbar.OnMenuItemClickListener, IMineView {
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

    private MinePresenter minePresenter;

    private String userAccount;
    private String password;

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
        minePresenter = new MinePresenter(this);

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
                // TODO: 2016/10/27 消息待做 
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
                EditInfoActivity.startActiviyForResult(getActivity(), userAccount, password);
                break;
            case R.id.card_view_borrow_rule:
                // TODO: 2016/10/27 借阅规则待做 
                break;
            case R.id.card_view_setting:
                goToActivity(SettingsActivity.class);
                break;
            case R.id.card_view_about:
                goToActivity(AboutUsActivity.class);
                break;
        }
    }

    private void goToActivity(Class activity) {
        startActivity(new Intent(getActivity(), activity));
    }

    /**
     * 设置用户信息
     *
     * @param user
     */
    @Override
    public void setUserInfo(User user) {
        userAccount = user.getUser();
        password = user.getUserPassword();
        if (user.getUrl() != null && user.getPetname() != null) {
            System.out.println(userAccount + " " + password);
            Glide.with(getActivity()).load(user.getUrl())
                    .placeholder(R.drawable.default_image)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(new SimpleTarget<GlideDrawable>() {
                        @Override
                        public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                            imageUserIcon.setImageDrawable(resource);
                        }
                    });
            tvUserNickname.setText(user.getPetname());
        } else {
            tvUserNickname.setText("您还没有设置昵称快去设置吧");
        }
    }

    /**
     * 从sharedPreferencs文件中获取账户信息
     * 作为关键字，去数据库中执行查询
     *
     * @return
     */
    private String getUserAccount() {
        SharedPreferences sp = getActivity().getSharedPreferences(SplashActivity.IS_LOGIN_FILE_NAME, 0);
        return sp.getString(LoginActivity.USER_ACCOUNT, "");
    }

    @Override
    public void onStart() {
        super.onStart();
        if (!getUserAccount().equals("")) {
            minePresenter.setUserInfo(getUserAccount());
        }
    }
}


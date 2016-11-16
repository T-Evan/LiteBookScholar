package com.bitworkshop.litebookscholar.ui.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Switch;
import android.widget.TabHost;

import com.bitworkshop.litebookscholar.R;
import com.bitworkshop.litebookscholar.util.ActivityCollectior;
import com.bitworkshop.litebookscholar.util.MyToastUtils;
import com.bumptech.glide.Glide;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingsActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.switch_messages)
    Switch switchMessages;
    @BindView(R.id.card_view_cache_clean_layout)
    CardView cardViewCacheCleanLayout;
    @BindView(R.id.card_view_feedback_layout)
    CardView cardViewFeedbackLayout;
    @BindView(R.id.card_view_check_update_layout)
    CardView cardViewCheckUpdateLayout;
    @BindView(R.id.card_view_logout_layout)
    CardView cardViewLogoutLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ButterKnife.bind(this);
        setupToolbar(toolbar, "设置", true);
    }

    @OnClick({R.id.card_view_cache_clean_layout, R.id.card_view_feedback_layout, R.id.card_view_check_update_layout, R.id.card_view_logout_layout})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.card_view_cache_clean_layout:
                cleanCache();
                break;
            case R.id.card_view_feedback_layout:
                // TODO: 2016/10/27 反馈待做
                break;
            case R.id.card_view_check_update_layout:
                // TODO: 2016/10/27 更新待做
                break;
            case R.id.card_view_logout_layout:
                logout();
                break;
        }
    }

    /**
     * 清除Glide的缓存
     */
    private void cleanCache() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(2);
                    Glide.get(SettingsActivity.this).clearDiskCache();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        MyToastUtils.showToast(this, "缓存清除完成");
    }

    /**
     * 退出登录
     */
    private void logout() {
        showAlertDialog(null, "是否退出当前账户?", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                SharedPreferences sp = getSharedPreferences(SplashActivity.IS_LOGIN_FILE_NAME, 0);
                SharedPreferences.Editor editor = sp.edit();
                editor.putBoolean(SplashActivity.IS_LOGIN_KEY, false);//设置登录状态false
                editor.putString(LoginActivity.USER_ACCOUNT, "");//清除用户账号信息
                editor.apply();
                Intent intent = new Intent(SettingsActivity.this, SplashActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                ActivityCollectior.finishAll();
            }
        }, "确定", null, "取消");

    }
}

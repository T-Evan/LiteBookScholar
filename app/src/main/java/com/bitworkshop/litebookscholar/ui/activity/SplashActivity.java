package com.bitworkshop.litebookscholar.ui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.bitworkshop.litebookscholar.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 启动activity
 * Created by AidChow on 2016/10/17.
 */

public class SplashActivity extends AppCompatActivity {
    public static final String IS_LOGIN_FILE_NAME = "login_file";
    public static final String IS_LOGIN_KEY = "IS_LOGIN";
    //activity集合
    public static List<AppCompatActivity> activities = new ArrayList<>();
    @BindView(R.id.btu_login)
    Button btuLogin;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        if (isLogin()) {
            btuLogin.setVisibility(View.GONE);
            Intent i = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(i);
            finish();
        }
    }

    private boolean isLogin() {
        SharedPreferences sp = getSharedPreferences(IS_LOGIN_FILE_NAME, 0);
        return sp.getBoolean(IS_LOGIN_KEY, false);
    }

    @OnClick(R.id.btu_login)
    public void onClick() {
        activities.add(this);
        Intent i = new Intent(SplashActivity.this, LoginActivity.class);
        startActivity(i);
    }

}

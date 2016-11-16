package com.bitworkshop.litebookscholar.ui.fragment;

import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.bitworkshop.litebookscholar.R;
import com.bitworkshop.litebookscholar.ui.activity.LoginActivity;
import com.bitworkshop.litebookscholar.ui.activity.SplashActivity;

/**
 * Created by 78537 on 2016/11/12.
 */

public class BaseFragment extends Fragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    /**
     * 从sharedPreferencs文件中获取账户信息
     * 作为关键字，去数据库中执行查询
     *
     * @return
     */
    protected String getUserAccount() {
        SharedPreferences sp = getActivity().getSharedPreferences(SplashActivity.IS_LOGIN_FILE_NAME, 0);
        return sp.getString(LoginActivity.USER_ACCOUNT, "");
    }

    protected void initToolbarCustemerTitle(TextView title) {
        Typeface typeface = Typeface.createFromAsset(getActivity().getAssets(), "fonts/方正清刻本悦宋简体.TTF");
        title.setVisibility(View.VISIBLE);
        title.setText(R.string.app_name);
        title.setTypeface(typeface);
    }
}

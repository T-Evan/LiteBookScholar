package com.bitworkshop.litebookscholar.ui.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.bitworkshop.litebookscholar.R;
import com.bitworkshop.litebookscholar.entity.User;
import com.bitworkshop.litebookscholar.presenter.LogingPresenter;
import com.bitworkshop.litebookscholar.ui.view.ILoginView;
import com.bitworkshop.litebookscholar.util.MyToastUtils;
import com.bitworkshop.litebookscholar.util.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity implements ILoginView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.edit_username)
    EditText editUsername;
    @BindView(R.id.edit_lib_account)
    EditText editLibAccount;
    @BindView(R.id.edit_password)
    EditText editPassword;
    @BindView(R.id.btu_login)
    Button btuLogin;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    private LogingPresenter logingPresenter;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        initToolBar();
        logingPresenter = new LogingPresenter(this);
    }

    private void initToolBar() {
        setSupportActionBar(toolbar);
        setTitle("登录");
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void LoginSuccess(User user) {
        System.out.println(user.getMessage());
        if (user.getMessage().equals("登录成功，请填写更多信息")) {
            startActivity(new Intent(this, EditInfoActivity.class));
        } else {
            Intent i = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(i);
        }
        SharedPreferences sp = getSharedPreferences(SplashActivity.IS_LOGIN_FILE_NAME, 0);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(SplashActivity.IS_LOGIN_KEY, true);
        editor.apply();
        for (AppCompatActivity a : SplashActivity.activities) {
            a.finish();
        }
        finish();
    }

    @Override
    public void LoginFailed(String msg) {
        MyToastUtils.showToast(this, msg);
    }

    @Override
    public String getInputName() {
        return Utils.editextUtiils(editUsername);
    }

    @Override
    public String getInputPassword() {
        return Utils.editextUtiils(editPassword);
    }

    @Override
    public String getInputLibAccount() {
        return Utils.editextUtiils(editLibAccount);
    }

    @Override
    public void showLoading() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("登录中...");
            progressDialog.show();
        }
    }


    @Override
    public void hideLoading() {
        if (progressDialog != null) {
            progressDialog.cancel();
            progressDialog = null;
        }
    }

    @Override
    public boolean userNameIsEmpty(String username) {
        return username.equals("");
    }

    @Override
    public boolean passwordIsEmpty(String password) {
        return password.equals("");
    }

    @Override
    public boolean libAccountIsEmpty(String account) {
        return account.equals("");
    }

    @OnClick(R.id.btu_login)
    public void onClick() {
        login();
    }

    private void login() {
        if (Utils.isOnline(getApplicationContext())) {
            if (userNameIsEmpty(getInputName())) {
                MyToastUtils.showToast(getApplicationContext(), "请输入姓名");
            } else if (passwordIsEmpty(getInputPassword())) {
                MyToastUtils.showToast(getApplicationContext(), "请输入密码");
            } else if (libAccountIsEmpty(getInputLibAccount())) {
                MyToastUtils.showToast(getApplicationContext(), "账号不能为空");
            } else {
                logingPresenter.Login(getInputName(), getInputLibAccount(), getInputPassword());
            }
        } else {
            MyToastUtils.showToast(getApplicationContext(), "当前网络无连接,请检查网络设置");
        }
    }
}

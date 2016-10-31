package com.bitworkshop.litebookscholar.ui.activity;

import android.app.ProgressDialog;
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
import com.bitworkshop.litebookscholar.util.ProgressDialogUtil;
import com.bitworkshop.litebookscholar.util.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 登录类
 * 用于登录到图书馆，若是第一次登录跳转致信息设置界面
 */
public class LoginActivity extends BaseActivity implements ILoginView {

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
    public static final String USER_ACCOUNT = "user_account";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        setupToolbar(toolbar, "登录", true);
        logingPresenter = new LogingPresenter(this);
    }


    @Override
    public void LoginSuccess(User user) {
        System.out.println(user.getMessage());
        if (user.getMessage().equals("登录成功，请填写更多信息")) {
            EditInfoActivity.startActiviyForResult(this, user.getUser(), user.getUserPassword());
        } else {
            MainActivity.startActiviyForResult(LoginActivity.this, false);
        }
        SharedPreferences sp = getSharedPreferences(SplashActivity.IS_LOGIN_FILE_NAME, 0);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(USER_ACCOUNT, user.getUser());
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
        ProgressDialogUtil.showProgressBar(this, "登录中...");
    }


    @Override
    public void hideLoading() {
        ProgressDialogUtil.hideProgressDiaglog();
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

package com.bitworkshop.litebookscholar.presenter;

import android.os.Handler;

import com.bitworkshop.litebookscholar.entity.User;
import com.bitworkshop.litebookscholar.model.ILogingModel;
import com.bitworkshop.litebookscholar.model.LogingModel;
import com.bitworkshop.litebookscholar.model.OnLoginListener;
import com.bitworkshop.litebookscholar.model.OnRequestListner;
import com.bitworkshop.litebookscholar.ui.view.ILoginView;

/**
 * Created by 78537 on 2016/10/17.
 */

public class LogingPresenter {
    private ILoginView iLoginView;
    private ILogingModel iLogingModel;
    private Handler mHandler;

    public LogingPresenter(ILoginView iLoginView) {
        this.iLoginView = iLoginView;
        iLogingModel = new LogingModel();
        mHandler = new Handler();
    }

    public void Login(String useName, String userAccount, String password) {
        iLoginView.showLoading();
        iLogingModel.Login(useName, userAccount, password, new OnRequestListner<User>() {
            @Override
            public void Seccess(User user) {
                iLoginView.hideLoading();
                iLoginView.LoginSuccess(user);
            }

            @Override
            public void Fiald(String msg) {
                iLoginView.hideLoading();
                iLoginView.LoginFailed(msg);
            }

            @Override
            public void Cancel() {
                iLoginView.hideLoading();
            }
        });
    }

    public void cancelLogin() {
        iLogingModel.cancelLogin();
    }
}

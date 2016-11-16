package com.bitworkshop.litebookscholar.ui.view;

import com.bitworkshop.litebookscholar.entity.User;

/**
 * Created by 78537 on 2016/10/17.
 */

public interface ILoginView {
    void LoginSuccess(User user);

    void LoginFailed(String msg);

    String getInputName();

    String getInputPassword();

    String getInputLibAccount();

    void showLoading();

    void hideLoading();

    boolean userNameIsEmpty(String username);

    boolean passwordIsEmpty(String password);

    boolean libAccountIsEmpty(String account);
}

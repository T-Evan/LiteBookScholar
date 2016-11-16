package com.bitworkshop.litebookscholar.model;

import com.bitworkshop.litebookscholar.entity.User;

/**
 * Created by 78537 on 2016/10/17.
 */

public interface ILogingModel {
    void Login(String userName, String userAccount, String userPassword, OnRequestListner<User> usr);

    void cancelLogin();
}

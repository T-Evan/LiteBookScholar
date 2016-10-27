package com.bitworkshop.litebookscholar.model;

import android.net.Uri;

import com.bitworkshop.litebookscholar.entity.User;

/**
 * Created by 78537 on 2016/10/26.
 */

public interface IEditUserInfoModel {
    void uplodaUserInfo(String userAccount, String password, String imageurl, String nickname, OnRequestListner<User> onRequestListner);

    void postImageToQiniuYun(Uri uri, OnRequestListner<String> qiniuTokenOnRequestListner);
}

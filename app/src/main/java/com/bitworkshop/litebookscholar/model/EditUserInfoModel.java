package com.bitworkshop.litebookscholar.model;

import android.net.Uri;

import com.bitworkshop.litebookscholar.api.Api;
import com.bitworkshop.litebookscholar.entity.QiniuToken;
import com.bitworkshop.litebookscholar.entity.User;
import com.bitworkshop.litebookscholar.retrofit.LoginService;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UploadManager;

import org.json.JSONException;
import org.json.JSONObject;
import org.litepal.crud.DataSupport;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by AidChow on 2016/10/26.
 */

public class EditUserInfoModel implements IEditUserInfoModel {
    private final LoginService loginService;

    public EditUserInfoModel() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Api.LOGIC_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        loginService = retrofit.create(LoginService.class);

    }

    @Override
    public void uplodaUserInfo(String userAccount, String password, String imageurl, String nickname, final OnRequestListner<User> onRequestListner) {
        System.out.println(userAccount + "\n" + password + "\n" + imageurl + "\n" + nickname);
        loginService.uploadImageAndNickname("upload",
                userAccount,
                password,
                imageurl,
                nickname).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                System.out.println(call.request().body());
                if (response.isSuccessful()) {
                    System.out.println(response.body().toString());
                    if (response.body().getCode().equals("200")) {
                        onRequestListner.Seccess(response.body());
                        updateUser(response.body());
                    } else {
                        onRequestListner.Fiald(response.body().getMessage());
                    }

                } else {
                    onRequestListner.Fiald(response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                onRequestListner.Fiald(t.getMessage());
            }
        });
    }

    private void updateUser(User body) {
        body.updateAll("user == ?", body.getUser());
    }

    @Override
    public void postImageToQiniuYun(final Uri uri, final OnRequestListner<String> qiniuTokenOnRequestListner) {
        loginService.getQiniuToken().enqueue(new Callback<QiniuToken>() {
            @Override
            public void onResponse(Call<QiniuToken> call, Response<QiniuToken> response) {
                if (response.isSuccessful()) {
                    System.out.println("token: " + response.body().getToken());
                    UploadManager uploadManager = new UploadManager();
                    uploadManager.put(uri.getPath()
                            , null, response.body().getToken(), new UpCompletionHandler() {
                                @Override
                                public void complete(String key, ResponseInfo info, JSONObject response) {
                                    if (info.isOK()) {
                                        try {
                                            String imageurl = "http://oflqakyh2.bkt.clouddn.com/" + response.getString("key");
                                            qiniuTokenOnRequestListner.Seccess(imageurl);
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    } else {
                                        qiniuTokenOnRequestListner.Fiald(info.error);
                                    }
                                    System.out.println(key + " " + info + " " + response);
                                }
                            }, null);
                } else {
                    qiniuTokenOnRequestListner.Fiald("发生了一些小意外");
                }
            }

            @Override
            public void onFailure(Call<QiniuToken> call, Throwable t) {
                qiniuTokenOnRequestListner.Fiald(t.getMessage());
            }
        });
    }


}
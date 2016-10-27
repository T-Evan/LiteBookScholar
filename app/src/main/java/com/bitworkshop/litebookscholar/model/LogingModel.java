package com.bitworkshop.litebookscholar.model;

import com.bitworkshop.litebookscholar.api.Api;
import com.bitworkshop.litebookscholar.entity.User;
import com.bitworkshop.litebookscholar.retrofit.LoginService;

import org.litepal.crud.DataSupport;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by AidChow on 2016/10/17.
 */

public class LogingModel implements ILogingModel {
    private static final String TAG = LogingModel.class.getSimpleName();
    private Call<User> call;
    private LoginService service;

    public LogingModel() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.LOGIC_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(LoginService.class);
    }

    @Override
    public void Login(final String userName, final String userAccount, final String userPassword, final OnRequestListner<User> usr) {
        call = service.login("validate", userAccount, userPassword, userName);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                System.out.println(call.request().url());
                if (response.isSuccessful()) {
                    if (response.body().getCode().equals("200")) {
                        User user = response.body();
                        user.setUser(userAccount);
                        user.setUserName(userName);
                        user.setUserPassword(userPassword);
                        usr.Seccess(user);
                        saveUserInfo(user);
                    } else {
                        usr.Fiald(response.body().getMessage());
                    }
                } else {
                    usr.Fiald(response.message());
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                usr.Fiald(t.getMessage());
            }
        });
    }

    @Override
    public void cancelLogin() {
        call.cancel();
    }

    private void saveUserInfo(User user) {
        List<User> users = DataSupport.where("user like ?", user.getUser()).find(User.class);
        if (users.size() == 0) {
            user.save();
        } else {
            user.updateAll("user == ?", user.getUser());
        }
    }
}

package com.bitworkshop.litebookscholar.model;

import com.bitworkshop.litebookscholar.api.Api;
import com.bitworkshop.litebookscholar.entity.PostType;
import com.bitworkshop.litebookscholar.entity.User;
import com.bitworkshop.litebookscholar.retrofit.LoginService;

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
    public void Login(String userName, String userAccount, String userPassword, final OnRequestListner<User> usr) {
        call = service.login("validate", userAccount, userPassword, userName);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                System.out.println(call.request().url());
                if (response.isSuccessful()) {
                    if (response.body().getCode().equals("200")) {
                        usr.Seccess(response.body());
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
    }
}

package com.bitworkshop.litebookscholar.model;

import com.bitworkshop.litebookscholar.api.Api;
import com.bitworkshop.litebookscholar.entity.One;
import com.bitworkshop.litebookscholar.retrofit.LoginService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by AidChow on 2016/10/27.
 */

public class OneModel implements IDiscoveryModel {
    private Call<One> call;
    private LoginService getOne;

    public OneModel() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.LOGIC_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        getOne = retrofit.create(LoginService.class);
    }

    @Override
    public void loadOne(final OnRequestListner<One> oneOnRequestListner) {
        getOne.getOne("get").enqueue(new Callback<One>() {
            @Override
            public void onResponse(Call<One> call, Response<One> response) {
                if (response.isSuccessful()) {
                    if (response.body().getCode().equals("200")) {
                        oneOnRequestListner.Seccess(response.body());
                    } else {
                        oneOnRequestListner.Fiald(response.body().getMessage());
                    }
                } else {
                    oneOnRequestListner.Fiald(response.message());
                }
            }

            @Override
            public void onFailure(Call<One> call, Throwable t) {
                oneOnRequestListner.Fiald(t.getMessage());
            }
        });
    }
}

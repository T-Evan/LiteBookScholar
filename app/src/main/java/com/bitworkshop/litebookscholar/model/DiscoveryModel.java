package com.bitworkshop.litebookscholar.model;

import com.bitworkshop.litebookscholar.api.Api;
import com.bitworkshop.litebookscholar.entity.BookHoldingInfo;
import com.bitworkshop.litebookscholar.entity.BookInfo;
import com.bitworkshop.litebookscholar.entity.One;
import com.bitworkshop.litebookscholar.entity.User;
import com.bitworkshop.litebookscholar.retrofit.LoginService;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by AidChow on 2016/10/27.
 */

public class DiscoveryModel implements IDiscoveryModel {
    private Call<One> call;
    private LoginService getOne;

    public DiscoveryModel() {
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

    /**
     * 从数据库加载所有书籍数据
     *
     * @return
     */
    @Override
    public List<BookInfo> loadBooksFromDataBase(String userAccount) {
        User user = DataSupport.where("user like ?", userAccount)
                .findFirst(User.class);
        List<BookInfo> bookInfos = DataSupport.where("user_id = ?", String.valueOf(user.getId())).find(BookInfo.class);
        return bookInfos;
    }

    @Override
    public boolean deleteAll(String userAccount) {
        User user = DataSupport.where("user like ?", userAccount)
                .findFirst(User.class);
        List<BookInfo> bookInfos = DataSupport.where("user_id = ?", String.valueOf(user.getId())).find(BookInfo.class);
        DataSupport.markAsDeleted(bookInfos);
        for (BookInfo bookInfo : bookInfos) {
            DataSupport.markAsDeleted(bookInfo.getBookHoldingInfos(bookInfo.getId()));
            for (BookHoldingInfo bookHoldingInfo : bookInfo.getBookHoldingInfos(bookInfo.getId())) {
                bookHoldingInfo.delete();
            }
        }
        DataSupport.deleteAll(BookInfo.class, "user_id = ?", String.valueOf(user.getId()));
        System.out.println("删除完成");
        return true;
    }


}

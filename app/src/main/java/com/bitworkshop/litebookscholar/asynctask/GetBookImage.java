package com.bitworkshop.litebookscholar.asynctask;

import com.bitworkshop.litebookscholar.entity.DoubanBookList;
import com.bitworkshop.litebookscholar.retrofit.GetBookService;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 78537 on 2016/10/20.
 */

public class GetBookImage implements Callable<String> {

    private String url = null;
    private String title = null;

    public GetBookImage(String title) {
        this.title = title;
    }

    @Override
    public String call() throws Exception {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.douban.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GetBookService getBookService = retrofit.create(GetBookService.class);
        Map<String, String> map = new HashMap<>();
        map.put("fields", "id,title,image");
        map.put("count", "1");
        final retrofit2.Call<DoubanBookList> response = getBookService.getBookImge(title, map);
        Response<DoubanBookList> response1 = response.execute();
        if (response1.isSuccessful()) {
            for (DoubanBookList.BooksBean s : response1.body().getBooks()) {
                if (title.equals(s.getTitle())) {
                    url = s.getImage();
                    System.out.println(s.getImage());
                }
            }
        } else {
            System.out.println(response1.code());
        }
        return url;
    }
}

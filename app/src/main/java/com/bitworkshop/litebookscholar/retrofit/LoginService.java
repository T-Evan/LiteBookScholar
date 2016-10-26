package com.bitworkshop.litebookscholar.retrofit;

import com.bitworkshop.litebookscholar.entity.PostType;
import com.bitworkshop.litebookscholar.entity.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by AidChow on 2016/10/25.
 */

public interface LoginService {
    @FormUrlEncoded
    @POST("/AReader/Library//index.php")
    Call<User> login(@Field("type") String type,
                     @Field("user") String user,
                     @Field("passwd") String passwd,
                     @Field("name") String name);
}

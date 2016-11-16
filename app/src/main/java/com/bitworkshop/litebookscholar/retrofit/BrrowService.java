package com.bitworkshop.litebookscholar.retrofit;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Administrator on 2016/11/5.
 */

public interface BrrowService {
    @FormUrlEncoded
    @POST("/AReader/Library/index.php")
    Call<JSONObject> getbrrowlist(@Field("type") String type,
                                  @Field("user") String user,
                                  @Field("passwd") String passwd);


}

package com.bitworkshop.litebookscholar.retrofit;

import com.bitworkshop.litebookscholar.entity.DoubanBookInfo;
import com.bitworkshop.litebookscholar.entity.DoubanBookList;

import retrofit2.Call;
import retrofit2.http.*;

import java.util.Map;

/**
 * Created by 78537 on 2016/10/19.
 */
public interface GetBookService {
    //    http://www.lib.scuec.edu.cn/Search/SearchSet/
    @Headers({"Accept: application/json, text/javascript, */*; q=0.01",
            "User-Agent: Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.143 Safari/537.36"})
    @POST("opac/openlink.php")
    Call<String> setSearch(@Query("strSearchType") String string,
                           @Query("strText") String strText,
                           @Query("doctype") String docType,
                           @Query("location") String location);

    @GET("opac/openlink.php")
    Call<String> setSearch(@QueryMap Map<String, String> map);

    @Headers("User-Agent: Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.143 Safari/537.36")
    @GET("v2/book/search")
    Call<DoubanBookList> getBookImge(@Query("q") String title, @QueryMap Map<String, String> map);

    @GET("/v2/book/isbn/{isbn}")
    Call<DoubanBookInfo> getBookByISBN(@Path("isbn") String isbn);

    @GET("opac/item.php")
    Call<String> getBookInfo(@Query("marc_no") String bookNum);
}

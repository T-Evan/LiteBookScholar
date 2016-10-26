package com.bitworkshop.litebookscholar.model;

import android.os.Handler;

import com.bitworkshop.litebookscholar.asynctask.GetBookImage;
import com.bitworkshop.litebookscholar.asynctask.ThreadPoolFactory;
import com.bitworkshop.litebookscholar.entity.LibraryQueryListItm;
import com.bitworkshop.litebookscholar.retrofit.GetBookService;
import com.bitworkshop.litebookscholar.retrofit.StringConverterFactory;
import com.bitworkshop.litebookscholar.util.BookStringUtils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by 78537 on 2016/10/20.
 */

public class SearchModel implements ISerachModel {
    private GetBookService getBookServise;
    private Call<String> call;
    private ExecutorService exec;
    private Handler handler;

    public SearchModel() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://coin.lib.scuec.edu.cn/")
                .addConverterFactory(StringConverterFactory.create())
                .build();
        getBookServise = retrofit.create(GetBookService.class);
        handler = new Handler();
    }


    @Override
    public void doSearchBooks(String title, int pages, final OnRequestListner<List<LibraryQueryListItm>> listner) {
        Map<String, String> map = new HashMap<>();
        map.put("strSearchType", "title");
        map.put("strText", title);
        map.put("displaypg", "10");
        map.put("page", String.valueOf(pages));
        call = getBookServise.setSearch(map);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, final Response<String> response) {
                if (response.isSuccessful()) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            final List<LibraryQueryListItm> libraryQueryListItms = parseHtml(response.body());
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    listner.Seccess(libraryQueryListItms);
                                }
                            });
                        }
                    }).start();
                } else if (call.isCanceled()) {
                    listner.Cancel();
                } else {
                    listner.Fiald(response.message());
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                listner.Fiald(t.getMessage());
            }
        });
    }

    @Override
    public void cancelSearch() {
        if (call != null) {
            call.cancel();
        }
    }

    /**
     * 解析资源匹配
     *
     * @param response
     * @return
     */
    private List<LibraryQueryListItm> parseHtml(String response) {
        exec = Executors.newCachedThreadPool(new ThreadPoolFactory());
        List<LibraryQueryListItm> libraryQueryListItms = new ArrayList<>();
        Document doc = Jsoup.parse(response);
        Element bookList = doc.getElementById("search_book_list");
        if (bookList != null) {
            Elements es = bookList.select("li.book_list_info");
            for (Element e : es) {
                LibraryQueryListItm libraryQueryListItm = new LibraryQueryListItm();
                Elements a = e.select("h3 > a");
                String title = a.text().trim().substring(2).replace(".", "").trim();
                try {
                    String image = exec.submit(new GetBookImage(title)).get();
                    libraryQueryListItm.setImge(image);
                } catch (InterruptedException | ExecutionException e1) {
                    libraryQueryListItm.setImge(null);
                }
                System.out.println("标题: " + title);
                libraryQueryListItm.setBookTitle(title);
                System.out.println("详情url: " + "http://coin.lib.scuec.edu.cn/opac/" + a.attr("href"));
                libraryQueryListItm.setBookInfoUrl("http://coin.lib.scuec.edu.cn/opac/" + a.attr("href"));
                String index = e.select("h3").first().ownText();
                System.out.println("索书号: " + index);
                libraryQueryListItm.setIndexBookNum(index);
                String holdingBooks = BookStringUtils.holdingBook(e.select("p > span").html());
                System.out.println("馆藏复本: " + holdingBooks);
                libraryQueryListItm.setHoldingBooks(Integer.parseInt(holdingBooks));
                String canBorrowBooks = BookStringUtils.canBoorowBook(e.select("p > span").html());
                System.out.println("可借复本: " + canBorrowBooks);
                libraryQueryListItm.setCanBorrowBooks(Integer.parseInt(canBorrowBooks));
                for (Element e1 : e.select("p")) {
                    String author = BookStringUtils.matchAuthor(e1.html());
                    String publish = BookStringUtils.matchPub(e1.html());
                    libraryQueryListItm.setAuthor(author);
                    libraryQueryListItm.setPublish(publish);
                    System.out.println("作者: " + author);
                    System.out.println("出版社: " + publish);
                }
                libraryQueryListItms.add(libraryQueryListItm);
            }
            exec.shutdown();
        }

        return libraryQueryListItms;
    }

}


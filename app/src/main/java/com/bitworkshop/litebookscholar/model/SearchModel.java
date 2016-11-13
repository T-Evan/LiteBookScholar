package com.bitworkshop.litebookscholar.model;

import android.os.Handler;

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
import java.util.concurrent.ExecutorService;

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
    public void doSearchBooks(String type, String title, int pages, final OnSearchRequestListner<List<LibraryQueryListItm>> listner) {
        final Map<String, String> map = new HashMap<>();
        map.put("strSearchType", type);
        map.put("strText", title);
        map.put("displaypg", "10");
        map.put("page", String.valueOf(pages));
        call = getBookServise.setSearch(map);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, final Response<String> response) {
                if (response.isSuccessful()) {
                    listner.SetPages(getPages(response.body()));
                    listner.Seccess(parseHtml(response.body()));
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

    @Override
    public void addMore(String title, int pages, final OnRequestListner<List<LibraryQueryListItm>> listner) {
        final Map<String, String> map = new HashMap<>();
        map.put("strSearchType", "title");
        map.put("strText", title);
        map.put("displaypg", "10");
        map.put("page", String.valueOf(pages));
        call = getBookServise.setSearch(map);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, final Response<String> response) {
                if (response.isSuccessful()) {
                    listner.Seccess(parseHtml(response.body()));
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

    /**
     * 解析资源匹配
     *
     * @param response
     * @return
     */
    private List<LibraryQueryListItm> parseHtml(String response) {
        List<LibraryQueryListItm> libraryQueryListItms = new ArrayList<>();
        Document doc = Jsoup.parse(response);
        Element bookList = doc.getElementById("search_book_list");
        if (bookList != null) {
            Elements es = bookList.select("li.book_list_info");
            for (Element e : es) {
                LibraryQueryListItm libraryQueryListItm = new LibraryQueryListItm();
                Elements a = e.select("h3 > a");
                String title = a.text().trim().substring(2).replace(".", "").replace(":", "").trim();
                System.out.println("标题: " + title);
                libraryQueryListItm.setBookTitle(title);
                System.out.println("详情url: " + "http://coin.lib.scuec.edu.cn/opac/" + a.attr("href"));
                libraryQueryListItm.setBookInfoId(a.attr("href").replace("item.php?marc_no=", ""));
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

        }

        return libraryQueryListItms;
    }

    private int getPages(String response) {
        int pages = 1;
        Document doc = Jsoup.parse(response);
        Elements nums = doc.select("span.pagination");
        for (Element e : nums) {
            try {
                pages = Integer.parseInt(e.getElementsByAttribute("color").last().text());
                System.out.println(pages);
            } catch (NullPointerException ee) {
                pages = 1;
            }
        }
        return pages;
    }
}


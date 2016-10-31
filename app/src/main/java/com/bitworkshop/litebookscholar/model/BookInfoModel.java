package com.bitworkshop.litebookscholar.model;

import com.bitworkshop.litebookscholar.entity.BookInfo;
import com.bitworkshop.litebookscholar.entity.DoubanBookInfo;
import com.bitworkshop.litebookscholar.retrofit.GetBookService;
import com.bitworkshop.litebookscholar.retrofit.StringConverterFactory;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.litepal.crud.DataSupport;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 78537 on 2016/10/30.
 */

public class BookInfoModel implements IBookInfoModel {

    @Override
    public void getBookInfo(String bookInfoId, final OnRequestListner<BookInfo> listner) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://coin.lib.scuec.edu.cn/")
                .addConverterFactory(StringConverterFactory.create())
                .build();
        GetBookService getBookServise = retrofit.create(GetBookService.class);
        Call<String> call = getBookServise.getBookInfo(bookInfoId);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                System.out.println(call.request().url());
                if (response.isSuccessful()) {
                    BookInfo bookInfo = parseHtml(response.body());
                    listner.Seccess(bookInfo);
                    for (BookInfo.HoldingInfo holdingInfo : bookInfo.getHoldingInfos()) {
                        System.out.println(holdingInfo.toString());
                    }
                } else {
                    listner.Fiald(response.message());
                }

            }

            @Override
            public void onFailure(Call<String> call, Throwable throwable) {
                listner.Fiald(throwable.getMessage());
            }
        });
        if (call.isCanceled()) {
            listner.Cancel();
        }
    }

    @Override
    public void getBookInfoFromDouban(String isbn, final OnRequestListner<DoubanBookInfo> listner) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.douban.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GetBookService getBookServise = retrofit.create(GetBookService.class);
        getBookServise.getBookByISBN(isbn).enqueue(new Callback<DoubanBookInfo>() {
            @Override
            public void onResponse(Call<DoubanBookInfo> call, Response<DoubanBookInfo> response) {
                if (response.isSuccessful()) {
                    if (response.code() == 200) {
                        listner.Seccess(response.body());
                    } else {
                        listner.Seccess(null);
                    }
                } else {
                    listner.Seccess(null);
                }
            }

            @Override
            public void onFailure(Call<DoubanBookInfo> call, Throwable t) {
                listner.Fiald(t.getMessage());
            }
        });
    }

    @Override
    public void addToBookShelf(BookInfo bookInfo) {
        bookInfo.save();
    }

    /**
     * 从数据库拿取详情
     *
     * @param isbn
     */
    @Override
    public List<BookInfo> getBookInfoFromDatabase(String isbn) {
        List<BookInfo> bookInfos = DataSupport.where(" isbn like ? ", isbn).find(BookInfo.class);
        return bookInfos;
    }

    @Override
    public void Cancel() {
    }

    private static BookInfo parseHtml(String body) {
        Document doc = Jsoup.parse(body);
        BookInfo bookInfo = new BookInfo();
        Element ele = doc.getElementById("book_info");
//        System.out.println(ele.html());
        Elements dl = ele.select("dl.booklist");
        Elements authors = dl.get(0).select("dd");
        for (Element e : authors) {
            //作者
            String author = e.ownText().replace("/", "");
            bookInfo.setAuthor(author);
            System.out.println(author);
        }
        //书名
        String title = dl.get(0).select("dd > a").text();
        System.out.println(title);
        bookInfo.setBookTitle(title);

        //出版社
        String publish = dl.get(1).select("dd").text();
        bookInfo.setPublish(publish);

        //isbn
        Elements element1 = dl.get(2).select("dd");
        String isbn = element1.text().split("/")[0];
        System.out.println("isbn: " + isbn);
        bookInfo.setIsbn(isbn);

        //馆藏信息
        Elements tables = doc.select("tr.whitetext");
        List<BookInfo.HoldingInfo> holdingInfos = new ArrayList<>();
        for (Element e : tables) {
            BookInfo.HoldingInfo holdingInfo = new BookInfo.HoldingInfo();
            String location = e.select("[width=25%]").text();
            String indexBookNum = e.select("[width=10%]").text().replace("-", "").replace(" ", "");
            String bookStatus = e.select("[width=20%]").text();
            holdingInfo.setIndexBookNum(indexBookNum);
            holdingInfo.setBookLocation(location);
            holdingInfo.setBookStatus(bookStatus);
            holdingInfos.add(holdingInfo);
            System.out.println("地点: " + location);
            System.out.println("索书号" + indexBookNum);
            System.out.println("状态" + bookStatus);
        }
        bookInfo.setHoldingInfos(holdingInfos);
        return bookInfo;
    }

}

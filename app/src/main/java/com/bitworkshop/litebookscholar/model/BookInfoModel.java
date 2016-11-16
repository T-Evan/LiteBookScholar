package com.bitworkshop.litebookscholar.model;

import android.database.Cursor;

import com.bitworkshop.litebookscholar.entity.BookHoldingInfo;
import com.bitworkshop.litebookscholar.entity.BookInfo;
import com.bitworkshop.litebookscholar.entity.DoubanBookInfo;
import com.bitworkshop.litebookscholar.entity.User;
import com.bitworkshop.litebookscholar.retrofit.GetBookService;
import com.bitworkshop.litebookscholar.retrofit.StringConverterFactory;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.litepal.LitePalApplication;
import org.litepal.crud.DataSupport;
import org.litepal.util.Const;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
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
    private boolean isSave = false;

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
                    for (BookHoldingInfo holdingInfo : bookInfo.getHoldingInfos()) {
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
    public boolean addToBookShelf(BookInfo bookInfo, String userAccount) {
        User users = DataSupport.where("user like ?", userAccount)
                .findFirst(User.class);
        List<BookInfo> bookInfos = new ArrayList<>();
        bookInfos.add(bookInfo);
        List<BookHoldingInfo> bookHoldingInfos = new ArrayList<>();
        if (users != null) {
            DataSupport.markAsDeleted(bookInfos);
            DataSupport.markAsDeleted(bookInfos.get(0).getHoldingInfos());
            DataSupport.saveAll(bookInfos.get(0).getHoldingInfos());
            if (bookInfo.save()) {
                users.getBookInfos().add(bookInfos.get(0));
                users.save();
                System.out.println("book info model save");
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    @Override
    public boolean bookIsSave(String isbn) {
        return false;
    }

    /**
     * 从数据库拿取详情
     *
     * @param booinfoId
     */
    @Override
    public BookInfo getBookInfoFromDatabase(String booinfoId) {
        BookInfo bookInfo = DataSupport.where(" bookinfoid like ? ", booinfoId).findFirst(BookInfo.class);
        if (bookInfo != null) {
            return bookInfo;
        }
        return null;
    }

    @Override
    public boolean deletBookInfoFromDatabase(String booinfoId) {
        List<BookInfo> bookInfo = DataSupport.where(" bookinfoid like ? ", booinfoId).find(BookInfo.class);
        if (bookInfo != null) {
            for (BookHoldingInfo holdingInfo : bookInfo.get(0).getBookHoldingInfos(bookInfo.get(0).getId())) {
                holdingInfo.delete();
            }
            bookInfo.get(0).delete();
            return true;
        }
        return false;
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
        for (Element e : dl) {
            String temp = e.select("dt").first().text();
            //作者和题名
            if (temp.equals("题名/责任者:")) {
                String title = e.select("dd > a").text();
                String author = e.select("dd").text().substring(title.length() + 1);
                bookInfo.setAuthor(author);
                bookInfo.setBookTitle(title);
                System.out.println(author);
                System.out.println(title);
            }
            //出版社
            if (temp.equals("出版发行项:")) {
                String publish = e.select("dd").text();
                bookInfo.setPublish(publish);
                System.out.println(publish);
            }
            //isbn
            if (temp.equals("ISBN及定价:")) {
                String isbn = e.select("dd").text().replaceAll("/.*", "");
                System.out.println(isbn);
                bookInfo.setIsbn(isbn);
            }
        }

        //馆藏信息
        Elements tables = doc.select("tr.whitetext");
        for (Element e : tables) {
            BookHoldingInfo holdingInfo = new BookHoldingInfo();
            String location = e.select("[width=25%]").text();
            String indexBookNum = e.select("[width=10%]").text().replace("-", "").replace(" ", "");
            String bookStatus = e.select("[width=20%]").text();
            holdingInfo.setIndexBookNum(indexBookNum);
            holdingInfo.setBookLocation(location);
            holdingInfo.setBookStatus(bookStatus);
            bookInfo.getHoldingInfos().add(holdingInfo);
            System.out.println("地点: " + location);
            System.out.println("索书号" + indexBookNum);
            System.out.println("状态" + bookStatus);
        }
        return bookInfo;
    }

}

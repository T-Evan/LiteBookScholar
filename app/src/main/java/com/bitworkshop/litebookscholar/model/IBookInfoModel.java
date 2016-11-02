package com.bitworkshop.litebookscholar.model;

import com.bitworkshop.litebookscholar.entity.BookInfo;
import com.bitworkshop.litebookscholar.entity.DoubanBookInfo;

import java.util.List;

/**
 * Created by 78537 on 2016/10/30.
 */

public interface IBookInfoModel {
    void getBookInfo(String bookInfoId, OnRequestListner<BookInfo> listner);

    void getBookInfoFromDouban(String isbn, OnRequestListner<DoubanBookInfo> listner);

    boolean addToBookShelf(BookInfo bookInfo, String userAccount);

    boolean bookIsSave(String isbn);

    BookInfo getBookInfoFromDatabase(String isbn);

    boolean deletBookInfoFromDatabase(String isbn);

    void Cancel();
}

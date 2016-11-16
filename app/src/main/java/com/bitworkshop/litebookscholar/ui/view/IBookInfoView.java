package com.bitworkshop.litebookscholar.ui.view;

import com.bitworkshop.litebookscholar.entity.BookInfo;
import com.bitworkshop.litebookscholar.entity.DoubanBookInfo;

/**
 * Created by 78537 on 2016/10/30.
 */

public interface IBookInfoView {
    void setBookInfo(BookInfo bookInfo);

    void setBookInfoFromDouban(DoubanBookInfo doubanBookInfo);

    void showLoading();

    void hideLoading();

    void showError(String msg);

}

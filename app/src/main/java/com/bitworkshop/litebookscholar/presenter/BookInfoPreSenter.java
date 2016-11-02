package com.bitworkshop.litebookscholar.presenter;

import com.bitworkshop.litebookscholar.entity.BookInfo;
import com.bitworkshop.litebookscholar.entity.DoubanBookInfo;
import com.bitworkshop.litebookscholar.model.BookInfoModel;
import com.bitworkshop.litebookscholar.model.IBookInfoModel;
import com.bitworkshop.litebookscholar.model.OnRequestListner;
import com.bitworkshop.litebookscholar.ui.view.IBookInfoView;

import java.util.List;

/**
 * Created by AidChow on 2016/10/31.
 */

public class BookInfoPresenter {
    private IBookInfoModel iBookInfoModel;
    private IBookInfoView iBookInfoView;

    public BookInfoPresenter(IBookInfoView iBookInfoView) {
        this.iBookInfoView = iBookInfoView;
        iBookInfoModel = new BookInfoModel();
    }

    public void getBookInfoFromLib(String bookId) {
        iBookInfoView.showLoading();
        iBookInfoModel.getBookInfo(bookId, new OnRequestListner<BookInfo>() {
            @Override
            public void Seccess(BookInfo bookInfo) {
                iBookInfoView.hideLoading();
                iBookInfoView.setBookInfo(bookInfo);
            }

            @Override
            public void Fiald(String msg) {
                iBookInfoView.hideLoading();
                iBookInfoView.showError(msg);
            }

            @Override
            public void Cancel() {
                //pass
            }
        });
    }

    public void getBookInfoRromDouban(String isbn) {
        iBookInfoModel.getBookInfoFromDouban(isbn, new OnRequestListner<DoubanBookInfo>() {
            @Override
            public void Seccess(DoubanBookInfo doubanBookInfo) {
                iBookInfoView.setBookInfoFromDouban(doubanBookInfo);
            }

            @Override
            public void Fiald(String msg) {
                iBookInfoView.showError(msg);
            }

            @Override
            public void Cancel() {
                //pass
            }
        });
    }

    public BookInfo getBookInfoFromDatabase(String isbn) {
        return iBookInfoModel.getBookInfoFromDatabase(isbn);
    }

    public boolean addBookToShelf(BookInfo bookInfo, String userAccount) {
        return iBookInfoModel.addToBookShelf(bookInfo, userAccount);
    }

    public boolean deleteBookInfo(String booinfoId) {
        return iBookInfoModel.deletBookInfoFromDatabase(booinfoId);
    }
}

package com.bitworkshop.litebookscholar.ui.view;

import com.bitworkshop.litebookscholar.entity.LibraryQueryListItm;

import java.util.List;

/**
 * Created by 78537 on 2016/10/20.
 */

public interface ISearchView {
    void showLoading();

    void hideLoading();

    void setBookList(List<LibraryQueryListItm> listItms);

    void showFialed(String msg);

    void setPages(int pages);

    void addMore(List<LibraryQueryListItm> libraryQueryListItms);

    String getQueryTitle();
}

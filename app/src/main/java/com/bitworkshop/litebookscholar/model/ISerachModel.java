package com.bitworkshop.litebookscholar.model;

import com.bitworkshop.litebookscholar.entity.LibraryQueryListItm;

import java.util.List;

/**
 * Created by 78537 on 2016/10/20.
 */

public interface ISerachModel {
    void doSearchBooks(String type, String title, int pages, OnSearchRequestListner<List<LibraryQueryListItm>> listner);

    void cancelSearch();

    void addMore(String title, int pages, OnRequestListner<List<LibraryQueryListItm>> listner);
}

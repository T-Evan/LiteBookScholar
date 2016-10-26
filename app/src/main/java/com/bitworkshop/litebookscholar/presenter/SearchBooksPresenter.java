package com.bitworkshop.litebookscholar.presenter;

import com.bitworkshop.litebookscholar.entity.LibraryQueryListItm;
import com.bitworkshop.litebookscholar.model.ISerachModel;
import com.bitworkshop.litebookscholar.model.OnRequestListner;
import com.bitworkshop.litebookscholar.model.SearchModel;
import com.bitworkshop.litebookscholar.ui.view.ISearchView;

import java.util.List;

/**
 * Created by 78537 on 2016/10/20.
 */

public class SearchBooksPresenter {
    private ISearchView iSearchView;
    private ISerachModel iSerachModel;

    public SearchBooksPresenter(ISearchView iSearchView) {
        this.iSearchView = iSearchView;
        iSerachModel = new SearchModel();
    }

    public void searchBooks(String tilte, int pages) {
        iSearchView.showLoading();
        iSerachModel.doSearchBooks(tilte, pages, new OnRequestListner<List<LibraryQueryListItm>>() {
            @Override
            public void Seccess(List<LibraryQueryListItm> libraryQueryListItms) {
                iSearchView.setBookList(libraryQueryListItms);
                iSearchView.hideLoading();
            }

            @Override
            public void Fiald(String msg) {
                iSearchView.hideLoading();
                iSearchView.showFialed(msg);
            }

            @Override
            public void Cancel() {
                iSearchView.hideLoading();
            }
        });
    }

    public void cancel() {
        iSerachModel.cancelSearch();
        iSearchView.hideLoading();
    }
}

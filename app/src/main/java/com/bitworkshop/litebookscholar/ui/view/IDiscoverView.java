package com.bitworkshop.litebookscholar.ui.view;

import com.bitworkshop.litebookscholar.entity.One;

import java.util.List;

/**
 * Created by AidChow on 2016/10/27.
 */

public interface IDiscoverView {
    void showRefresh();

    void hideRefresh();

    void setOne(List<One.DataBean> ones);

    void setToBorrowBook();

    void showError(String errormsg);

}

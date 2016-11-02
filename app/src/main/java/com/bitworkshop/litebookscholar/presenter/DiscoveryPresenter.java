package com.bitworkshop.litebookscholar.presenter;

import android.icu.lang.UScript;

import com.bitworkshop.litebookscholar.entity.BookHoldingInfo;
import com.bitworkshop.litebookscholar.entity.BookInfo;
import com.bitworkshop.litebookscholar.entity.One;
import com.bitworkshop.litebookscholar.model.IDiscoveryModel;
import com.bitworkshop.litebookscholar.model.OnRequestListner;
import com.bitworkshop.litebookscholar.model.DiscoveryModel;
import com.bitworkshop.litebookscholar.ui.view.IDiscoverView;

import java.util.List;

/**
 * Created by 78537 on 2016/10/27.
 */

public class DiscoveryPresenter {
    private IDiscoveryModel iDiscoveryModel;
    private IDiscoverView iDiscoverView;

    public DiscoveryPresenter(IDiscoverView iDiscoverView) {
        this.iDiscoverView = iDiscoverView;
        iDiscoveryModel = new DiscoveryModel();
    }

    public void getOne() {
        iDiscoverView.showRefresh();
        iDiscoveryModel.loadOne(new OnRequestListner<One>() {
            @Override
            public void Seccess(One one) {
                iDiscoverView.hideRefresh();
                iDiscoverView.setOne(one.getData());
            }

            @Override
            public void Fiald(String msg) {
                iDiscoverView.hideRefresh();
                iDiscoverView.showError(msg);
            }

            @Override
            public void Cancel() {
                //pass
            }
        });
    }

    public List<BookInfo> loadBookInfos(String userAccount) {
        return iDiscoveryModel.loadBooksFromDataBase(userAccount);
    }

    public boolean deleteAll(String userAccount) {
        return iDiscoveryModel.deleteAll(userAccount);
    }
}

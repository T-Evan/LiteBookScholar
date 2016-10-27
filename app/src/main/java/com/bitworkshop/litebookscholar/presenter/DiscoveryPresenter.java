package com.bitworkshop.litebookscholar.presenter;

import com.bitworkshop.litebookscholar.entity.One;
import com.bitworkshop.litebookscholar.model.IDiscoveryModel;
import com.bitworkshop.litebookscholar.model.OnRequestListner;
import com.bitworkshop.litebookscholar.model.OneModel;
import com.bitworkshop.litebookscholar.ui.view.IDiscoverView;

/**
 * Created by 78537 on 2016/10/27.
 */

public class DiscoveryPresenter {
    private IDiscoveryModel iDiscoveryModel;
    private IDiscoverView iDiscoverView;

    public DiscoveryPresenter(IDiscoverView iDiscoverView) {
        this.iDiscoverView = iDiscoverView;
        iDiscoveryModel = new OneModel();
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
}

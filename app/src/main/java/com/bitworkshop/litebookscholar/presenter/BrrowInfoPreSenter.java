package com.bitworkshop.litebookscholar.presenter;

import com.bitworkshop.litebookscholar.entity.BrrowInfo;
import com.bitworkshop.litebookscholar.model.BrrowInfoModel;
import com.bitworkshop.litebookscholar.model.IBrrowInfoModel;
import com.bitworkshop.litebookscholar.model.OnRequestListner;
import com.bitworkshop.litebookscholar.ui.view.IBrrowInfoView;

import java.util.List;

public class BrrowInfoPreSenter {
    private IBrrowInfoModel iBrrowInfoModel;
    private IBrrowInfoView iBrrowInfoView;

    public BrrowInfoPreSenter(IBrrowInfoView iBrrowInfoView) {
        this.iBrrowInfoView = iBrrowInfoView;
        iBrrowInfoModel = new BrrowInfoModel();
    }

    public void getBrrowInfoFromLib(String userAccount, String password) {

        iBrrowInfoModel.getBrrowInfo(userAccount, password, new OnRequestListner<List<BrrowInfo.brrowdata>>() {
            @Override
            public void Seccess(List<BrrowInfo.brrowdata> brrowInfo) {
                    iBrrowInfoView.setBrrowInfo(brrowInfo);
            }

            @Override
            public void Fiald(String msg) {
            }

            @Override
            public void Cancel() {
            }
        });
    }


}




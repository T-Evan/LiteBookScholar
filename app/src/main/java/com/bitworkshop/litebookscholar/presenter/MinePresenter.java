package com.bitworkshop.litebookscholar.presenter;

import com.bitworkshop.litebookscholar.entity.User;
import com.bitworkshop.litebookscholar.model.IMineModel;
import com.bitworkshop.litebookscholar.model.MineModel;
import com.bitworkshop.litebookscholar.ui.view.IMineView;

/**
 * Created by AidChow on 2016/10/26.
 */

public class MinePresenter {
    private IMineModel iMineModel;
    private IMineView iMineView;

    public MinePresenter(IMineView iMineView) {
        this.iMineView = iMineView;
        iMineModel = new MineModel();
    }

    public void setUserInfo(String userAccount) {
        User user = iMineModel.readUserInfo(userAccount);
        iMineView.setUserInfo(user);
    }

}

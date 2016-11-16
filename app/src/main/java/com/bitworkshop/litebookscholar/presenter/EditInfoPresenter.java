package com.bitworkshop.litebookscholar.presenter;

import android.net.Uri;

import com.bitworkshop.litebookscholar.entity.User;
import com.bitworkshop.litebookscholar.model.EditUserInfoModel;
import com.bitworkshop.litebookscholar.model.IEditUserInfoModel;
import com.bitworkshop.litebookscholar.model.OnRequestListner;
import com.bitworkshop.litebookscholar.ui.view.IEditUserInfoView;

/**
 * Created by 78537 on 2016/10/26.
 */

public class EditInfoPresenter {
    private IEditUserInfoModel iEditUserInfoModel;
    private IEditUserInfoView iEditUserInfoView;

    public EditInfoPresenter(IEditUserInfoView iEditUserInfoView) {
        this.iEditUserInfoView = iEditUserInfoView;
        iEditUserInfoModel = new EditUserInfoModel();
    }

    public void postImageToQiniuYun(Uri uri) {
        iEditUserInfoView.showLoading();
        iEditUserInfoModel.postImageToQiniuYun(uri, new OnRequestListner<String>() {
            @Override
            public void Seccess(String s) {
                iEditUserInfoView.setImageUrl(s);
            }
            @Override
            public void Fiald(String msg) {
                iEditUserInfoView.hideLoading();
                iEditUserInfoView.showError(msg);
            }

            @Override
            public void Cancel() {
                //pass
            }
        });
    }

    public void updateUserInfo(String userAccount, String password, String imageUrl, String nickname) {
        iEditUserInfoModel.uplodaUserInfo(userAccount, password, imageUrl, nickname, new OnRequestListner<User>() {
            @Override
            public void Seccess(User user) {
                iEditUserInfoView.hideLoading();
                iEditUserInfoView.seccess();
            }
            @Override
            public void Fiald(String msg) {
                iEditUserInfoView.hideLoading();
                iEditUserInfoView.showError(msg);
            }

            @Override
            public void Cancel() {
            }
        });
    }
}

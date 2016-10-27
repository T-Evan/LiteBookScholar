package com.bitworkshop.litebookscholar.ui.view;

/**
 * Created by AidChow on 2016/10/26.
 */

public interface IEditUserInfoView {
    String getUserNickname();

    void seccess();

    void showLoading();

    void hideLoading();

    void showError(String errorMsg);

    void setImageUrl(String imageUrl);

}

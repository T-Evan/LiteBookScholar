package com.bitworkshop.litebookscholar.model;

import com.bitworkshop.litebookscholar.entity.BrrowInfo;

import java.util.List;

/**
 * Created by Administrator on 2016/11/3.
 */

public interface IBrrowInfoModel {
    void getBrrowInfo(String userAccount, String userPassword, OnRequestListner<List<BrrowInfo.brrowdata>> bro);
    void continueBrrow();
}

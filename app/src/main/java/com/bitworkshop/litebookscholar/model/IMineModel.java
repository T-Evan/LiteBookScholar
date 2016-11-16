package com.bitworkshop.litebookscholar.model;

import com.bitworkshop.litebookscholar.entity.User;

/**
 * Created by AidChow on 2016/10/26.
 */

public interface IMineModel {
    User readUserInfo(String userAccount);
}

package com.bitworkshop.litebookscholar.model;

import com.bitworkshop.litebookscholar.entity.User;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * Created by AidChow on 2016/10/26.
 */

public class MineModel implements IMineModel {


    @Override
    public User readUserInfo(String userAccount) {
        List<User> users = DataSupport.where("user like ?", userAccount)
                .find(User.class);
        return users.get(0);
    }
}

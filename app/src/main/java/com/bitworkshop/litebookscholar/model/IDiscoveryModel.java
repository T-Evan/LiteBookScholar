package com.bitworkshop.litebookscholar.model;

import com.bitworkshop.litebookscholar.entity.BookInfo;
import com.bitworkshop.litebookscholar.entity.One;

import java.util.List;

/**
 * Created by AidChow on 2016/10/27.
 */

public interface IDiscoveryModel {
    void loadOne(OnRequestListner<One> oneOnRequestListner);

    List<BookInfo> loadBooksFromDataBase(String userAccount);

    boolean deleteAll(String userAccount);
}

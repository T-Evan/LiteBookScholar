package com.bitworkshop.litebookscholar.model;

import com.bitworkshop.litebookscholar.entity.One;

/**
 * Created by AidChow on 2016/10/27.
 */

public interface IDiscoveryModel {
    void loadOne(OnRequestListner<One> oneOnRequestListner);
}

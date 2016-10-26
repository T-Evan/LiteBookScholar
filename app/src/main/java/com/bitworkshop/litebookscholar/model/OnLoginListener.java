package com.bitworkshop.litebookscholar.model;

import com.bitworkshop.litebookscholar.entity.User;

/**
 * Created by 78537 on 2016/10/17.
 */

public interface OnLoginListener<T> {
    void Success(T t);

    void Failed();
}

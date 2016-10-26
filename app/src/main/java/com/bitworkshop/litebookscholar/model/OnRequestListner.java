package com.bitworkshop.litebookscholar.model;

/**
 * Created by 78537 on 2016/10/20.
 */

public interface OnRequestListner<T> {
    void Seccess(T t);

    void Fiald(String msg);

    void Cancel();
}

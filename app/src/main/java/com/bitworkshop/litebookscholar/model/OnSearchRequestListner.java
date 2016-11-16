package com.bitworkshop.litebookscholar.model;

/**
 * Created by 78537 on 2016/10/20.
 */

public interface OnSearchRequestListner<T> {
    void Seccess(T t);

    void Fiald(String msg);

    void Cancel();

    void SetPages(int pages);
}

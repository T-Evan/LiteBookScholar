package com.bitworkshop.litebookscholar.entity;

import org.litepal.crud.DataSupport;

/**
 * Created by 78537 on 2016/11/1.
 */

public class BookHoldingInfo extends DataSupport {
    private String indexBookNum;//索数号
    private String bookLocation;//借阅位置
    private String bookStatus;

    public String getIndexBookNum() {
        return indexBookNum;
    }

    public void setIndexBookNum(String indexBookNum) {
        this.indexBookNum = indexBookNum;
    }

    public String getBookLocation() {
        return bookLocation;
    }

    public void setBookLocation(String bookLocation) {
        this.bookLocation = bookLocation;
    }

    public String getBookStatus() {
        return bookStatus;
    }

    public void setBookStatus(String bookStatus) {
        this.bookStatus = bookStatus;
    }

    @Override
    public String toString() {
        return "HoldingInfo{" +
                "indexBookNum='" + indexBookNum + '\'' +
                ", bookLocation='" + bookLocation + '\'' +
                ", bookStatus='" + bookStatus + '\'' +
                '}';
    }
}

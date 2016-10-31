package com.bitworkshop.litebookscholar.entity;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * Created by AidChow on 2016/10/30.
 */

public class BookInfo extends DataSupport {
    private String bookTitle;//标题
    private String author;//作者
    private String publish;//出版社
    private double average;//平均分
    private String smalImge;//图书封面小图
    private String midImge;//图书封面中图
    private String largeImge;//图书封面大图

    private List<HoldingInfo> holdingInfos;

    private String summary;//简介

    private String isbn;

    public static class HoldingInfo {
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

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublish() {
        return publish;
    }

    public void setPublish(String publish) {
        this.publish = publish;
    }

    public double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }

    public String getSmalImge() {
        return smalImge;
    }

    public void setSmalImge(String smalImge) {
        this.smalImge = smalImge;
    }

    public String getMidImge() {
        return midImge;
    }

    public void setMidImge(String midImge) {
        this.midImge = midImge;
    }

    public String getLargeImge() {
        return largeImge;
    }

    public void setLargeImge(String largeImge) {
        this.largeImge = largeImge;
    }

    public List<HoldingInfo> getHoldingInfos() {
        return holdingInfos;
    }

    public void setHoldingInfos(List<HoldingInfo> holdingInfos) {
        this.holdingInfos = holdingInfos;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Override
    public String toString() {
        return "BookInfo{" +
                "bookTitle='" + bookTitle + '\'' +
                ", author='" + author + '\'' +
                ", publish='" + publish + '\'' +
                ", average=" + average +
                ", smalImge='" + smalImge + '\'' +
                ", midImge='" + midImge + '\'' +
                ", largeImge='" + largeImge + '\'' +
                ", holdingInfos=" + holdingInfos +
                ", summary='" + summary + '\'' +
                ", isbn='" + isbn + '\'' +
                '}';
    }
}

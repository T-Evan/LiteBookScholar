package com.bitworkshop.litebookscholar.entity;

import android.graphics.Bitmap;

import org.litepal.annotation.Column;
import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AidChow on 2016/10/30.
 */

public class BookInfo extends DataSupport {
    private long id;
    private String bookTitle;//标题
    private String author;//作者
    private String publish;//出版社
    private double average;//平均分
    private String smalImge;//图书封面小图
    private String midImge;//图书封面中图
    private String largeImge;//图书封面大图

    private String bookInfoId;
    private Bitmap largeBitmap;//大图bitmap对象

    private List<BookHoldingInfo> holdingInfos = new ArrayList<>();

    private String summary;//简介

    private String isbn;


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

    public List<BookHoldingInfo> getHoldingInfos() {
        return holdingInfos;
    }

    public void setHoldingInfos(List<BookHoldingInfo> holdingInfos) {
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

    public Bitmap getLargeBitmap() {
        return largeBitmap;
    }

    public void setLargeBitmap(Bitmap largeBitmap) {
        this.largeBitmap = largeBitmap;
    }

    public String getBookInfoId() {
        return bookInfoId;
    }

    public void setBookInfoId(String bookInfoId) {
        this.bookInfoId = bookInfoId;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<BookHoldingInfo> getBookHoldingInfos(long id) {
        return DataSupport.where("bookinfo_id like ?", String.valueOf(id)).find(BookHoldingInfo.class);
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
                ", bookInfoId='" + bookInfoId + '\'' +
                ", largeBitmap=" + largeBitmap +
                ", holdingInfos=" + holdingInfos +
                ", summary='" + summary + '\'' +
                ", isbn='" + isbn + '\'' +
                '}';
    }
}

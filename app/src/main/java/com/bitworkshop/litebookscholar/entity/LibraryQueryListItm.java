package com.bitworkshop.litebookscholar.entity;

/**
 * Created by AidChow on 2016/10/20.
 */
public class LibraryQueryListItm {
    private String bookTitle;//标题
    private String bookInfoId;//详情URl
    private String indexBookNum;//索数号
    private int holdingBooks;//馆藏复本
    private int canBorrowBooks;//可借复本
    private String author;//作者
    private String publish;//出版社
    private String imge;//图书封面

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getBookInfoId() {
        return bookInfoId;
    }

    public void setBookInfoId(String bookInfoId) {
        this.bookInfoId = bookInfoId;
    }

    public String getIndexBookNum() {
        return indexBookNum;
    }

    public void setIndexBookNum(String indexBookNum) {
        this.indexBookNum = indexBookNum;
    }

    public int getHoldingBooks() {
        return holdingBooks;
    }

    public void setHoldingBooks(int holdingBooks) {
        this.holdingBooks = holdingBooks;
    }

    public int getCanBorrowBooks() {
        return canBorrowBooks;
    }

    public void setCanBorrowBooks(int canBorrowBooks) {
        this.canBorrowBooks = canBorrowBooks;
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

    public String getImge() {
        return imge;
    }

    public void setImge(String imge) {
        this.imge = imge;
    }
}

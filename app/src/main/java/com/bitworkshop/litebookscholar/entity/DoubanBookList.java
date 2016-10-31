package com.bitworkshop.litebookscholar.entity;

import java.util.List;

/**
 * Created by 78537 on 2016/10/20.
 */
public class DoubanBookList {

    /**
     * count : 1
     * start : 0
     * total : 25
     * books : [{"image":"https://img3.doubanio.com/mpic/s29071040.jpg","id":"1474824","title":"Thinking in Java"}]
     */

    private int count;
    private int start;
    private int total;
    /**
     * image : https://img3.doubanio.com/mpic/s29071040.jpg
     * id : 1474824
     * title : Thinking in Java
     */

    private List<BooksBean> books;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<BooksBean> getBooks() {
        return books;
    }

    public void setBooks(List<BooksBean> books) {
        this.books = books;
    }

    public static class BooksBean {
        private String image;
        private String id;
        private String title;

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        @Override
        public String toString() {
            return "BooksBean{" +
                    "image='" + image + '\'' +
                    ", id='" + id + '\'' +
                    ", title='" + title + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "DoubanBookList{" +
                "count=" + count +
                ", start=" + start +
                ", total=" + total +
                ", books=" + books +
                '}';
    }
}

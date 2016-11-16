package com.bitworkshop.litebookscholar.entity;

import java.util.List;

/**
 * Created by Administrator on 2016/11/3.
 */

public class BrrowInfo {

    /**
     * code : 200
     * message : 查询成功
     * booklist : [["1726620","Java范例开发大全 / 张帆等编著","2016-10-11","2016-11-10        ","0","9楼北计算机应用借阅室","无"]]
     */
    private String code;
    private String message;
    private List<List<brrowdata>> data;

    public static class brrowdata {


        private String Imge;//图书封面小图
        private String bookTitle;//标题
        private String brrowTime;//借阅时间
        private String endTime;//归还时间
        private String url;//详情
        private String bookId;//索书号
        private String bookLocation;//借阅位置
        private String num;
        private String state;

        public String getImge() {
            return Imge;
        }

        public void setImge(String imge) {
            Imge = imge;
        }
        public String getBookTitle() {
            return bookTitle;
        }

        public void setBookTitle(String bookTitle) {
            this.bookTitle = bookTitle;
        }

        public String getBrrowTime() {
            return brrowTime;
        }

        public void setBrrowTime(String brrowTime) {
            this.brrowTime = brrowTime;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getBookId() {
            return bookId;
        }

        public void setBookId(String bookId) {
            this.bookId = bookId;
        }

        public String getBookLocation() {
            return bookLocation;
        }

        public void setBookLocation(String bookLocation) {
            this.bookLocation = bookLocation;
        }

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<List<brrowdata>> getBooklist() {
        return data;
    }

    public void setBooklist(List<List<brrowdata>> data) {
        this.data = data;
    }
}


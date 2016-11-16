package com.bitworkshop.litebookscholar.entity;

import java.util.List;

/**
 * Created by 78537 on 2016/10/27.
 */

public class One {

    /**
     * code : 200
     * message : 请求成功
     * data : [{"vol":"1","sentence":"啊啊啊啊啊","author":"oreo","url":"https://www.google.com.hk/images/branding/googlelogo/2x/googlelogo_color_272x92dp.png","date":"20161021"}]
     */

    private String code;
    private String message;
    /**
     * vol : 1
     * sentence : 啊啊啊啊啊
     * author : oreo
     * url : https://www.google.com.hk/images/branding/googlelogo/2x/googlelogo_color_272x92dp.png
     * date : 20161021
     */

    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private String vol;
        private String sentence;
        private String author;
        private String url;
        private String date;

        public String getVol() {
            return vol;
        }

        public void setVol(String vol) {
            this.vol = vol;
        }

        public String getSentence() {
            return sentence;
        }

        public void setSentence(String sentence) {
            this.sentence = sentence;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "vol='" + vol + '\'' +
                    ", sentence='" + sentence + '\'' +
                    ", author='" + author + '\'' +
                    ", url='" + url + '\'' +
                    ", date='" + date + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "One{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}

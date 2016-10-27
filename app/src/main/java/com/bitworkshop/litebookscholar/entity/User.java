package com.bitworkshop.litebookscholar.entity;

import org.litepal.annotation.Column;
import org.litepal.crud.DataSupport;

/**
 * Created by AidChow on 2016/10/17.
 */

public class User extends DataSupport {
    @Column(ignore = true)
    private String code;
    @Column(ignore = true)
    private String message;
    private String petname;
    private String url;
    @Column(unique = true)
    private String user;
    private String userPassword;
    private String userName;

    @Override
    public String toString() {
        return "User{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", petname='" + petname + '\'' +
                ", url='" + url + '\'' +
                ", user='" + user + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public String getPetname() {
        return petname;
    }

    public void setPetname(String petname) {
        this.petname = petname;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

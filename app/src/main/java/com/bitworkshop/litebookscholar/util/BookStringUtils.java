package com.bitworkshop.litebookscholar.util;

/**
 * Created by 78537 on 2016/10/20.
 */
public class BookStringUtils {

    public static String matchAuthor(String autorstr) {
        return autorstr
                .replaceAll("<span>.*?</span>", "")
                .replaceAll("<img.*?>.*?</a>", "")
                .split("<br>")[0].trim();
    }

    public static String matchPub(String pubstr) {
        return pubstr
                .replaceAll("<span>.*?</span>", "")
                .replaceAll("<img.*?>.*?</a>", "")
                .split("<br>")[1].trim().replace("&nbsp;", "");
    }

    public static String holdingBook(String holdStr) {
        return holdStr
                .split(" <br> ")[0].replace("馆藏复本：", "")
                .trim();
    }

    public static String canBoorowBook(String canBoorowStr) {
        return canBoorowStr
                .split(" <br> ")[1].replace("可借复本：", "")
                .trim();
    }
}

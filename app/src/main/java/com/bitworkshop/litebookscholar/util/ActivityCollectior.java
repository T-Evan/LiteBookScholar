package com.bitworkshop.litebookscholar.util;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * activity 管理容器
 * Created by AicChow on 2016/10/27.
 */

public class ActivityCollectior {
    private static List<AppCompatActivity> activities = new ArrayList<>();

    public static void addActivity(AppCompatActivity appCompatActivity) {
        activities.add(appCompatActivity);
    }

    public static void removeActivity(AppCompatActivity appCompatActivity) {
        activities.remove(appCompatActivity);
    }

    public static void finishAll() {
        for (AppCompatActivity appCompatActivity : activities) {
            if (!appCompatActivity.isFinishing()) {
                appCompatActivity.finish();
            }
        }
    }
}

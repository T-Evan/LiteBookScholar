package com.bitworkshop.litebookscholar;

import android.content.Context;
import android.content.pm.ApplicationInfo;

import org.litepal.LitePalApplication;

/**
 * Created by AidChow on 2016/10/16.
 */

public class App extends LitePalApplication {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        LitePalApplication.initialize(this);
    }

    public static Context getContext() {
        return context;
    }
}

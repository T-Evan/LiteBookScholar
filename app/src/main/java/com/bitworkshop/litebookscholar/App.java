package com.bitworkshop.litebookscholar;

import org.litepal.LitePalApplication;

/**
 * Created by AidChow on 2016/10/16.
 */

public class App extends LitePalApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        LitePalApplication.initialize(this);
    }
}

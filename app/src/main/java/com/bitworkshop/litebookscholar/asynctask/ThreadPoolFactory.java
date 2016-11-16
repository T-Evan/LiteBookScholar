package com.bitworkshop.litebookscholar.asynctask;

import java.util.concurrent.ThreadFactory;

/**
 * Created by 78537 on 2016/10/20.
 */

public class ThreadPoolFactory implements ThreadFactory {
    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r);
        t.setDaemon(true);
        return t;
    }
}

package com.bitworkshop.litebookscholar.asynctask;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.BufferedInputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.Callable;

/**
 * Created by 78537 on 2016/10/31.
 */

public class DownloadIImageFromHttp implements Callable<Bitmap> {
    private String imageUrl;

    public DownloadIImageFromHttp(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public Bitmap call() throws Exception {
        HttpURLConnection httpURLConnection = null;
        URL url = new URL(imageUrl);
        httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.setConnectTimeout(8000);
        httpURLConnection.setReadTimeout(8000);
        BufferedInputStream in = new BufferedInputStream(httpURLConnection.getInputStream());
        Bitmap bitmap = BitmapFactory.decodeStream(in);
        in.close();
        return bitmap;
    }
}

package com.bitworkshop.litebookscholar.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.Toast;

/**
 * Created by 78537 on 2016/10/17.
 */

public class MyToastUtils {
    private static Toast toast;

    @SuppressLint("ShowToast")
    public static void showToast(Context context, String content) {
        if (toast == null) {
            toast = Toast.makeText(context, content, Toast.LENGTH_SHORT);
        } else {
            toast.setText(content);
        }
        toast.show();
    }
}

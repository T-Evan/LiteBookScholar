package com.bitworkshop.litebookscholar.util;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.widget.EditText;

import com.bitworkshop.litebookscholar.App;
import com.bitworkshop.litebookscholar.R;

import java.util.Random;

/**
 * Created by AidChow on 2016/10/17.
 */

public class Utils {
    /**
     * 是否有网络检查
     *
     * @param context
     * @return
     */
    public static boolean isOnline(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }

    public static String editextUtiils(EditText editText) {
        String content = editText.getText().toString().trim();
        if (content.isEmpty()) {
            return "";
        } else {
            return content;
        }
    }

    public static float dp2px(Context context, float dp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                context.getResources().getDisplayMetrics());
    }

    /**
     * 像素转dp
     *
     * @param context
     * @param px
     * @return
     */
    public static int px2Dp(Context context, int px) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int dp = Math.round(px / (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
        return dp;
    }

    private static Resources res = App.getContext().getResources();
    private static TypedArray colorArray = res.obtainTypedArray(R.array.default_image_bg);

    public static int getRandomColors() {
        Random random = new Random();
        int index = random.nextInt(colorArray.length());
        System.out.println("index " + index);
        int id = colorArray.getResourceId(index, 0);
        System.out.println("id " + id);
        return id;
    }

}

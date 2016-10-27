package com.bitworkshop.litebookscholar.util;

import android.app.ProgressDialog;
import android.content.Context;
import android.widget.ProgressBar;

/**
 * Created by AidChow on 2016/10/26.
 */

public class ProgressDialogUtil {
    private static ProgressDialog progressDialog;


    public static void showProgressBar(Context context, String message) {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(context);
            progressDialog.setMessage(message);
            progressDialog.show();
        }
    }

    public static void hideProgressDiaglog() {
        if (progressDialog != null) {
            progressDialog.cancel();
            progressDialog = null;
        }
    }

}

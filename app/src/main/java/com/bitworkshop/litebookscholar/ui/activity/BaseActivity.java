package com.bitworkshop.litebookscholar.ui.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by AidChow on 2016/10/17.
 */

public class BaseActivity extends AppCompatActivity {
    protected static final int REQUEST_STORAGE_READ_ACCESS_PERMISSION = 101;
    protected static final int REQUEST_STORAGE_WRITE_ACCESS_PERMISSION = 102;
    protected static final int REQUEST_CAMERA_ACCESS_PERMISSION = 103;

    private AlertDialog mAlertDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mAlertDialog != null && mAlertDialog.isShowing()) {
            mAlertDialog.dismiss();
        }
    }

    protected void requestPermission(final String permission, String rationale, final int requestCode) {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
            showAlertDialog("需要权限", rationale, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    rebusesPermissions(permission, requestCode);
                }
            }, "确定", null, "取消");
        } else {
            rebusesPermissions(permission, requestCode);
        }
    }

    private void rebusesPermissions(String permission, int requestCode) {
        ActivityCompat.requestPermissions(BaseActivity.this, new String[]{permission}, requestCode);
    }

    /**
     * @param title                         标题
     * @param message                       消息
     * @param onPositiveButtonClickListener 监听积极事件
     * @param onNegativeButtonClickListener 监听消极事件
     * @param negativeText                  事件button消息
     */
    protected void showAlertDialog(String title,
                                   String message,
                                   DialogInterface.OnClickListener onPositiveButtonClickListener,
                                   String positiveText,
                                   DialogInterface.OnClickListener onNegativeButtonClickListener,
                                   String negativeText) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton(positiveText, onPositiveButtonClickListener);
        builder.setNeutralButton(negativeText, onNegativeButtonClickListener);
        mAlertDialog = builder.show();
    }
}

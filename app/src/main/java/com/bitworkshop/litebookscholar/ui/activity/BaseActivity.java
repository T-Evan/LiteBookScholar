package com.bitworkshop.litebookscholar.ui.activity;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.bitworkshop.litebookscholar.util.ActivityCollectior;

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
        ActivityCollectior.addActivity(this);
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
        builder.create();
        mAlertDialog = builder.show();
    }

    protected void setupToolbar(Toolbar toolbar, String title, boolean asHomeUp) {
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(asHomeUp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(asHomeUp);
    }
    /**
     * 从sharedPreferencs文件中获取账户信息
     * 作为关键字，去数据库中执行查询
     *
     * @return
     */
    protected String getUserAccount() {
        SharedPreferences sp = getSharedPreferences(SplashActivity.IS_LOGIN_FILE_NAME, 0);
        return sp.getString(LoginActivity.USER_ACCOUNT, "");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollectior.removeActivity(this);
    }
}

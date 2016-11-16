package com.bitworkshop.litebookscholar.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by 78537 on 2016/10/17.
 */

public class ConnectivityChangeReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        debugIntent(intent, "aid");
    }

    private void debugIntent(Intent intent, String aid) {
        Log.v(aid, "action: " + intent.getAction());
        Log.v(aid, "component: " + intent.getComponent());
        Bundle extras = intent.getExtras();
        if (extras != null) {
            for (String key : extras.keySet()) {
                Log.v(aid, "key [" + key + "]: " + extras.get(key));
            }
        } else {
            Log.v(aid, "no extras");
        }
    }
}

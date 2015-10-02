package com.xlab.detectinternetconnectionstatus;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by user on 2015/10/1
 */
public class ConnectivityChangeReceiver extends BroadcastReceiver{
    private final String TAG = getClass().getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        Log.i(TAG, "action: " + action);
        ConnectivityChangeReceiverObserver.getInstance().update();
    }
}

package com.xlab.detectinternetconnectionstatus;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by user on 2015/10/1
 */
public class ConnectionDetector {

    Context context;
    private ConnectivityManager connectivity;

    public ConnectionDetector(Context context){
        this.context = context;
        if (connectivity == null){
            connectivity =  (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        }
    }

    public boolean isConnectingToInternet(){
        boolean isConnected = false;
        if (connectivity != null){
            NetworkInfo activeNetwork = connectivity.getActiveNetworkInfo();
            isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        }
        return isConnected;
    }
}

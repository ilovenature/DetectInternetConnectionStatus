package com.xlab.detectinternetconnectionstatus;

import java.util.Observable;

/**
 * 使用了singleton pattern
 * Created by user on 2015/10/1.
 */
public class ConnectivityChangeReceiverObserver extends Observable{
    private static ConnectivityChangeReceiverObserver connectivityChangeReceiverObserver;
    private ConnectivityChangeReceiverObserver(){

    }

    public static synchronized ConnectivityChangeReceiverObserver getInstance(){
        if (connectivityChangeReceiverObserver == null){
            connectivityChangeReceiverObserver = new ConnectivityChangeReceiverObserver();
        }
        return connectivityChangeReceiverObserver;
    }
    public void update(){
        setChanged();
        notifyObservers();
    }

}

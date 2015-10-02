package com.xlab.detectinternetconnectionstatus;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.Observable;
import java.util.Observer;

/**
 * 此專案使用了observer pattern和singleton pattern
 *ConnectivityChangeReceiverObserver觀察ConnectivityChangeReceiver
 */
public class MainActivity extends AppCompatActivity implements Observer{
    private final String TAG = getClass().getSimpleName();

    private ConnectionDetector connectionDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        connectionDetector = new ConnectionDetector(this);
        Log.i(TAG, "connectivity: " + connectionDetector.isConnectingToInternet());

        ConnectivityChangeReceiverObserver.getInstance().addObserver(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        /*
        improve efficiency
        enable ConnectivityChangeReceiver to start to listen for connectivity changes
         */
        enableReceiver(this, ConnectivityChangeReceiver.class);
    }

    @Override
    protected void onStop() {
        super.onStop();
        /*
        improve efficiency
        disable ConnectivityChangeReceiver to stop listening for connectivity changes
         */
        disableReceiver(this, ConnectivityChangeReceiver.class);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void disableReceiver(Context context, Class<?> cls){
        ComponentName receiver = new ComponentName(context, cls);

        PackageManager pm = context.getPackageManager();

        pm.setComponentEnabledSetting(receiver,
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                PackageManager.DONT_KILL_APP);
    }

    private void enableReceiver(Context context, Class<?> cls){
        ComponentName receiver = new ComponentName(context, cls);

        PackageManager pm = context.getPackageManager();

        pm.setComponentEnabledSetting(receiver,
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP);
    }

    @Override
    public void update(Observable observable, Object data) {
        //observer pattern
        Log.i(TAG, "connectivity: " + connectionDetector.isConnectingToInternet());
    }
}

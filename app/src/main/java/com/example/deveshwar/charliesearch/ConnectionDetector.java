package com.example.deveshwar.charliesearch;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Devashish on 11/29/2015.
 */
public class ConnectionDetector {
    private Context context;
    public ConnectionDetector(Context context){
        this.context = context;
    }


    public static int getConnectivityStatus(Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService(context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if(null!=networkInfo){
            if (networkInfo.getType() == ConnectivityManager.TYPE_MOBILE)
                return 1;
            if(networkInfo.getType() == ConnectivityManager.TYPE_WIFI)
                return 1;
        }
        return 0;
    }


    public static int getConnectivityStatusInt(Context context){
        int connectedTo = getConnectivityStatus(context);
        int status = 0;
        if (connectedTo == 1){
            status = 1;
        }
        return status;
    }
}

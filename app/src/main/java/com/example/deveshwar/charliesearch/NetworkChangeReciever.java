package com.example.deveshwar.charliesearch;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.SearchManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.util.Log;

import com.example.deveshwar.charliesearch.data.Query;
import com.example.deveshwar.charliesearch.data.QueryDbHelper;

import java.util.List;

/**
 * Created by Devashish on 11/29/2015.
 */
public class NetworkChangeReciever extends BroadcastReceiver {

    int mId=1;
    //String connStatus = "Internet Connected";
    @Override
    public void onReceive(Context context, Intent intent) {
        QueryDbHelper queryDbHelper = new QueryDbHelper(context);
        int status = ConnectionDetector.getConnectivityStatusInt(context);
        if(status == 1) {
            Log.d("Reading: ", "Reading Query");
            List<Query> queryList = queryDbHelper.getAllquery();
            for(Query query:queryList){
                String log = "Id: "+ query.getId()+" ,Query: "+query.getQuery()+" ,Time: "+query.getTime();
                Log.d("Query: ",log);
            }
            Log.d("Reading: ", "Reading");
            int count = queryDbHelper.getQueryCount();
            if(count>0) {
                for (int i=count;i>0;i--) {
                    Query TheQuery = queryDbHelper.getQuery(i);
                    String log = "Id: " + TheQuery.getId() + ",Query:" + TheQuery.getQuery() + ",Time: " + TheQuery.getTime();
                    Log.d("Query: ", log);
                    NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context)
                            .setVibrate(new long[]{1000, 1000, 1000})
                            .setLights(Color.GREEN, 3000, 3000)
                            .setSmallIcon(R.mipmap.ic_launcher)
                            .setContentTitle("Charlie Search")
                            .setContentText("You searched for " + TheQuery.query)
                            .setSubText("Here are Your Results")
                            .setAutoCancel(true)
                            .setContentIntent(PendingIntent.getActivity(context, 0, new Intent(), 0));
                    NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
                    Intent NotiIntent = new Intent(Intent.ACTION_WEB_SEARCH);
                    String term = TheQuery.query;
                    NotiIntent.putExtra(SearchManager.QUERY, term);
                    TaskStackBuilder taskStackBuilder = TaskStackBuilder.create(context);
                    taskStackBuilder.addParentStack(MainActivity.class);
                    taskStackBuilder.addNextIntent(NotiIntent);
                    PendingIntent pendingIntent = taskStackBuilder.getPendingIntent(i, PendingIntent.FLAG_UPDATE_CURRENT);
                    mBuilder.setContentIntent(pendingIntent);
                    queryDbHelper.deleteQuery(count);
                    notificationManager.notify(mId, mBuilder.build());
                    mId++;
                }
            }else{
                System.exit(0);
            }
        }
    }
}

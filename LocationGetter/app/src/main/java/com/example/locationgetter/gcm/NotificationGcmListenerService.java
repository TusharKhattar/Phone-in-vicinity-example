package com.example.locationgetter.gcm;

import android.app.NotificationManager;
import android.location.Location;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.example.locationgetter.locationservice.FusedLocationService;
import com.example.locationgetter.R;
import com.example.locationgetter.helpers.Utilities;
import com.google.android.gms.gcm.GcmListenerService;

/**
 * Created by toothless on 24/10/15.
 */
public class NotificationGcmListenerService extends GcmListenerService implements FusedLocationService.LocationListener {

    public static final double VICINITY_DISTANCE=1;
    double updaterLatitude;
    double updaterLongitude;

    @Override
    public void onMessageReceived(String from, Bundle data) {
        Log.i("ppppppp", "here it is");
        showNotification("vjhvgcjcgjv");
        new FusedLocationService(getApplicationContext(),this);
    }

    public void showNotification(String message){

        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        NotificationCompat.Builder notificationBuilder=new NotificationCompat.Builder(this);
        notificationBuilder.setSmallIcon(R.drawable.common_signin_btn_icon_dark);
        notificationBuilder.setContentTitle("GCM MESSAGE").setContentText(message).setSound(defaultSoundUri);

        NotificationManager notificationManager= (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(1, notificationBuilder.build());

    }

    @Override
    public void onReceiveLocation(Location location) {
        Log.i("iiiiii","  "+location.getLatitude()+"  "+location.getLongitude());
        double userLatitude=location.getLatitude();
        double userLongitude=location.getLongitude();
        double dist= Utilities.distance(userLatitude, userLongitude, updaterLatitude, updaterLongitude);
        if(dist<VICINITY_DISTANCE){
            showNotification("Another phone is in vicinity!! " );
        }
    }

    @Override
    public void onLocationDisabled(String msg) {

    }

    @Override
    public void onConnectionFailed(String msg) {

    }
}

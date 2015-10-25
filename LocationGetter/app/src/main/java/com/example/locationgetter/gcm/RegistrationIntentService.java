package com.example.locationgetter.gcm;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;

import java.io.IOException;

/**
 * Created by toothless on 24/10/15.
 */
public class RegistrationIntentService extends IntentService {



    private static final String TAG = "ppppppppp";
    static final String SENDER_ID="243101062543";
    String token = null;

    public RegistrationIntentService() {
        super("RegistrationIntentService");
    }

    public RegistrationIntentService(String name) {
        super(name);
    }


    public String getRegToken(){
        SharedPreferences preferences = this.getSharedPreferences("gcm", Context.MODE_PRIVATE);
        return preferences.getString("gcm_reg", null);
    }

    public void saveRegToken(String str){
        SharedPreferences.Editor preferences = this.getSharedPreferences("gcm", Context.MODE_PRIVATE).edit();
        preferences.putString("gcm_reg", str);
        preferences.commit();
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.i(TAG,intent.toString());
        if (getRegToken() == null) {
            Log.i(TAG, "token is :" + token);
            final InstanceID instanceID = InstanceID.getInstance(this);
            Log.i(TAG, "instance is :" + instanceID);
            try {
                token = instanceID.getToken(SENDER_ID, GoogleCloudMessaging.INSTANCE_ID_SCOPE, null);
                Log.i(TAG, "token is :" + token);
            } catch (IOException e) {
                e.printStackTrace();
            }
            saveRegToken(token);
        }else{
            Log.i(TAG," stored token is :"+token);
        }
    }
}

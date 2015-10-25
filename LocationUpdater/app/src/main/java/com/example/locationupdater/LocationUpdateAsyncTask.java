package com.example.locationupdater;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by toothless on 25/10/15.
 */
public class LocationUpdateAsyncTask extends AsyncTask<String,Void,Void> {

    @Override
    protected Void doInBackground(String... params) {

        URL url=null;
        try {
            url=new URL(params[0]);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        HttpURLConnection httpURLConnection=null;
        try {
            httpURLConnection= (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            httpURLConnection.setRequestMethod("GET");
        } catch (ProtocolException e) {
            e.printStackTrace();
        }

        InputStream inputStream = null;
        try {
            inputStream=httpURLConnection.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

        StringBuffer jsonBuffer=new StringBuffer();
        if(inputStream==null){
            return null;
        }
        Scanner s=new Scanner(inputStream);
        while (s.hasNext()){
            jsonBuffer.append(s.nextLine());
        }
        Log.i("pppppp",jsonBuffer.toString());


        return null;
    }
}

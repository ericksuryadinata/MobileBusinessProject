package com.erick.uas.settingDirection;

import android.os.AsyncTask;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;

/**
 * @auth Priyanka
 */

public class GetDistanceData extends AsyncTask<Object,String,String> {

    GoogleMap mMap;
    String url;
    String googleDirectionsData;
    String duration, distance;
    LatLng latLng;
    TextView jarak, harga;
    @Override
    protected String doInBackground(Object... objects) {
        mMap = (GoogleMap)objects[0];
        url = (String)objects[1];
        jarak = (TextView)objects[2];
        harga = (TextView)objects[3];
        DownloadUrl downloadUrl = new DownloadUrl();
        try {
            googleDirectionsData = downloadUrl.readUrl(url);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return googleDirectionsData;
    }

    @Override
    protected void onPostExecute(String s) {

        String directionsList, asu;
        DataParser parser = new DataParser();
        directionsList = parser.parseDistance(s);
        String[] fak = directionsList.split(" ");
        double bla = Double.parseDouble(fak[0]) * 5000;
        asu = "Rp. "+String.valueOf(bla);
        displayDistance(directionsList, asu);
    }

    public void displayDistance(String directionsList, String price)
    {
        jarak.setText("Jarak : "+directionsList);
        harga.setText("Harga : "+price);

    }






}


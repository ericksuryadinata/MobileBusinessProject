package com.proesd.erick.mapsdirection;

import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import android.os.AsyncTask;

import org.w3c.dom.Document;

import java.util.ArrayList;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        //Map Direction
        LatLng untag = new LatLng(-7.298471, 112.766892);
        MarkerOptions untagsby = new MarkerOptions().position(untag).title("UNTAG");
//        untagsby.icon(BitmapDescriptorFactory.fromResource(R.drawable.mapmarker_flag_green));
        mMap.addMarker(untagsby);

        LatLng its = new LatLng(-7.282128, 112.795128);
        MarkerOptions itssby = new MarkerOptions().position(its).title("ITS");
//        itssby.icon(BitmapDescriptorFactory.fromResource(R.drawable.mapmarker_flag_violet));
        mMap.addMarker(itssby);

        getDirectionMap(untag, its);

        LatLng kopertis = new LatLng(-7.287659, 112.780988);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(kopertis));

        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(kopertis)
                .zoom(13)
                .tilt(0) // Memiringkan sudut kamera
                .build(); // Creates a CameraPosition from the builder
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }

    private void getDirectionMap(LatLng from, LatLng to) {
        LatLng fromto[] = { from, to };
        new LongOperation().execute(fromto);
    }

    private class LongOperation extends AsyncTask<LatLng, Void, Document> {
        @Override
        protected Document doInBackground(LatLng... params) {
            MapsDirection md = new MapsDirection();
            Document doc = md.getDocument(params[0], params[1],
                    MapsDirection.MODE_DRIVING);
            return doc;
        }

        @Override
        protected void onPostExecute(Document result) {
            setResult(result);
        }

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected void onProgressUpdate(Void... values) {
        }
    }

    public void setResult(Document doc) {
        MapsDirection md = new MapsDirection();
        int duration = md.getDurationValue(doc);
        String distance = md.getDistanceText(doc);
        String start_address = md.getStartAddress(doc);
        String copy_right = md.getCopyRights(doc);
        ArrayList<LatLng> directionPoint = md.getDirection(doc);
        PolylineOptions rectLine = new PolylineOptions().width(10).color(Color.BLUE);
        for (int i = 0; i < directionPoint.size(); i++) {
            rectLine.add(directionPoint.get(i));
        }
        mMap.addPolyline(rectLine);
    }
}

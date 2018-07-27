package com.proesd.erick.uasgojek;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,LocationListener {

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

        //Tampilan peta
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
//      mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
//      mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
//      mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            return;
        }
        mMap.setMyLocationEnabled(true);

//      mMap.setBuildingsEnabled(true);
//      mMap.setTrafficEnabled(true);
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setCompassEnabled(true);
        mMap.getUiSettings().setMapToolbarEnabled(true);
        mMap.getUiSettings().setMyLocationButtonEnabled(true);

        // Mendapatkan LocationManager object dari System Service LOCATION_SERVICE
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        // Membuat sebuah criteria object untuk mengambil provider
        Criteria criteria = new Criteria();

        // Mendapatkan nama dari provider
        String provider = locationManager.getBestProvider(criteria, true);

        // Mendapatkan Current Location
        Location location = locationManager.getLastKnownLocation(provider);

        if(location!=null){
            onLocationChanged(location);
        }

        locationManager.requestLocationUpdates(provider, 50000000, 0, (LocationListener) this);
    }

    @Override
    public void onLocationChanged(final Location location) {

        // Mendapatkan latitude dari current location
        final double latitude = location.getLatitude();

        // Mendapatkan longitude dari current location
        final double longitude = location.getLongitude();

        // Membuat sebuah LatLng object untuk current location
        LatLng latLng = new LatLng(latitude, longitude);

        // Menampilkan current location di Google Map
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));

        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(latLng)
                .zoom(15)
                .tilt(0) // Memiringkan sudut kamera
                .build(); // Creates a CameraPosition from the builder
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

        Button cekposisi = (Button) findViewById(R.id.cek);

        cekposisi.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Toast.makeText(getApplicationContext(), "Lokasi anda :\nLatitude: " + latitude + "\nLongitude: " + longitude, Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public void onProviderDisabled(String provider) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onProviderEnabled(String provider) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        // TODO Auto-generated method stub
    }
}
package com.example.makichanz.uas;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Camera;
import android.graphics.Color;
import android.icu.text.DecimalFormat;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.makichanz.uas.Command.coomand;
import com.example.makichanz.uas.adapter.MyAdapter;
import com.example.makichanz.uas.model.Lokasi;
import com.example.makichanz.uas.settingDirection.GetDirectionsData;
import com.example.makichanz.uas.settingDirection.GetDistanceData;
import com.github.rubensousa.floatingtoolbar.FloatingToolbar;
import com.github.ybq.android.spinkit.style.DoubleBounce;
import com.github.ybq.android.spinkit.style.Wave;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.ldoublem.loadingviewlib.view.LVCircularRing;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import pub.devrel.easypermissions.EasyPermissions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, LocationListener, EasyPermissions.PermissionCallbacks, EasyPermissions.RationaleCallbacks {

    private GoogleMap mMap;
    private LatLngBounds.Builder bounds;
    private Marker marker, marker_destination;
    private MyAdapter adapter;
    private LatLng my_location;
    private Location mLocation;
    private double end_latitude;
    private double end_longitide;
    private static final int RC_GPS_PERM = 1;
    private ListView listView;
    private Dialog dialog, dialog_destination;
    private static final String[] LOCATIONS = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};

    @BindView(R.id.current_position)
    CircleImageView btn_my_current_position;
    @BindView(R.id.floatingAction)
    FloatingActionButton mFloatingActionButton;
    @BindView(R.id.floatingToolbar)
    FloatingToolbar mFloatingToolbar;
    @BindView(R.id.ed_destination)
    EditText ed_destination;
    @BindView(R.id.distance)
    TextView distance;
    @BindView(R.id.harga)
    TextView harga;
    LocationManager locationManager;
    private GetDirectionsData getDirectionsData;

    public void init() {
        ButterKnife.bind(this);
        bounds = new LatLngBounds.Builder();
        allClickEvents();
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        getDirectionsData = new GetDirectionsData();
        GPSask();
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.loading);
        dialog.setCancelable(true);

    }

    public void allClickEvents() {
        ed_destination.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(ed_destination.getWindowToken(), 0);
                    ed_destination.setCursorVisible(false);
                }
                return false;
            }
        });

        ed_destination.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ed_destination.setCursorVisible(true);
                openListView();

            }
        });

        btn_my_current_position.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (marker != null) {
                    bounds.include(marker.getPosition());
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(my_location, (float) 19));
//                  mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds.build(), padding));
                }
            }
        });

    }

    public void makeDirection(){
        Object[] data_transfer = new Object[3];
        String url = getDirectionsUrl();
        data_transfer[0] = mMap;
        data_transfer[1] = url;
        data_transfer[2] = my_location;
        getDirectionsData = new GetDirectionsData();
        getDirectionsData.execute(data_transfer);
    }

    public void makeDistance(){
        Object[] data_transfer = new Object[4];
        String url = getDirectionsUrl();
        GetDistanceData getDistanceData = new GetDistanceData();
        data_transfer[0] = mMap;
        data_transfer[1] = url;
        data_transfer[2] = distance;
        data_transfer[3] = harga;
        getDistanceData.execute(data_transfer);
    }

    private String getDirectionsUrl()
    {
        StringBuilder googleDirectionsUrl = new StringBuilder("https://maps.googleapis.com/maps/api/directions/json?");
        googleDirectionsUrl.append("origin="+my_location.latitude+","+my_location.longitude);
        googleDirectionsUrl.append("&destination="+end_latitude+","+end_longitide);
        googleDirectionsUrl.append("&key="+"AIzaSyCAcfy-02UHSu2F6WeQ1rhQhkCr51eBL9g");

        return googleDirectionsUrl.toString();
    }

    public static double distance(double lat1, double lng1, double lat2, double lng2 ){
        double earthRadius = 6371000; //meters
        double dLat = Math.toRadians(lat2 - lat1);
        double dLng = Math.toRadians(lng2 - lng1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLng / 2) * Math.sin(dLng / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        float dist = (float) (earthRadius * c);
        dist = dist/1000;
        return dist;
//        Location A = new Location("A");
//        Location B = new Location("B");
//        A.setLatitude(lat1);
//        A.setLongitude(lng1);
//        B.setLatitude(lat2);
//        B.setLongitude(lng2);
//
//        double dist = A.distanceTo(B);
//        DecimalFormat df = new DecimalFormat("#.#");
//        dist = Double.valueOf(df.format(dist));
//        return dist/1000;
    }

    public void openListView(){
        dialog_destination = new Dialog(this,R.style.Theme_Dialog);
        dialog_destination.setContentView(R.layout.dialog_destination);
        dialog_destination.setCancelable(true);
        dialog.show();

        new coomand().getAllData(new coomand.Response() {
            @Override
            public void sukses(final ArrayList<Lokasi>lokasis) {
                dialog.dismiss();
                dialog_destination.show();
                adapter = new MyAdapter(MapsActivity.this,lokasis);
                listView = (ListView)dialog_destination.findViewById(R.id.listview);
                listView.setAdapter(adapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        ed_destination.setText(lokasis.get(position).getNama_orang());
                        end_latitude = Double.parseDouble(lokasis.get(position).getLatitude());
                        end_longitide = Double.parseDouble(lokasis.get(position).getLongitude());
                        dialog_destination.dismiss();
                        LatLng latLng_destination = new LatLng(end_latitude,end_longitide);
                        if(marker_destination!=null){
                            getDirectionsData.setOnClickDirectionsListener(new GetDirectionsData.onClickDirections() {
                                @Override
                                public void OnClick() {
                                    Toast.makeText(getApplicationContext(),"Directions has been removed",Toast.LENGTH_SHORT).show();
                                }
                            });
                            marker_destination.remove();
                        }
                        marker_destination = mMap.addMarker(new MarkerOptions().position(latLng_destination).title(lokasis.get(position).getNama_orang()));
                        bounds.include(marker_destination.getPosition());
                        mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds.build(), 150));
                    }
                });
            }
            @Override
            public void gagal() {
                dialog.dismiss();
                Toast.makeText(MapsActivity.this,"Gagal ambil data",Toast.LENGTH_SHORT).show();
            }
        });

    }

    private boolean hasGPSPermission() {
        return EasyPermissions.hasPermissions(this, LOCATIONS);
    }


    public void GPSask() {
        if (hasGPSPermission()) {
            Toast.makeText(this, "Permission is granted", Toast.LENGTH_SHORT).show();
        } else {
            EasyPermissions.requestPermissions(this, "LOKASI", RC_GPS_PERM, LOCATIONS);
        }
    }

    private void makeFloatingToolbar() {
        mFloatingToolbar.attachFab(mFloatingActionButton);
        mFloatingToolbar.setClickListener(new FloatingToolbar.ItemClickListener() {
            @Override
            public void onItemClick(MenuItem item) {
                if (item.getTitle().equals("Add_location")) {
                    Toast.makeText(MapsActivity.this, "Tambah Lokasi", Toast.LENGTH_SHORT).show();
                    final Dialog dialog_option = new Dialog(MapsActivity.this, R.style.Theme_Dialog);
                    dialog_option.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog_option.setContentView(R.layout.dialog_options);
                    dialog_option.show();
                    TextView lokasi_sekarang_button, lokasi_lain_button;
                    lokasi_sekarang_button= (TextView)dialog_option.findViewById(R.id.lokasi_sekarang);
                    lokasi_lain_button= (TextView)dialog_option.findViewById(R.id.lokasi_lain);
                    lokasi_sekarang_button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog_option.dismiss();
                            new coomand().dialog_add_lokasi(MapsActivity.this, my_location.latitude, my_location.longitude,new coomand.Response_dialog_add() {
                                @Override
                                public void sukses(String nama) {
                                    dialog.show();
                                    new coomand().insertData(nama,my_location, new coomand.Response_insert() {
                                        @Override
                                        public void sukses() {
                                            dialog.dismiss();

                                            Toast.makeText(MapsActivity.this,"Berhasil menambah data"+my_location.latitude+" "+my_location.longitude,Toast.LENGTH_SHORT);

                                        }

                                        @Override
                                        public void gagal() {

                                        }
                                    });
                                }

                                @Override
                                public void gagal() {

                                }
                            });


                        }
                    });

                    lokasi_lain_button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog_option.dismiss();
                        }
                    });

                }

                if(item.getTitle().equals("Make_route")){
                    if(ed_destination.getText().equals("")){
                        Toast.makeText(MapsActivity.this,"Fill the Destination field !",Toast.LENGTH_SHORT);
                    }else{
                        makeDirection();
                        makeDistance();
                        //double hasil = distance(my_location.latitude,my_location.longitude,end_latitude,end_longitide);
                        //distance.setText(String.valueOf(hasil)+ " Km");
                    }

                }
            }

            @Override
            public void onItemLongClick(MenuItem item) {

            }
        });
    }

    public void createMarker(Location location){
        my_location = new LatLng(location.getLatitude(),location.getLongitude());
        marker = mMap.addMarker(new MarkerOptions().position(my_location).title(String.valueOf(location.getLatitude()) +" "+ String.valueOf(location.getLongitude()+" L")));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);
//        locationManager = (LocationManager) this.getSystemService(LOCATION_SERVICE);
//        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 200, 100, this);
//        mLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
//        if(mLocation!=null){
//            mLocation = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
//        }
//        if(marker==null) {
//            createMarker(mLocation);
//            bounds.include(marker.getPosition());
//            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(marker.getPosition().latitude,marker.getPosition().longitude), 100));
//        }
//        else {
//            marker.remove();
//            createMarker(mLocation);
//        }


        mMap.setOnMyLocationChangeListener(new GoogleMap.OnMyLocationChangeListener() {
            @Override
            public void onMyLocationChange(Location location){
                if(marker==null) {
                    createMarker(location);
                    bounds.include(marker.getPosition());
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(my_location, 100));
                }
                else {
                    marker.remove();
                    createMarker(location);
                }
            }
        });
        // Add a marker in Sydney and move the camera
        //LatLng aa = new LatLng(-1,101);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        init();
        makeFloatingToolbar();

    }


    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {

    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {

    }

    @Override
    public void onRationaleAccepted(int requestCode) {

    }

    @Override
    public void onRationaleDenied(int requestCode) {

    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
}

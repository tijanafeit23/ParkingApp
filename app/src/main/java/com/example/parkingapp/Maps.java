package com.example.parkingapp;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.Scanner;

public class Maps extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMapLoadedCallback, GoogleMap.OnMarkerClickListener {

    private GoogleMap mMap = null;
    private LatLng myLoc;
    private static final int req_code = 1;
    String grad;
    String lokacija;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mMap = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mMap.getMapAsync(this);

        Intent intent = getIntent();
        grad = intent.getStringExtra("grad");
        lokacija = intent.getStringExtra("lokacija");
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
    @Override
    public void onMapReady(GoogleMap mMap) {
        this.mMap = mMap;
        mMap.setOnMapLoadedCallback(this);
    }

    @Override
    public void onMapLoaded() {
        readCities();
        mMap.setOnMarkerClickListener(this);
        myLoc = getMyLocation();
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myLoc, 15.0f));
    }

    public void readCities() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String data = prefs.getString("grad", "no id");
        Scanner scan = new Scanner(getResources().openRawResource(R.raw.koordinati));
        while (scan.hasNextLine()) {
            String name = scan.nextLine();
            if(name.isEmpty()) break;
            if(name.equals(data)) {
                double lat = Double.parseDouble(scan.nextLine());
                double lng = Double.parseDouble(scan.nextLine());
                mMap.addMarker(new MarkerOptions().position(new LatLng(lat, lng)).title(name));
                break;
            }
        }
    }

    private LatLng getMyLocation() {
        LocationManager locMan = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[] { Manifest.permission.ACCESS_FINE_LOCATION }, req_code);
        }
        Location loc = locMan.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if(loc == null) {
            loc = locMan.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        }
        if(loc == null) {
            loc = locMan.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);
        }
        if(loc == null) {
            return null;
        }
        else {
            double myLat = loc.getLatitude();
            double myLng = loc.getLongitude();
            mMap.setMyLocationEnabled(true);
            return new LatLng(myLat, myLng);
        }
    }

    public void onRequestPermissionsResult(int reqCode, String permissions[], int[] grantResults) {}

    public boolean onMarkerClick(Marker marker) {
        if(myLoc != null) {
            LatLng markerLL = marker.getPosition();
            mMap.addPolyline(new PolylineOptions().add(myLoc).add(markerLL));
            return true;
        }
        else { return false; }
    }
}
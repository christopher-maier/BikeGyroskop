package com.example.christopher.bikegyroskop;

import android.arch.persistence.room.Room;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorListener;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.hardware.SensorManager;
import android.hardware.SensorEventListener;
import android.hardware.Sensor;
import android.hardware.SensorEvent;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import android.database.sqlite.*;
import android.util.Log;

import java.util.Calendar;

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

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    public void fillDummyData(){
        AppDatabase db;
        db = AppDatabase.getInMemoryDatabase(getApplicationContext());
        TiltAtLocation i = new TiltAtLocation();
        Calendar cal = Calendar.getInstance ();
        i.setId(cal);
        i.setLatitude(34);
        i.setLongitude(13);
        i.setX(133);
        i.setY(1224);
        db.TiltAtLocationDao().insertAll();
    }
}

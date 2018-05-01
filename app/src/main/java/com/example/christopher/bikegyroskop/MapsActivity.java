package com.example.christopher.bikegyroskop;

import android.Manifest;
import android.arch.persistence.room.Room;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorListener;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.nfc.Tag;
import android.support.v4.app.ActivityCompat;
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
import android.widget.Toast;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private SensorManager sensorManager;
    private Sensor gyroscopeSensor;
    private SensorEventListener gyroscopeListener;
    private LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        AppDatabase db;
        db = AppDatabase.getInMemoryDatabase(getApplicationContext());
        final TiltAtLocation i = new TiltAtLocation();

        gyroscopeSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        if (gyroscopeSensor != null){
            sensorManager.registerListener((SensorEventListener) MapsActivity.this, gyroscopeSensor, SensorManager.SENSOR_DELAY_NORMAL);

        }

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
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
        //pruefen ob der Networkprovider vorhanden ist
        if (locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)){
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 0, new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    //den Laengengrad holen
                    double longitude = location.getLongitude();
                    //den Breitengrad holen
                    double latitude = location.getLatitude();
                    //instanzieren der Latlng Klasse
                    LatLng latLng = new LatLng(latitude, longitude);
                    //instanzieren der Geocoder Klasse
                    Geocoder geocoder = new Geocoder(getApplicationContext());
                    try {
                        List<Address> addressList = geocoder.getFromLocation(latitude, longitude, 1);
                        String str = addressList.get(0).getLocality() + ", ";
                        str += addressList.get(0).getCountryName();
                        mMap.addMarker(new MarkerOptions().position(latLng).title(str));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10.2f));
                        i.setLatitude(latitude);
                        i.setLongitude(longitude);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
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
            });
        } else if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    //den Laengengrad holen
                    double longitude = location.getLongitude();
                    //den Breitengrad holen
                    double latitude = location.getLatitude();
                    //instanzieren der Latlng Klasse
                    LatLng latLng = new LatLng(latitude, longitude);
                    //instanzieren der Geocoder Klasse
                    Geocoder geocoder = new Geocoder(getApplicationContext());
                    try {
                        List<Address> addressList = geocoder.getFromLocation(latitude, longitude, 1);
                        String str = addressList.get(0).getLocality() + ", ";
                        str += addressList.get(0).getCountryName();
                        mMap.addMarker(new MarkerOptions().position(latLng).title(str));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10.2f));
                        i.setLatitude(latitude);
                        i.setLongitude(longitude);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
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
            });
        }

        /**sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
         gyroscopeSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

         if (gyroscopeSensor == null){
         Toast.makeText(this, "The device has no gyroscope", Toast.LENGTH_SHORT).show();
         finish();
         }


         gyroscopeListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event){
        if(event.values[2] > 0.5f){
        getWindow().getDecorView().setBackgroundColor(Color.BLUE);
        }else if (event.values[2] < -0.5f){
        getWindow().getDecorView().setBackgroundColor(Color.RED);
        }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {


        };**/
    }
    /** @Override
    protected void onResume() {
    super.onResume();
    sensorManager.registerListener(gyroscopeListener, gyroscopeSensor, sensorManager.SENSOR_DELAY_FASTEST);
    }

     @Override
     protected void onPause() {
     super.onPause();
     sensorManager.unregisterListener(gyroscopeListener);
     }**/


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


        //  LatLng sydney = new LatLng(-34, 151);
        // mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
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
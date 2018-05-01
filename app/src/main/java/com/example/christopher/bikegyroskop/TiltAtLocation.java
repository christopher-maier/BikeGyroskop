package com.example.christopher.bikegyroskop;

import android.arch.persistence.room.*;

import java.util.Calendar;

@Entity
public class TiltAtLocation{ //Zeit als PrimaryKey
        @PrimaryKey
        private Calendar id ;

        @ColumnInfo(name = "longitude") //double
        private double longitude;

        @ColumnInfo(name = "latitude") //double
        private double latitude;

        @ColumnInfo(name = "x-axis")
        private double x;

        @ColumnInfo(name = "y-axis")
         private double y;


    public Calendar getId() {
        return id;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setId(Calendar id) {
        this.id = id;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }
}

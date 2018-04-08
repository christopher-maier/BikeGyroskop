package com.example.christopher.bikegyroskop;

import android.arch.persistence.room.*;

@Entity
public class TiltAtLocation{
        @PrimaryKey
        private int id;

        @ColumnInfo(name = "longitude")
        private int longitude;

        @ColumnInfo(name = "latitude")
        private int latitude;

        @ColumnInfo(name = "x-axis")
        private double x;

        @ColumnInfo(name = "y-axis")
         private double y;


    public int getId() {
        return id;
    }

    public int getLongitude() {
        return longitude;
    }

    public int getLatitude() {
        return latitude;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLongitude(int longitude) {
        this.longitude = longitude;
    }

    public void setLatitude(int latitude) {
        this.latitude = latitude;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }
}

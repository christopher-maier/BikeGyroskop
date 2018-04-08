package com.example.christopher.bikegyroskop;

import android.arch.persistence.room.*;

import com.example.christopher.bikegyroskop.TiltAtLocation;

import java.util.List;

@Dao
public interface TiltAtLocationDao{
        @Query("SELECT * FROM TiltAtLocation")
        List<TiltAtLocation> getAll();

        @Query("SELECT * FROM TiltAtLocation WHERE id IN (:userIds)")
        List<TiltAtLocation> loadAllByIds(int[] userIds);

        @Insert
        void insertAll(TiltAtLocation... table);

        @Delete
        void delete(TiltAtLocation table);
}

package com.safewalk.coimbatore.pro;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface JunctionDao {
    @Insert
    void insert(Junction junction);

    @Query("SELECT * FROM junctions ORDER BY name ASC")
    LiveData<List<Junction>> getAllJunctions();

    @Query("SELECT COUNT(*) FROM junctions")
    int getCount();

    @Query("DELETE FROM junctions")
    void deleteAll();
}

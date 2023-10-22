package com.example.davidobst_cis3334_project1;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface eventDao {
    @Insert
    void insert(Event event);

    @Delete
    void delete(Event event);

    @Query("SELECT * FROM Event")
    LiveData<List<Event>> getAll();



}

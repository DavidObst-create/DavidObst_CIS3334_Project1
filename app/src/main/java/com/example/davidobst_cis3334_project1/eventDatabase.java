package com.example.davidobst_cis3334_project1;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Event.class}, version = 1, exportSchema = false)
public abstract class eventDatabase extends RoomDatabase {

    public abstract eventDao eventDao();

    private static volatile eventDatabase INSTANCE;

    private static final int NUMBER_OF_THREADS = 4;

    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static eventDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (eventDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    eventDatabase.class, "event_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}

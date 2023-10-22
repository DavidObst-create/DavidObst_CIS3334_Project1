package com.example.davidobst_cis3334_project1;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class eventRepository {

    private eventDao eventDao;

    private LiveData<List<Event>> eventList;

    eventRepository(Application applicaiton) {
        eventDatabase db = eventDatabase.getDatabase(applicaiton);
        eventDao = db.eventDao();
        eventList = eventDao.getAll();
    }

    LiveData<List<Event>> getAllEvents() {
        eventDatabase.databaseWriteExecutor.execute(() -> {
            eventList = eventDao.getAll();
        });
        return eventList;
    }

    void insert(Event event) {
        eventDatabase.databaseWriteExecutor.execute(() -> {
            eventDao.insert(event);
        });
    }
}

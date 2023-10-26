package com.example.davidobst_cis3334_project1;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class EventRepository {

    private EventDao eventDao;

    private LiveData<List<Event>> eventList;

    EventRepository(Application applicaiton) {
        EventDatabase db = EventDatabase.getDatabase(applicaiton);
        eventDao = db.eventDao();
        eventList = eventDao.getAll();
    }

    LiveData<List<Event>> getAllEvents() {
        EventDatabase.databaseWriteExecutor.execute(() -> {
            eventList = eventDao.getAll();
        });
        return eventList;
    }

    void insert(Event event) {
        EventDatabase.databaseWriteExecutor.execute(() -> {
            eventDao.insert(event);
        });
    }
}

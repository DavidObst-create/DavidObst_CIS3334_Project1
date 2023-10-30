package com.example.davidobst_cis3334_project1;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class MainViewModel extends AndroidViewModel {

    private EventRepository eventRepository;

    private LiveData<List<Event>> eventList;

    public MainViewModel(Application application) {
        super(application);
        eventRepository = new EventRepository(application);
        eventList = eventRepository.getAllEvents();
    }

    public void insert(String description, String date, String start, String end) {
        Event event = new Event(description, date, start, end);
        eventRepository.insert(event);
    }

    public LiveData<List<Event>> getAllEvents() {
        eventList = eventRepository.getAllEvents();
        return eventList;
    }

    public void delete(Event event) {
        eventRepository.delete(event);
    }

    public boolean equals(Event event) {
        if (event.getEventDescription().equals(this)
                && event.getEventStart().equals(this)
                && event.getEventEnd().equals(this)
                && event.getEventDate().equals(this)) {
            return true;
        }
        else {
            return false;
        }
    }




}

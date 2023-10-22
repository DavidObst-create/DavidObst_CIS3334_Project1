package com.example.davidobst_cis3334_project1;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class MainViewModel extends AndroidViewModel {

    private eventRepository eventRepository;

    private LiveData<List<Event>> eventList;

    public MainViewModel(Application application) {
        super(application);
        eventRepository = new eventRepository(application);
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

}

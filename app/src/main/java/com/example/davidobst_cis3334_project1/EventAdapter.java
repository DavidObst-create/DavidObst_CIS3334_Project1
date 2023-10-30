package com.example.davidobst_cis3334_project1;

import android.app.Application;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventViewHolder> {

    private List<Event> eventList;
    private Context context;

    public EventAdapter(Application application, MainViewModel mainViewModel) {
        this.context = application;

        eventList = new ArrayList<>();

        //test data
        //eventList.add(new Event("Class", "10/20/2020", "12:00", "13:00"));
        //eventList.add(new Event("Work", "10/21/2020", "14:00", "15:00"));
        //eventList.add(new Event("Sleep", "10/22/2020", "16:00", "17:00"));
        //eventList.add(new Event("Eat", "10/23/2020", "18:00", "19:00"));
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.event_row, parent, false);
        EventViewHolder eventViewHolder = new EventViewHolder(view);
        return new EventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
        Event event = eventList.get(position);
        holder.textViewEventDescription.setText(event.getEventDescription());
        holder.textViewEventDate.setText(event.getEventDate());
        holder.textViewEventStart.setText(event.getEventStart());
        holder.textViewEventEnd.setText(event.getEventEnd());
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }

    public void addEvent(String description, String date, String start, String end) {
        Event event = new Event(description, date, start, end);
        eventList.add(event);
        notifyDataSetChanged();
    }

    public void setEventList(List<Event> eventList) {
        this.eventList = eventList;
        notifyDataSetChanged();
    }

    public void addAllEvents(List<Event> events) {
        eventList.addAll(events);
        notifyDataSetChanged();
    }
}

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

public class eventAdapter extends RecyclerView.Adapter<eventViewHolder> {

    private List<Event> eventList = new ArrayList<>();

    private Context context;

    public eventAdapter(Application application) {
        this.context = application;

        eventList.add(new Event("Class", "10/20/2020", "12:00", "13:00"));
        eventList.add(new Event("Work", "10/21/2020", "14:00", "15:00"));
        eventList.add(new Event("Sleep", "10/22/2020", "16:00", "17:00"));
        eventList.add(new Event("Eat", "10/23/2020", "18:00", "19:00"));
    }

    @NonNull
    @Override
    public eventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.event_row, parent, false);
        eventViewHolder holder = new eventViewHolder(view);
        return new eventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull eventViewHolder holder, int position) {
        Event event = eventList.get(position);
        holder.textViewEventDescription.setText(event.getEventDescription());
        holder.textViewEventDate.setText(event.getEventDate());
        holder.textViewEventStart.setText(event.getEventStart());
        holder.textViewEventEnd.setText(event.getEventEnd());
    }

    @Override
    public int getItemCount() {return eventList.size(); }
}

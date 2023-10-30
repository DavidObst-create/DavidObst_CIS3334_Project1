package com.example.davidobst_cis3334_project1;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Event {
    //@NonNull
    //@ColumnInfo(name = "eventDescription")
    private String eventDescription;
    //@NonNull
    //@ColumnInfo(name = "eventDate")
    private String eventDate;
    //@NonNull
    //@ColumnInfo(name = "eventStart")
    private String eventStart;
    //@NonNull
    //@ColumnInfo(name = "eventEnd")
    private String eventEnd;

    @PrimaryKey(autoGenerate = true)
    public Integer id; // primary key

    public Event(String eventDescription, String eventDate, String eventStart, String eventEnd) {
        this.eventDescription = eventDescription;
        this.eventDate = eventDate;
        this.eventStart = eventStart;
        this.eventEnd = eventEnd;
        Log.d("CIS 3334","Event constructor has been called");
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public String getEventStart() {
        return eventStart;
    }

    public void setEventStart(String eventStart) {
        this.eventStart = eventStart;
    }

    public String getEventEnd() {
        return eventEnd;
    }

    public void setEventEnd(String eventEnd) {
        this.eventEnd = eventEnd;
    }
}

package com.example.davidobst_cis3334_project1;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Event {
    private String eventDescription;
    private String eventDate;
    private String eventStart;
    private String eventEnd;

    @PrimaryKey(autoGenerate = true)
    public Integer id; // primary key

    public Event(String eventDescription, String eventDate, String eventStart, String eventEnd) {
        this.eventDescription = eventDescription;
        this.eventDate = eventDate;
        this.eventStart = eventStart;
        this.eventEnd = eventEnd;
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

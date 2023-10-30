package com.example.davidobst_cis3334_project1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText eventDescription;
    EditText eventDate;
    EditText eventStart;
    EditText eventEnd;
    Button buttonAdd;
    Button buttonRemove;
    ImageButton buttonSettings;
    MainViewModel mainViewModel;
    RecyclerView recyclerView;
    EventAdapter adapter = new EventAdapter(getApplication(), mainViewModel);

    List<Event> eventList = new ArrayList<>();
    //LiveData<List<Event>> eventListLiveData;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);

        // Get the references to the widgets
        eventDescription = (EditText) findViewById(R.id.editTextDescription);
        eventDate = (EditText) findViewById(R.id.editTextEventDate);
        eventStart = (EditText) findViewById(R.id.editTimeStart);
        eventEnd = (EditText) findViewById(R.id.editTimeEnd);
        buttonAdd = (Button) findViewById(R.id.buttonAdd);
        buttonRemove = (Button) findViewById(R.id.buttonRemove);
        buttonSettings = (ImageButton) findViewById(R.id.buttonSettings);

        //eventListLiveData = mainViewModel.getAllEvents();




        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        setupAddEventButton();
        setupRemoveEventButton();
        setupEditTextDate();
        setupEditTextDescription();
        setupEditStartTime();
        setupEditEndTime();
        setupLiveDataObserver();

        /*
        eventListLiveData.observe(this, new Observer<List<Event>>() {
            @Override
            public void onChanged(List<Event> myItens) {
                adapter.setEventList(myItens);
            }
        });

         */
    }



    private void setupAddEventButton() {
        buttonAdd = (Button) findViewById(R.id.buttonAdd);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Log.d("CIS 3334", "Add Event Button Clicked");

               try {
                   String description = eventDescription.getText().toString();
                   String date = eventDate.getText().toString();
                   String start = eventStart.getText().toString();
                   String end = eventEnd.getText().toString();

                   //add event to database
                   mainViewModel.insert(description, date, start, end);

                   //empty the editTextViews and set add button to false
                   eventDescription.setText("");
                   eventDate.setText("");
                   eventStart.setText("");
                   eventEnd.setText("");
                   //buttonAdd.setEnabled(false);
               } catch (Exception e) {
                     Log.d("CIS 3334", "Error: " + e.getMessage());
               }




           }
        });
    }

    private void setupRemoveEventButton() {
        buttonRemove = (Button) findViewById(R.id.buttonRemove);

        buttonRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("CIS 3334", "Remove Event Button Clicked");
                //eventList to the value of live data
                //eventList = eventListLiveData.getValue();
                Log.d("CIS 3334", "Event List Size: " + eventList.size());
                Log.d("CIS 3334", "Event List: " + eventList.get(0).getEventDescription());

                //find and store index of event from eventList where the date, start, and end match
                for (Event event: eventList) {
                    if (event.getEventDate().equals(eventDate.getText().toString())
                            && event.getEventStart().equals(eventStart.getText().toString())
                            && event.getEventEnd().equals(eventEnd.getText().toString())) {
                        //remove event from eventList
                        mainViewModel.delete(event);
                    }
                }
                //notify the adapter that the data has changed
                recyclerView.getAdapter().notifyDataSetChanged();

            }
        });
    }

    private void setupEditTextDescription() {
        eventDescription = (EditText) findViewById(R.id.editTextDescription);
        eventDescription.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                Log.d("CIS 3334", "Description Entered: " + eventDescription.getText().toString());
                for (Event event : eventList) {
                    if (event.getEventDescription().equals(eventDescription.getText().toString())) {
                        eventDate.setText(event.getEventDescription());
                        eventStart.setText(event.getEventStart());
                        eventEnd.setText(event.getEventEnd());
                    }

                }
                return false;
            }
        });
    }

    private void setupEditTextDate() {
        eventDate = (EditText) findViewById(R.id.editTextEventDate);
        eventDate.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                Log.d("CIS 3334", "Date Entered: " + eventDate.getText().toString());
                for (Event event : eventList) {
                    if (event.getEventDate().equals(eventDate.getText().toString())) {
                        eventDescription.setText(event.getEventDescription());
                        eventStart.setText(event.getEventStart());
                        eventEnd.setText(event.getEventEnd());
                    }

                }
                return false;
            }
        });
    }

    private void setupEditStartTime() {
        eventStart = (EditText) findViewById(R.id.editTimeStart);
        eventStart.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                Log.d("CIS 3334", "Start Time Entered: " + eventStart.getText().toString());
                for (Event event : eventList) {
                    if (event.getEventDate().equals(eventStart.getText().toString())) {
                        eventDate.setText(event.getEventDate());
                        eventDescription.setText(event.getEventDescription());
                        eventEnd.setText(event.getEventEnd());
                    }

                }
                return false;
            }
        });
    }

    private void setupEditEndTime() {
        eventEnd = (EditText) findViewById(R.id.editTimeEnd);
        eventEnd.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                Log.d("CIS 3334", "End Time Entered: " + eventEnd.getText().toString());
                for (Event event : eventList) {
                    if (event.getEventDate().equals(eventEnd.getText().toString())) {
                        eventDate.setText(event.getEventDate());
                        eventDescription.setText(event.getEventDescription());
                        eventStart.setText(event.getEventEnd());
                    }

                }
                return false;
            }
        });
    }


    private void setupLiveDataObserver () {

        mainViewModel.getAllEvents().observe(this, new Observer<List<Event>>() {
            @Override
            public void onChanged(@Nullable List<Event> events) {
                Log.d("CIS 3334", "On Change Called");
                eventList = events;
                adapter.setEventList(eventList);
                Log.d("CIS 3334", "Event List Size: " + eventList.size());
                recyclerView.getAdapter().notifyDataSetChanged();
            }
        });
    }
}

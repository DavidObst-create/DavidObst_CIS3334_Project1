package com.example.davidobst_cis3334_project1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
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
    Button buttonEdit;
    ImageButton buttonSettings;
    MainViewModel mainViewModel;
    RecyclerView recyclerView;

    ArrayList<Event> eventList = new ArrayList<>();



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
        buttonEdit = (Button) findViewById(R.id.buttonEdit);
        buttonSettings = (ImageButton) findViewById(R.id.buttonSettings);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        EventAdapter adapter = new EventAdapter(getApplication(), mainViewModel);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        setupAddEventButton();

        setupEditTextDate();
        setupEditTextDescription();
        setupEditStartTime();
        setupEditEndTime();


        /*
        //Check if editTextViews are empty
        if (eventDescription.getText().toString().isEmpty()
                && eventDate.getText().toString().isEmpty()
                && eventStart.getText().toString().isEmpty()
                && eventEnd.getText().toString().isEmpty()) {
            buttonAdd.setEnabled(false);
            buttonEdit.setEnabled(false);
        } else {
            buttonAdd.setEnabled(true);
            buttonEdit.setEnabled(true);
        }
        */
    }

    private void setupAddEventButton() {
        buttonAdd = (Button) findViewById(R.id.buttonAdd);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Log.d("CIS 3334", "Add Event Button Clicked");

               //make sure the editTextViews are not empty
               if (eventDescription.getText() != null &&
                       eventDate.getText() != null &&
                       eventStart.getText() != null &&
                       eventEnd.getText() != null) {


                   //add the event to the database
                   mainViewModel.insert(
                           eventDescription.getText().toString(),
                           eventDate.getText().toString(),
                           eventStart.getText().toString(),
                           eventEnd.getText().toString()
                   );
                   //empty the editTextViews and set add button to false
                   eventDescription.setText("");
                   eventDate.setText("");
                   eventStart.setText("");
                   eventEnd.setText("");
                   buttonAdd.setEnabled(false);
               }
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
                eventList.clear();
                eventList.addAll(events);
                recyclerView.getAdapter().notifyDataSetChanged();
            }
        });
    }
}

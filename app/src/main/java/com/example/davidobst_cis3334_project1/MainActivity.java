package com.example.davidobst_cis3334_project1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText eventDescription;
    EditText eventDate;
    EditText eventStart;
    EditText eventEnd;
    Button buttonAdd;
    Button buttonEdit;
    RecyclerView recyclerView;

    ArrayList<Event> eventList = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get the references to the widgets
        eventDescription = (EditText) findViewById(R.id.editTextDescription);
        eventDate = (EditText) findViewById(R.id.editTextEventDate);
        eventStart = (EditText) findViewById(R.id.editTimeStart);
        eventEnd = (EditText) findViewById(R.id.editTimeEnd);
        buttonAdd = (Button) findViewById(R.id.buttonAdd);
        buttonEdit = (Button) findViewById(R.id.buttonEdit);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        eventAdapter adapter = new eventAdapter(getApplication());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
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



    private void setupEditText() {
        eventDescription = (EditText) findViewById(R.id.editTextDescription);
        eventDate = (EditText) findViewById(R.id.editTextEventDate);
        eventStart = (EditText) findViewById(R.id.editTimeStart);
        eventEnd = (EditText) findViewById(R.id.editTimeEnd);

        eventDate.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
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


}
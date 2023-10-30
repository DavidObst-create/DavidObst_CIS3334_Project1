package com.example.davidobst_cis3334_project1;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class EventViewHolder extends RecyclerView.ViewHolder {

        public EventViewHolder(View itemView) {
            super(itemView);
            textViewEventDescription = itemView.findViewById(R.id.textViewEventDescription);
            textViewEventDate = itemView.findViewById(R.id.textViewEventDate);
            textViewEventStart = itemView.findViewById(R.id.textViewEventStart);
            textViewEventEnd = itemView.findViewById(R.id.textViewEventEnd);
        }

        TextView textViewEventDescription;
        TextView textViewEventDate;
        TextView textViewEventStart;
        TextView textViewEventEnd;
}

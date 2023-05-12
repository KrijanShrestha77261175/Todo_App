package com.example.todoapp;

import androidx.cardview.widget.CardView;

public interface ListClick {
    void onClick(myList lists);
    void onLongClick(myList lists, CardView cardView);
}

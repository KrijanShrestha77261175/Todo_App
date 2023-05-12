package com.example.todoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.todoapp.databinding.ActivityDataInsertBinding;

/**
 * Insert Activity for adding a task or to update
 */
public class DataInsertActivity extends AppCompatActivity {

    // Creating an object of binding
    ActivityDataInsertBinding binding;

    /**
     * ON create method to initialize the activity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialing the binding object
        binding = ActivityDataInsertBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // get the type of crud operation,returns the type
        String type = getIntent().getStringExtra("type");

        // For Update
        if (type.equals("update")) {
            // Set the title of activity
            setTitle("Edit your Task");

            // Get the title for update
            binding.title.setText(getIntent().getStringExtra("title"));
            // Get the description for update
            binding.description.setText(getIntent().getStringExtra("description"));
            // Get the id for update
            int id = getIntent().getIntExtra("idList", 0);

            // change the text of button to update
            binding.addButton.setText("Update Task");


            /**
             * Action for clicking button
             */
            binding.addButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent();
                    intent.putExtra("title", binding.title.getText().toString());
                    intent.putExtra("description", binding.description.getText().toString());
                    intent.putExtra("idList", id);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            });
        } else {

            setTitle("Add Your Task");
            /**
             * Onclick listener to add a new to do task
             */
            binding.addButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    Intent intent = new Intent();
                    intent.putExtra("title", binding.title.getText().toString());
                    intent.putExtra("description", binding.description.getText().toString());
                    setResult(RESULT_OK, intent);
                    finish();
                }


            });
        }


    }


    /**
     * onBackpressed used in order to navigate back to the main activity from the data insert activity
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(DataInsertActivity.this, MainActivity.class));
    }
}
package com.example.todoapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.todoapp.databinding.ActivityMainBinding;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

/**
 * Main Activity
 */
public class MainActivity extends AppCompatActivity {

    Button fragmentButton;
    FrameLayout frameLayout;

    // Creating an object of binding
    ActivityMainBinding binding;
//    ViewAdapter adapter = new ViewAdapter(MainActivity.this);

    // Creating an object of listViewModel
    private ListViewModel listViewModel;


    /**
     * // Method where the activity is initialized
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialing binding object
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        fragmentButton = findViewById(R.id.fragmentBtn);
        frameLayout = findViewById(R.id.frameLayout);

        fragmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new TodoFragment();

                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

                transaction.replace(R.id.frameLayout, fragment).commit();
            }
        });


        // Initializing the object of ListViewModel
        listViewModel = new ViewModelProvider(this, (ViewModelProvider.Factory) ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(ListViewModel.class);


        binding.goToAdd.setOnClickListener(new View.OnClickListener() {
            /**
             * go To add button on click listener for directing to data insert activity
             * @param view
             */
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DataInsertActivity.class);
                intent.putExtra("type", "Add Your Task");
                startActivityForResult(intent, 1);
            }
        });

        // Setting the layout manager to be linear thus making sure the layout is inflated
        binding.recyclerViewList.setLayoutManager(new LinearLayoutManager(this));
        // Ensuring that the content of recycler view is fixed without being affected of any change
        binding.recyclerViewList.setHasFixedSize(true);

        // Creating an object of the adapter class ViewAdapter passing main activity context
        ViewAdapter adapter = new ViewAdapter(MainActivity.this);
        binding.recyclerViewList.setAdapter(adapter);


        listViewModel.getTodoList().observe(this, new Observer<List<myList>>() {
            /**
             * Observe the live data through the model passing in the activity as observer
             * adapter stores the entire list of tasks
             * @param myLists
             */
            @Override
            public void onChanged(List<myList> myLists) {
                adapter.submitList(myLists);
            }
        });



        /**
         * For delete swipe functionality
         */
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }



            /**
             * Function to enable swipe feature.
             * If the task is swiped right, the task is deleted, if the task is swiped left,the task is updated based on the direction of the onSwipe
             *
             * @param viewHolder The ViewHolder which has been swiped by the user.
             * @param direction  The direction to which the ViewHolder is swiped. It is one of
             *                   {@link #UP}, {@link #DOWN},
             *                   {@link #LEFT} or {@link #RIGHT}. If your
             *                   {@link #getMovementFlags(RecyclerView, ViewHolder)}
             *                   method
             *                   returned relative flags instead of {@link #LEFT} / {@link #RIGHT};
             *                   `direction` will be relative as well. ({@link #START} or {@link
             *                   #END}).
             */
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                if (direction == ItemTouchHelper.RIGHT) {

                    listViewModel.delete(adapter.getList(viewHolder.getAdapterPosition()));
                    Toast.makeText(MainActivity.this, "Successfully deleted the task", Toast.LENGTH_SHORT).show();


                } else {
                 /*   Intent intent = new Intent(MainActivity.this, DataInsertActivity.class);
                    intent.putExtra("type", "update");
                    intent.putExtra("title", adapter.getList(viewHolder.getAdapterPosition()).getListTitle());
                    intent.putExtra("description", adapter.getList(viewHolder.getAdapterPosition()).getListDescription());
                    intent.putExtra("idList", adapter.getList(viewHolder.getAdapterPosition()).getIdList());

                    startActivityForResult(intent, 2);*/
                    //   Toast.makeText(MainActivity.this, "Updating", Toast.LENGTH_SHORT).show();
                    ///////
                    Snackbar.make(binding.recyclerViewList, "Updating", Snackbar.LENGTH_LONG).setAction("Updating", new View.OnClickListener() {
                        /**
                         * For Uodate setting intent according to title, description, and ID from the adapter
                         * @param v
                         */
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(MainActivity.this, DataInsertActivity.class);
                            intent.putExtra("type", "update");
                            intent.putExtra("title", adapter.getList(viewHolder.getAdapterPosition()).getListTitle());
                            intent.putExtra("description", adapter.getList(viewHolder.getAdapterPosition()).getListDescription());
                            intent.putExtra("idList", adapter.getList(viewHolder.getAdapterPosition()).getIdList());

                            startActivityForResult(intent, 2);
                        }
                    }).show();
                    //////
                }

            }
        }).attachToRecyclerView(binding.recyclerViewList);

    }




    /**
     *
     * // Function to get the result based on intent from the activity
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Check if the request code is 1.If true then receive the title and description
        if (requestCode == 1) {
            String title = data.getStringExtra("title");
            String description = data.getStringExtra("description");

            // Calling the constructor of myList class
            myList my_list = new myList(title, description);

            // Adding the data in viewModel
            listViewModel.insert(my_list);

            // Toast message after a task has been added successfully
            Toast.makeText(this, "Your task has been added successfully", Toast.LENGTH_SHORT).show();
        } else if (requestCode == 2) {
            String title = data.getStringExtra("title");
            String description = data.getStringExtra("description");

            // Calling the constructor of myList class
            myList my_list = new myList(title, description);
            my_list.setIdList(data.getIntExtra("idList", 0));

            // Updating the data in ViewModel
            listViewModel.update(my_list);


            // Toast message after a task has been edited successfully
            Toast.makeText(this, "Your task has been edited successfully", Toast.LENGTH_SHORT).show();

        }
    }
}
package com.example.todoapp;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

/**
 * View Model class that performs crud operations
 */
public class ListViewModel extends AndroidViewModel {

    // Data is brought from repository class so an object is created of that class
    private ListRepository listRepo;

    // The data is "livedata" as it matches the state as well as store the most recent data after becoming active
    private LiveData<List<myList>> todoList;


    /**
     * // Data is received from list repository class
     *
     * @param application
     */
    public ListViewModel(@NonNull Application application) {
        super(application);

        listRepo = new ListRepository(application);
        todoList = listRepo.showList();
    }

    // Calling CRUD functions


    /**
     * // Calling insert function
     *
     * @param my_list
     */
    public void insert(myList my_list) {
        listRepo.insertData(my_list);
    }


    /**
     * // Calling update function
     *
     * @param my_list
     */
    public void update(myList my_list) {
        listRepo.updateData(my_list);
    }


    /**
     * // Calling delete function
     *
     * @param my_list
     */
    public void delete(myList my_list) {
        listRepo.deleteData(my_list);
    }


    /**
     * // Through model all the to do task are shown as live data
     *
     * @return
     */
    public LiveData<List<myList>> getTodoList() {
        return todoList;
    }
}

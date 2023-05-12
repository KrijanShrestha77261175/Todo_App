package com.example.todoapp;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

/**
 * Interface for DAO of list
 */
@Dao
public interface ListDao {


    /**
     * // Insert function annotation
     *
     * @param my_list
     */
    @Insert
    public void insert(myList my_list);


    /**
     * // Update function annotation
     *
     * @param my_list
     */
    @Update
    public void update(myList my_list);


    /**
     * // Delete function annotation
     *
     * @param my_list
     */
    @Delete
    public void delete(myList my_list);


    /**
     * // Show all list query
     *
     * @return
     */
    @Query("SELECT * FROM my_List")
    public LiveData<List<myList>> showList();
}

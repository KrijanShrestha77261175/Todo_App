package com.example.todoapp;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


/**
 * entities of myList class
 */
@Database(entities = myList.class, version = 1)
public abstract class ListDatabase extends RoomDatabase {

    // Single object throughout the application
    private static ListDatabase instance;

    /**
     * abstract function of return type ListDao
     * @return
     */
    public abstract ListDao listDao();

    /**
     * Synchronized is used in order to prevent blocking of main thread, basically to run over the main thread
     * @param context
     * @return
     */
    public static synchronized ListDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    ListDatabase.class, "list_database").fallbackToDestructiveMigration().build();
        }
        return instance;
    }
}

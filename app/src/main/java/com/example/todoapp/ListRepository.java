package com.example.todoapp;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

/**
 * Repository that stores all data for performing crud
 */
public class ListRepository {

    // Object of abstract class ListDao
    private ListDao listDao;

    private LiveData<List<myList>> todoList;



    /**
     * //Application being the sub class of context, we use application to get context from the abstract class ListDatabase
     * @param application
     */
    public ListRepository(Application application) {

        ListDatabase listDatabase = ListDatabase.getInstance(application);
        listDao = listDatabase.listDao();
        todoList = listDao.showList();
    }


    /**
     * // Insert function by calling respective classes
     * @param my_list
     */
    public void insertData(myList my_list){new InsertTask(listDao).execute(my_list);}


    /**
     * // Update function by calling respective classes
     * @param my_list
     */
    public void updateData(myList my_list){new UpdateTask(listDao).execute(my_list);}




    /**
     * // Delete function by calling respective classes
     * @param my_list
     */
    public void deleteData(myList my_list){new DeleteTask(listDao).execute(my_list);}




    /**
     * // Show entire list by calling respective classes
     * @return
     */
    public LiveData<List<myList>> showList(){
        return todoList;
    }




    /**
     * // one object for entire application, a class for inserting to do task
     *     // Async task runs one by one
     */
    private static class InsertTask extends AsyncTask<myList, Void, Void>{

        // Object of ListDao class
        private ListDao listDao;



        /**
         * // Constructor for ListDao
         * @param listDao
         */
        public InsertTask(ListDao listDao) {
            this.listDao = listDao;
        }

        /**
         * Insert runs in the background and null is returned as notifying the background process has finished is not necessary
         * @param myLists
         * @return
         */
        @Override
        protected Void doInBackground(myList... myLists) {
            listDao.insert(myLists[0]);
            return null;
        }
    }



    /**
     * // one object for entire application, a class for deleting to do task
     */
    private static class DeleteTask extends AsyncTask<myList, Void, Void>{

        // Object of ListDao class
        private ListDao listDao;



        /**
         * // Constructor for ListDao
         * @param listDao
         */
        public DeleteTask(ListDao listDao) {
            this.listDao = listDao;
        }

        /**
         * In the background as update is called in order to delete the task.
         * null is returned as we do not need to notify that the background process is complete
         * @param myLists
         * @return
         */
        @Override
        protected Void doInBackground(myList... myLists) {
            listDao.delete(myLists[0]);
            return null;
        }
    }



    /**
     * one object for entire application, a class for updating to do task
     */
    private static class UpdateTask extends AsyncTask<myList, Void, Void>{

        // Creating an object of the interface listdao
        private ListDao listDao;

        /**
         * Update task constructor to update the to do task
         * @param listDao
         */
        public UpdateTask(ListDao listDao) {
            this.listDao = listDao;
        }

        /**
         * In the background as update is called in order to edit the task.
         * null is returned as we do not need to notify that the background process is complete
         * @param myLists
         * @return
         */
        @Override
        protected Void doInBackground(myList... myLists) {
            listDao.update(myLists[0]);
            return null;
        }
    }


}

package com.example.todoapp;

import androidx.room.Entity;
import androidx.room.PrimaryKey;



/**
 * // Setting the name of the table my_List
 */
@Entity(tableName = "my_List")
public class myList {

    // integer id for each list that is set to be a primary key
    @PrimaryKey(autoGenerate = true)
    private int idList;
    // Variables to store the list title
    private String listTitle;
    // Variables to store the list description
    private String listDescription;




    /**
     * // Setter method for list id
     * @param idList
     */
    public void setIdList(int idList) {
        this.idList = idList;
    }



    /**
     * // Getter method for list id
     * @return
     */
    public int getIdList() {
        return idList;
    }



    /**
     * // Getter method for list title
     * @return
     */
    public String getListTitle() {
        return listTitle;
    }



    /**
     * // Setter method for list title
     * @param listTitle
     */
    public void setListTitle(String listTitle) {
        this.listTitle = listTitle;
    }



    /**
     * // Getter method for list description
     * @return
     */
    public String getListDescription() {
        return listDescription;
    }



    /**
     * // Setter method for list description
     * @param listDescription
     */
    public void setListDescription(String listDescription) {
        this.listDescription = listDescription;
    }



    /**
     * // Parameterized constructor for myList class having the title and description of the list
     * @param listTitle
     * @param listDescription
     */
    public myList(String listTitle, String listDescription) {
        this.listTitle = listTitle;
        this.listDescription = listDescription;
    }
}

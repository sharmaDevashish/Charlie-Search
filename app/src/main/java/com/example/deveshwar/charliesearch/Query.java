package com.example.deveshwar.charliesearch;

import android.database.sqlite.SQLiteDatabase;

import java.util.List;

/**
 * Created by Devashish on 11/29/2015.
 */
public class Query {
    int id;
    String query;

    public Query(){
        //Empty Constructor
    }

    public Query(int id,String query){
        this.id = id;
        this.query = query;
    }

    //getter for ID
    public int getId(){
        return this.id;
    }
    //setter for ID
    public void setId(int id){
        this.id = id;
    }
    //getter for Query
    public String getQuery(){
        return this.query;
    }
    //setter for Query
    public void setQuery(String query) {
        this.query = query;
    }
}

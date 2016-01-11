package com.example.deveshwar.charliesearch.data;

/**
 * Created by devashish.sharma on 12/2/2015.
 */
public class Query {

    public int id;
    public String query;
    public int time;

    //Empty Constructor
    public Query(){}

    //constructor
    public Query(int id, String query, int time){
        this.id = id;
        this.query = query;
        this.time = time;
    }

    //constructor
    public Query(String query,int time){
        this.query = query;
        this.time = time;
    }

    //getter for Id
    public int getId(){
        return this.id;
    }

    //setter for Id
    public void setId(int id){
        this.id = id;
    }

    //getter for Query
    public String getQuery(){
        return this.query;
    }

    //setter for Query
    public void setQuery(String query){
        this.query = query;
    }

    //getter for time
    public int getTime() {
        return this.time;
    }

    //setter for time
    public void setTime(int time){
        this.time = time;
    }
    }


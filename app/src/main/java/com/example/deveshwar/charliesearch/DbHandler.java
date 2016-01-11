package com.example.deveshwar.charliesearch;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Devashish on 11/29/2015.
 */
public class DbHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "QueryManager";
    private static final String TABLE_QUERY = "MyQueries";

    private static final String KEY_ID = "id";
    private static final String KEY_QUERY = "query";

    public DbHandler(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);//constructor
    }

    //Create Tables
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase){
        String CREATE_QUERY_TABLE = "CREATE TABLE " + TABLE_QUERY + "(" + KEY_ID  +" INTEGER PRIMARY KEY " + KEY_QUERY + " TEXT " +")";
        sqLiteDatabase.execSQL(CREATE_QUERY_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion){
        sqLiteDatabase.execSQL("DROP TABLE IF EXSISTS" + TABLE_QUERY);
        onCreate(sqLiteDatabase);
    }

    public void addQuery(Query query){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_QUERY,query.getQuery());

        sqLiteDatabase.insert(TABLE_QUERY, null, contentValues);
        sqLiteDatabase.close();
    }

    public Query getQuery(int id){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query(TABLE_QUERY, new String[]{KEY_ID,KEY_QUERY},KEY_ID +"=?",new String[]{String.valueOf(id)},null,null,null,null);
        if(cursor != null)cursor.moveToFirst();

        Query query = new Query(Integer.parseInt(cursor.getString(0)),cursor.getString(1));
        return query;
    }

    public List<Query>getAllQuery(){
        List<Query> queryList = new ArrayList<Query>();
        String selectQuery = "SELECT * FROM" +TABLE_QUERY;
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(selectQuery,null);
        if(cursor.moveToFirst()){
            do{
                Query query = new Query();
                query.setId(Integer.parseInt(cursor.getString(0)));
                query.setQuery(cursor.getString(1));
                queryList.add(query);
            }while (cursor.moveToNext());
        }
        return queryList;
    }

    public void deleteQuery(Query query){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(TABLE_QUERY,KEY_ID + "=?", new String[]{String.valueOf(query.getId())});
        sqLiteDatabase.close();
    }

    public int getQueryCount(){
        String countQuery = "SELECT * FROM" + TABLE_QUERY;
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(countQuery,null);
        cursor.close();
        return cursor.getCount();
    }
}

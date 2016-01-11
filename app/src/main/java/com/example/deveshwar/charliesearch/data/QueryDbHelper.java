package com.example.deveshwar.charliesearch.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

//import com.example.deveshwar.charliesearch.data.QueryContract.QueryEntry;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by devashish.sharma on 12/1/2015.
 */
public class QueryDbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    static final String DATABASE_NAME = "query.db";
    private static final  String TABLE_QUERY = "query";

    //public static final String TABLE_NAME = "data";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_QUERY ="query";
    public static final String COLUMN_DATE_TIME = "date_time";
    Query query;
    public QueryDbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase){

        String CREATE_QUERY_TABLE = "CREATE TABLE " + TABLE_QUERY + "(" + COLUMN_ID + " INTEGER PRIMARY KEY,"
                + COLUMN_QUERY + " TEXT NOT NULL," + COLUMN_DATE_TIME + " TEXT NOT NULL" + ")";
        sqLiteDatabase.execSQL(CREATE_QUERY_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion){
        sqLiteDatabase .execSQL("DROP TABLE IF EXISTS" + TABLE_QUERY);
        onCreate(sqLiteDatabase);
    }

    public void addQuery(Query query){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_QUERY,query.getQuery());
        contentValues.put(COLUMN_DATE_TIME,query.getTime());
        sqLiteDatabase.insert(TABLE_QUERY, null, contentValues);
        sqLiteDatabase.close();
        }

    public Query getQuery(int id){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query(TABLE_QUERY,new String[]
                {COLUMN_ID,COLUMN_QUERY,COLUMN_DATE_TIME},COLUMN_ID + "=?", new String[]{String.valueOf(id)},null,null,null,null);
        if(cursor!=null) {
            cursor.moveToFirst();
            query = new Query(Integer.parseInt(cursor.getString(0)), cursor.getString(1), Integer.parseInt(cursor.getString(2)));
        }
        return query;
    }

    public List<Query> getAllquery(){
        List<Query> queryList = new ArrayList<Query>();
        String selectQuery = "SELECT * FROM "+ TABLE_QUERY;
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()){
            do{
                Query query = new Query();
                query.setId(Integer.parseInt(cursor.getString(0)));
                query.setQuery(cursor.getString(1));
                query.setTime(Integer.parseInt(cursor.getString(2)));
                queryList.add(query);
            }while (cursor.moveToNext());
        }
        return queryList;
    }

    public void deleteQuery(int a){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        //sqLiteDatabase.execSQL("delete from " + TABLE_QUERY );
        sqLiteDatabase.delete(TABLE_QUERY,COLUMN_ID+"="+a,null);
        sqLiteDatabase.close();
    }

    public void deleteAllQuery(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.execSQL("delete from " + TABLE_QUERY );
        sqLiteDatabase.close();
    }

    public int getQueryCount(){
        String QueryCount = "SELECT * FROM " + TABLE_QUERY;
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(QueryCount,null);
        //cursor.close();
        return cursor.getCount();

    }
}

package com.example.myfirstquiz.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class MyDBManager {
    private Context context;
    private MyDBHelper myDBHelper;
    private SQLiteDatabase db;

    public MyDBManager(Context context){
        this.context = context;
        myDBHelper = new MyDBHelper(context);
    }

    public void openDB(){
        db = myDBHelper.getWritableDatabase();
    }

    public void insertToDB(String name, int scores){
        ContentValues cv = new ContentValues();
        cv.put(MyConstants.NAME, name);
        cv.put(MyConstants.SCORES, scores);
        db.insert(MyConstants.TABLE_NAME, null, cv);
    }

    public List<String> getFromDB (){
        List<String> tempList = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT " + MyConstants.NAME + ", " + MyConstants.SCORES + " FROM " +
                MyConstants.TABLE_NAME + " ORDER BY " + MyConstants.SCORES + " DESC", null);
        while(cursor.moveToNext()){
            int i = cursor.getColumnIndex(MyConstants.NAME);
            int y = cursor.getColumnIndex(MyConstants.SCORES);
            String name = cursor.getString(i);
            int scores = cursor.getInt(y);
            tempList.add(name + " " + scores + " баллов");
        }
        cursor.close();
        return tempList;
    }

    public void closeDB(){
        myDBHelper.close();
    }

}

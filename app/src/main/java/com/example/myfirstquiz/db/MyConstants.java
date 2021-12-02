package com.example.myfirstquiz.db;

public class MyConstants {
    public static final String TABLE_NAME = "my_table";
    public static final String _ID = "_id";
    public static final String NAME = "name";
    public static final String SCORES = "scores";
    public static final String DB_NAME = "my_bd.db";
    public static final int DB_VERSION = 1;
    public static final String TABLE_STRUCTURE = "CREATE TABLE IF NOT EXISTS " +
            TABLE_NAME + " (" + _ID + " INTEGER PRIMARY KEY," + NAME + " TEXT," +
            SCORES + " INTEGER)";
    public static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

}

package com.example.autorize;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.EditText;

import static com.example.autorize.MainActivity.LOG_TAG;

class DBHelper extends SQLiteOpenHelper {

    static DBHelper dbHelper;

    private DBHelper(Context context) {
        // конструктор суперкласса
        super(context, "myDB", null, 1);
    }

    public static DBHelper getInstance(Context context) {
        if (dbHelper == null) {
            dbHelper = new DBHelper(context);
        }
        return dbHelper;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(LOG_TAG, "--- onCreate database ---");
        // создаем таблицу с полями
        db.execSQL("create table mytable ("
                + "id integer primary key autoincrement,"
                + "name text,"
                + "lastName text,"
                + "email text,"
                + "username text,"
                + "password text" + ");");
    }

    public Cursor setOnClick(EditText username, EditText password){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Log.d(LOG_TAG, "---Rows in users: ---");
        Cursor cursor = db.query("mytable", new String[]{"id", "name", "username", "password"},
                "username=? AND password=?", new String[]{username.getText().toString(), password.getText().toString()},
                null, null, null);
        return(cursor);
    }

    public Cursor setOnClick(String id){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Log.d(LOG_TAG, "---Rows in users: ---");
        Cursor cursor = db.query("mytable", new String[]{"id", "name", "lastName","username", "password"},
                "id=?", new String[]{id},
                null, null, null);
        return(cursor);
    }

    public Cursor setOnClick(String id, EditText password){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Log.d(LOG_TAG, "---Rows in users: ---");
        Cursor cursor = db.query("mytable", new String[]{"id", "name", "username", "password"},
                "id=? AND password=?", new String[]{id, password.getText().toString()},
                null, null, null);
        return(cursor);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}


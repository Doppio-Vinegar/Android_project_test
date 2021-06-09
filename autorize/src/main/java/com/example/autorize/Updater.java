package com.example.autorize;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Updater extends Activity implements View.OnClickListener {

    public DBHelper dbHelper;
    EditText oldPas, newPas1, newPas2;
    Button btnUpdPas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updater);
        String id = getIntent().getStringExtra("id");

        dbHelper = DBHelper.getInstance(this);
        Cursor cursor = dbHelper.setOnClick(id);
        cursor.moveToFirst();
        //oldPas = (EditText) findViewById(R.id.oldPas);
        newPas1 = (EditText) findViewById(R.id.newPas1);
        newPas2 = (EditText) findViewById(R.id.newPas2);
        btnUpdPas = (Button) findViewById(R.id.btnUpdPas);
        btnUpdPas.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        dbHelper = DBHelper.getInstance(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String id = getIntent().getStringExtra("id");
        ContentValues cv = new ContentValues();
        switch (v.getId()) {
            case R.id.btnUpdPas:
                EditText oldPas = (EditText) findViewById(R.id.oldPas);
                Cursor cursor = dbHelper.setOnClick(id, oldPas);
                cursor.moveToFirst();
                if (cursor.getCount() < 1 ) {
                    //вывод всплывающего сообщения
                    Toast.makeText(this, "Введён неверный пароль", Toast.LENGTH_LONG).show();
                } else {
                    String password1 = cursor.getString(cursor.getColumnIndex("password"));
                    if (oldPas.getText().toString().equals(password1) && newPas1.getText().toString().equals(newPas2.getText().toString())) {
                        cv.put("password", newPas1.getText().toString());
                        db.update("mytable", cv, "id = ?", new String[]{id});
                        //вывод всплывающего сообщения
                        Toast.makeText(this, "Пароль успешно изменён", Toast.LENGTH_LONG).show();
                    }
                    else //вывод всплывающего сообщения
                        Toast.makeText(this, "Повторный пароль не совпадает", Toast.LENGTH_LONG).show();
                }
                break;
        }

    }
}
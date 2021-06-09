package com.example.autorize;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    static final String LOG_TAG = "myLogs";


    public DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Связываемся с элементами нашего интерфейса:
        dbHelper = DBHelper.getInstance(this);
    }

    public void onClickListener(View view)  {
        dbHelper = DBHelper.getInstance(this);
        EditText username = (EditText)findViewById(R.id.editTextTextPersonName);
        EditText password = (EditText) findViewById(R.id.editTextTextPassword);
        Cursor cursor = dbHelper.setOnClick(username, password);
        cursor.moveToFirst();
        if (cursor.getCount() < 1 ) {
            Intent intent = new Intent(this, ThirdActivity.class);
            startActivity(intent);
        } else {
            String username1 = cursor.getString(cursor.getColumnIndex("username"));
            String password1 = cursor.getString(cursor.getColumnIndex("password"));
            if (username.getText().toString().equals(username1)&&password.getText().toString().equals(password1)) {
                Intent intent = new Intent(this, SecondActivity.class);
                intent.putExtra("id",cursor.getString(cursor.getColumnIndex("id")));
                startActivity(intent);
                finish();
            }
        }
    }

    public void Reg(View view) {
        // Перейти в форму для регистрации при нажатии на кнопку
        Intent intent = new Intent(MainActivity.this, Registration.class);
        startActivity(intent);
    }


}
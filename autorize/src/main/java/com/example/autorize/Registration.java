package com.example.autorize;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Registration extends Activity implements View.OnClickListener{

    final String LOG_TAG = "myLogs";

    DBHelper dbHelper;

    private EditText name;
    private EditText lastName;
    private EditText email;
    private EditText username;
    private EditText password;
    //private SQLiteOpenHelper dbHelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        // Связываемся с элементами нашего интерфейса:
        name = (EditText) findViewById(R.id.editTextTextPersonName2);
        lastName = (EditText) findViewById(R.id.editTextTextPersonName3);
        email = (EditText) findViewById(R.id.editTextTextPersonName4);
        username = (EditText) findViewById(R.id.editTextTextPersonName5);
        password = (EditText) findViewById(R.id.editTextTextPassword2);
        Button registration = (Button) findViewById((R.id.registration));
        registration.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.registration:
                Log.d(LOG_TAG, "--- Insert in mytable: ---");
                // подготовим данные для вставки в виде пар: наименование столбца - значение
                dbHelper = DBHelper.getInstance(this);

                // создаем объект для данных
                ContentValues cv = new ContentValues();
                // подключаемся к БД
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                // получаем данные из полей ввода
                String name1 = name.getText().toString();
                String lastName1 = lastName.getText().toString();
                String email1 = email.getText().toString();
                String username1 = username.getText().toString();
                String password1 = password.getText().toString();

                cv.put("name", name1);
                cv.put("lastName", lastName1);
                cv.put("email", email1);
                cv.put("username", username1);
                cv.put("password", password1);
                // вставляем запись и получаем ее ID
                long rowID = db.insert("mytable", null, cv);
                Log.d(LOG_TAG, "row inserted, ID = " + rowID);
                Intent intent = new Intent(this, SecondActivity.class);
                intent.putExtra("id",Long.toString(rowID));
                startActivity(intent);
                finish();
                break;
        }
        // закрываем подключение к БД
        dbHelper.close();
    }
/*
    //прописываем функционал кнопки "Назад"
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
*/

}
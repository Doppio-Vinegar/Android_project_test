package com.example.autorize;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;

public class SecondActivity extends Activity implements View.OnClickListener{

    public DBHelper dbHelper;
    TextView name, lastName;
    Button btnUpd, btnDel, btnCurs;
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);
        String id = getIntent().getStringExtra("id");

        dbHelper = DBHelper.getInstance(this);
        Cursor cursor = dbHelper.setOnClick(id);
        cursor.moveToFirst();
        String name1 = cursor.getString(cursor.getColumnIndex("name"));
        String lastName1 = cursor.getString(cursor.getColumnIndex("lastName"));

        name = (TextView) findViewById(R.id.idname);
        name.setText(name1);

        lastName = (TextView) findViewById(R.id.idlastName);
        lastName.setText(lastName1);

        btnDel = (Button) findViewById(R.id.btnDel);
        btnDel.setOnClickListener(this);

        btnUpd = (Button) findViewById(R.id.btnUpd);
        btnUpd.setOnClickListener(this);

        btnCurs = (Button) findViewById(R.id.btnCurs);
        btnCurs.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        dbHelper = DBHelper.getInstance(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String id = getIntent().getStringExtra("id");
        switch (v.getId()) {
            case R.id.btnUpd:
                Intent intent1 = new Intent(SecondActivity.this, Updater.class);
                intent1.putExtra("id", id);
                startActivity(intent1);
                break;
            case R.id.btnDel:
                db.delete("mytable", "id = " + id, null);
                Intent intent2 = new Intent(SecondActivity.this, MainActivity.class);
                startActivity(intent2);
                finish();
                break;
            case R.id.btnCurs:
                Intent intent3 = new Intent(SecondActivity.this, exchange_rate.class);
                //intent3.putExtra("id", id);
                startActivity(intent3);
                break;
        }

    }

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
}
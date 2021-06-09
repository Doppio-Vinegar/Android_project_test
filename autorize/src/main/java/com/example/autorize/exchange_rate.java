package com.example.autorize;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.autorize.exchange.Message;
import com.example.autorize.exchange.MessageInsrt;
import com.example.autorize.exchange.MessagesApi;

import java.sql.Struct;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class exchange_rate extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{

    public static final String BASE_URL = "https://www.cbr-xml-daily.ru/";
    final int REQUEST_GET_INTERNET = 1;
    private SwipeRefreshLayout swipeRefL;
    private static final String TAG = "myLogs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exchange_rate);
        swipeRefL = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);
        swipeRefL.setOnRefreshListener(this);
        int permissionStatus = ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET);
        if (permissionStatus == PackageManager.PERMISSION_GRANTED) {
            showExchange();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET}, REQUEST_GET_INTERNET);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_GET_INTERNET:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission granted
                    showExchange();
                } else {
                    // permission denied
                }
                return;
        }
    }

    @Override
    public void onRefresh() {
        swipeRefL.setRefreshing(true);
        swipeRefL.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Останавливаем обновление:
                swipeRefL.setRefreshing(false);
            }
        }, 1000);
        int permissionStatus = ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET);
        if (permissionStatus == PackageManager.PERMISSION_GRANTED) {
            showExchange();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET}, REQUEST_GET_INTERNET);
        }
    }

    private void showExchange() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MessagesApi messagesApi = retrofit.create(MessagesApi.class);
        final Call<Message> messages = messagesApi.messages();
        messages.enqueue(new Callback<Message>() {
            @Override
            public void onResponse(Call<Message> call, Response<Message> response) {
                Map<String, MessageInsrt> msgInsrt = response.body().getValute();
                TextView jsonDollar = findViewById(R.id.jsonDollar);
                TextView jsonEuro = findViewById(R.id.jsonEuro);
                TextView jsonRub = findViewById(R.id.jsonRub);
                jsonDollar.setText(Double.toString(msgInsrt.get("USD").getValue()));
                jsonEuro.setText(Double.toString(msgInsrt.get("EUR").getValue()));
                jsonRub.setText(Double.toString(msgInsrt.get("BYN").getValue()));
            }

            @Override
            public void onFailure(Call<Message> call, Throwable t) {

            }
        });
    }



}
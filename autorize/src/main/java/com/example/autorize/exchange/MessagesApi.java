package com.example.autorize.exchange;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MessagesApi {

    @GET("https://www.cbr-xml-daily.ru/daily_json.js")
    Call<Message> messages();

}
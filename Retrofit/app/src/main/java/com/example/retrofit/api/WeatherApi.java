package com.example.retrofit.api;




import androidx.annotation.Nullable;

import com.example.retrofit.retrofit.annotation.Field;
import com.example.retrofit.retrofit.annotation.GET;
import com.example.retrofit.retrofit.annotation.POST;
import com.example.retrofit.retrofit.annotation.Query;

import okhttp3.Call;


public interface WeatherApi {

    @POST("/v3/weather/weatherInfo")
    Call postWeather(@Field("city") String city, @Field("key") String key);


    @GET("/v3/weather/weatherInfo")
    Call getWeather(@Query("city") @Nullable String city, @Query("key") String key);
}

package net;



import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by zj on 2018/1/12.
 */

public class RetrofitUtilGosn
{

    private static Retrofit retrofit = null;
    private static OkHttpClient.Builder httpClient=null;

    public static Retrofit getRetrofit()
    {
        if(retrofit==null){

            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.level(HttpLoggingInterceptor.Level.BODY);

            httpClient = new OkHttpClient.Builder();
            httpClient.connectTimeout(10, TimeUnit.SECONDS);
            httpClient.writeTimeout(10, TimeUnit.SECONDS);
            httpClient.readTimeout(10, TimeUnit.SECONDS);
            httpClient.interceptors().add(loggingInterceptor);
            httpClient.interceptors().add(new HeaderInterceptor());

            retrofit=new Retrofit.Builder()
                    .baseUrl("http://192.168.1.128:9091")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static OkHttpClient getOkHttp(){
        if(retrofit==null){
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.level(HttpLoggingInterceptor.Level.BODY);

            httpClient = new OkHttpClient.Builder();
            httpClient.connectTimeout(30, TimeUnit.SECONDS);
            httpClient.writeTimeout(30, TimeUnit.SECONDS);
            httpClient.readTimeout(30, TimeUnit.SECONDS);
            httpClient.interceptors().add(loggingInterceptor);
            httpClient.interceptors().add(new HeaderInterceptor());
            retrofit=new Retrofit.Builder()
                    .baseUrl("http://192.168.1.128:9091")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        return httpClient.build();
    }

}

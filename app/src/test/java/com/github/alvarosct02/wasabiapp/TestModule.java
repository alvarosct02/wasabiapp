package com.github.alvarosct02.wasabiapp;

import android.content.Context;
import android.util.Log;

import com.github.alvarosct02.wasabiapp.utils.retrofit.Services;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Alvaro on 12/27/2016.
 */

@Module
public class TestModule {
    private final TestApp application;

    public TestModule(TestApp application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Gson provideTestGson() {
        Log.e("ASCT", "Gson Provided");
        return new GsonBuilder().create();
    }

    @Provides
    OkHttpClient provideTestOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder()
                .readTimeout(12, TimeUnit.SECONDS)
                .connectTimeout(12, TimeUnit.SECONDS);

        //For adding logs of APIs requests & responses
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(interceptor);

        Log.e("ASCT", "OkHttpClient Provided");
        return builder.build();
    }

    @Provides
    @Singleton
    Retrofit provideTestRetrofit(OkHttpClient okHttpClient, Gson gson) {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://wasabiapp-389a5.firebaseio.com/");
        builder = builder.addConverterFactory(GsonConverterFactory.create(gson));
        Log.e("ASCT", "Retorfit Provided");
        return builder.client(okHttpClient).build();
    }

    @Provides
    @Singleton
    Services provideTestServices(Retrofit retrofit){
        Log.e("ASCT", "Services Provided");
        return retrofit.create(Services.class);
    }
}
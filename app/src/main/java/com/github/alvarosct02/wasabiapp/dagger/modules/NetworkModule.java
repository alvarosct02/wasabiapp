package com.github.alvarosct02.wasabiapp.dagger.modules;

import android.util.Log;

import com.github.alvarosct02.wasabiapp.BuildConfig;
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
 * Created by Alvaro on 12/14/2016.
 */

@Module
public class NetworkModule {

    private String mBaseUrl;
    // Constructor needs one parameter to instantiate.
    public NetworkModule(String baseUrl) {
        this.mBaseUrl = baseUrl;
    }

    @Provides
    @Singleton
    Gson provideGson() {
        Log.e("ASCT", "Gson Provided");
        return new GsonBuilder().create();
    }

    @Provides
    OkHttpClient provideOkHttpClient() {
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
    Retrofit provideRetrofit(OkHttpClient okHttpClient, Gson gson) {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(mBaseUrl);
        builder = builder.addConverterFactory(GsonConverterFactory.create(gson));
        Log.e("ASCT", "Retorfit Provided");
        return builder.client(okHttpClient).build();
    }

    @Provides
    @Singleton
    Services provideServices(Retrofit retrofit){
        Log.e("ASCT", "Services Provided");
        return retrofit.create(Services.class);
    }
}

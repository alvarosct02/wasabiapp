package com.github.alvarosct02.wasabiapp;

import com.github.alvarosct02.wasabiapp.dagger.modules.NetworkModule;
import com.github.alvarosct02.wasabiapp.ui.activities.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Alvaro on 12/27/2016.
 */

public class TestApp extends App {

    @Override public void onCreate() {
        super.onCreate();
        component = DaggerApp_NetworkComponent.builder()
                .networkModule(new NetworkModule(BuildConfig.BASE_API_URL))
                .build();
    }
}
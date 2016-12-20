package com.github.alvarosct02.wasabiapp;

import android.app.Application;
import android.content.Context;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;

/**
 * Created by Alvaro on 12/18/2016.
 */

public class App extends Application {

    public static App app;

    @Override
    public void onCreate() {
        super.onCreate();
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        app = this;
    }

    public static Context getContext(){
        return app.getApplicationContext();
    }
}

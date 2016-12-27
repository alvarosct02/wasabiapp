package com.github.alvarosct02.wasabiapp;

import android.app.Application;
import android.content.Context;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.github.alvarosct02.wasabiapp.dagger.modules.NetworkModule;
import com.github.alvarosct02.wasabiapp.ui.activities.GameActivity;
import com.karumi.dexter.Dexter;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Alvaro on 12/18/2016.
 */

public class App extends Application {

    public static App app;
    protected NetworkComponent component;

    @Singleton @Component(modules = {NetworkModule.class})
    public interface NetworkComponent {
        void inject(GameActivity activity);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        Dexter.initialize(this);
        app = this;

        component = DaggerApp_NetworkComponent.builder()
                .networkModule(new NetworkModule(BuildConfig.BASE_API_URL))
                .build();
    }

    public static Context getContext(){
        return app.getApplicationContext();
    }

    public NetworkComponent getComponent() {
        return component;
    }
}

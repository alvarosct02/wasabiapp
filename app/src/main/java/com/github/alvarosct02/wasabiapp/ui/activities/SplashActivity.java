package com.github.alvarosct02.wasabiapp.ui.activities;

import android.content.Intent;
import android.os.Bundle;

import com.facebook.AccessToken;

/**
 * Created by Alvaro on 12/18/2016.
 */

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (AccessToken.getCurrentAccessToken() != null) {
            startActivity(new Intent(this, MainActivity.class));
        } else {
            startActivity(new Intent(this, LoginActivity.class));
        }
        finish();
    }

}

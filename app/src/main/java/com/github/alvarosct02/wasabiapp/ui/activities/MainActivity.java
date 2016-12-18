package com.github.alvarosct02.wasabiapp.ui.activities;

import android.os.Bundle;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.github.alvarosct02.wasabiapp.R;

/**
 * Created by Alvaro on 12/18/2016.
 */

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initialize(R.layout.activity_main);
    }

    @Override
    public void setupViews() {
        super.setupViews();

        TextView tv_text = (TextView) findViewById(R.id.tv_text);
        tv_text.setText(AccessToken.getCurrentAccessToken().getToken());
    }
}

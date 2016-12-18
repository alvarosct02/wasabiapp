package com.github.alvarosct02.wasabiapp.ui.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Alvaro on 12/18/2016.
 */

public class BaseActivity extends AppCompatActivity {
    protected static String TAG = "ASCT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void initialize(int layoutId){
        setContentView(layoutId);
        setupVariables();
        setupViews();
        setupActions();
    }

    public void setupActions() {

    }

    public void setupViews() {

    }

    public void setupVariables() {

    }
}

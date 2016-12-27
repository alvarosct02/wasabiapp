package com.github.alvarosct02.wasabiapp;

import com.github.alvarosct02.wasabiapp.ui.activities.GameActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.*;

/**
 * Created by Alvaro on 12/27/2016.
 */

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class TestActivity {
    private GameActivity gameActivity;

    @Before public void setUp() throws Exception {
        gameActivity = Robolectric.setupActivity(GameActivity.class);
        ((TestApp) gameActivity.getApplication()).getComponent().inject(gameActivity);
    }

    @Test public void isRetrofitEnabled() throws Exception {
        assertNotNull(gameActivity.retrofit);
    }
}



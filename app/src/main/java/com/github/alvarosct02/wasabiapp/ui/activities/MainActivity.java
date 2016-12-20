package com.github.alvarosct02.wasabiapp.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.github.alvarosct02.wasabiapp.R;
import com.github.alvarosct02.wasabiapp.managers.FirebaseAuthManager;
import com.github.alvarosct02.wasabiapp.managers.FirebaseManager;
import com.github.alvarosct02.wasabiapp.models.User;
import com.github.alvarosct02.wasabiapp.utils.FirebaseRefs;
import com.github.alvarosct02.wasabiapp.utils.UtilMethods;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by Alvaro on 12/18/2016.
 */

public class MainActivity extends BaseActivity {

    private Button bt_new, bt_join, bt_test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initialize(R.layout.activity_main);
    }

    @Override
    public void setupViews() {
        super.setupViews();

        bt_new = (Button) findViewById(R.id.bt_new);
        bt_join = (Button) findViewById(R.id.bt_join);
        bt_test = (Button) findViewById(R.id.bt_test);
    }

    @Override
    public void setupActions() {
        super.setupActions();

        bt_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UtilMethods.upcomingFeature();
            }
        });

        bt_join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                TODO: Only joining to a fixed session
                Intent i = new Intent(MainActivity.this, GameActivity.class);
                startActivity(i);
            }
        });

        bt_join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                TODO: Only joining to a fixed session
                Intent i = new Intent(MainActivity.this, GameActivity.class);
                startActivity(i);
            }
        });

        bt_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addUserToSession();
            }
        });
    }

    public void addUserToSession(){
        FirebaseUser user = FirebaseManager.getAuth().getCurrentUser();
        Toast.makeText(this, "User: " + user.getUid() + " - " + user.getDisplayName() + " Created", Toast.LENGTH_SHORT).show();
        FirebaseRefs.getInstance().getUser().setValue(new User(user.getDisplayName(), 0));
    }
}

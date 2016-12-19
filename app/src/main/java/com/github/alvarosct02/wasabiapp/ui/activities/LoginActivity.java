package com.github.alvarosct02.wasabiapp.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.facebook.login.LoginResult;
import com.github.alvarosct02.wasabiapp.R;
import com.github.alvarosct02.wasabiapp.managers.FacebookAuthManager;
import com.github.alvarosct02.wasabiapp.managers.FirebaseAuthManager;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends BaseActivity implements FacebookAuthManager.IFacebookAuthManager, FirebaseAuth.AuthStateListener {

    private FacebookAuthManager facebookAuthManager;
    private FirebaseAuthManager firebaseAuthManager;
    private Button bt_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initialize(R.layout.activity_login);
    }


    @Override
    public void setupVariables() {
        super.setupVariables();

        firebaseAuthManager = new FirebaseAuthManager(this, this);

        facebookAuthManager = new FacebookAuthManager(this, this);
        facebookAuthManager.addPermission("public_profile");
        facebookAuthManager.addPermission("email");
    }

    @Override
    public void setupViews() {
        super.setupViews();
        bt_login = (Button) findViewById(R.id.bt_login);

    }

    @Override
    public void setupActions() {
        super.setupActions();

        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                facebookAuthManager.logInWithReadPermissions();
            }
        });
    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        facebookAuthManager.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if (user != null) {
            // User is signed in
            Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
            Intent i = new Intent(this, MainActivity.class);

        } else {
            // User is signed out
            Log.d(TAG, "onAuthStateChanged:signed_out");
        }
    }

    @Override
    public void handleFacebookLogin(LoginResult loginResult) {
        AuthCredential credential = FacebookAuthProvider.getCredential(loginResult.getAccessToken().getToken());
        firebaseAuthManager.signInWithCredential(credential);
    }

    @Override
    public void onStart() {
        super.onStart();
        firebaseAuthManager.start();
    }

    @Override
    public void onStop() {
        super.onStop();
        firebaseAuthManager.stop();
    }
}

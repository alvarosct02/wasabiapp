package com.github.alvarosct02.wasabiapp.managers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by rubymobile on 12/19/16.
 */

public class FacebookAuthManager {
    private final static String TAG = "FacebookAuthManager";

    private CallbackManager callbackManager;
    private AppCompatActivity loginActivity;
    private Collection<String> permissions;
    private IFacebookAuthManager iFacebookAuthManager;

    public FacebookAuthManager(AppCompatActivity activity, final IFacebookAuthManager iFacebook) {
        this.loginActivity = activity;
        this.permissions = new ArrayList<>();
        this.iFacebookAuthManager = iFacebook;
        this.callbackManager = CallbackManager.Factory.create();

        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        Log.d(TAG, "Login Success");
                        iFacebookAuthManager.handleFacebookLogin(loginResult);
                    }

                    @Override
                    public void onCancel() {
                        Log.d(TAG, "Login Cancel");
                        Toast.makeText(loginActivity, "Login Cancel", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        Log.d(TAG, "Login Error");
                        Toast.makeText(loginActivity, exception.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data){
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    public void addPermission(String newPermission){
        permissions.add(newPermission);
    }

    public void addPermissions(Collection<String> newPermissions){
        permissions.addAll(newPermissions);
    }

    public void logInWithReadPermissions(){
        LoginManager.getInstance().logInWithReadPermissions(loginActivity, permissions);
    }

    public void logInWithPublishPermissions(){
        LoginManager.getInstance().logInWithPublishPermissions(loginActivity, permissions);
    }

    public interface IFacebookAuthManager {
        void handleFacebookLogin(LoginResult loginResult);
    }
}

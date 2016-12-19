package com.github.alvarosct02.wasabiapp.managers;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.facebook.login.LoginResult;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by rubymobile on 12/19/16.
 */

public class FirebaseAuthManager {
    private final static String TAG = "FirebaseAuthManager";

    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener authListener;
    private AppCompatActivity loginActivity;

    public FirebaseAuthManager(AppCompatActivity activity, FirebaseAuth.AuthStateListener listener) {
        this.loginActivity = activity;
        this.auth = FirebaseManager.getAuth();
        this.authListener = listener;
    }

    public void start() {
        auth.addAuthStateListener(authListener);
    }

    public void stop() {
        if (authListener != null) {
            auth.removeAuthStateListener(authListener);
        }
    }

    public void signInWithCredential(AuthCredential authCredential) {
        auth.signInWithCredential(authCredential).addOnCompleteListener(loginActivity, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                Log.d(TAG, "signInWithCredential:onComplete:" + task.isSuccessful());

                if (!task.isSuccessful()) {
                    Log.w(TAG, "signInWithCredential", task.getException());
                    Toast.makeText(loginActivity, "Authentication failed.",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public interface IFirebaseAuthManager {
        void handleFirebaseLogin(FirebaseUser user);
    }

}

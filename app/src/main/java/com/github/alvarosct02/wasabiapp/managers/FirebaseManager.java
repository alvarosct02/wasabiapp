package com.github.alvarosct02.wasabiapp.managers;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by rubymobile on 12/19/16.
 */

public class FirebaseManager {
    private static FirebaseAuth auth;
    private static FirebaseDatabase db;

    public static FirebaseAuth getAuth(){
        return (auth == null)? auth = FirebaseAuth.getInstance(): auth;
    }

    public static FirebaseDatabase getDatabase(){
        return (db == null)? db = FirebaseDatabase.getInstance(): db;
    }



}

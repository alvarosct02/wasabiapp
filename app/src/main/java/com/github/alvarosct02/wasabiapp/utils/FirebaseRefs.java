package com.github.alvarosct02.wasabiapp.utils;

import com.github.alvarosct02.wasabiapp.managers.FirebaseManager;
import com.google.firebase.database.DatabaseReference;

/**
 * Created by rubymobile on 12/20/16.
 */

public class FirebaseRefs {

    private static FirebaseRefs instance;

    public static FirebaseRefs getInstance(){
        return (instance == null)? instance = new FirebaseRefs(): instance;
    }

    public DatabaseReference getSession(){
        return FirebaseManager.getDatabase().getReference("sessions/1");
    }

    public DatabaseReference getUser(){
        return FirebaseManager.getDatabase().getReference("sessions/1/users/" + FirebaseManager.getAuth().getCurrentUser().getUid());
    }
}

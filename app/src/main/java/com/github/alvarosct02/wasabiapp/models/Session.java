package com.github.alvarosct02.wasabiapp.models;

import java.util.Map;

/**
 * Created by rubymobile on 12/20/16.
 */

public class Session {
    private Map<String, User> users;


    public Map<String, User> getUsers() {
        return users;
    }

    public void setUsers(Map<String, User> users) {
        this.users = users;
    }
}

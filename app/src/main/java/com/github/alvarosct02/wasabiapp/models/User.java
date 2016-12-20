package com.github.alvarosct02.wasabiapp.models;


/**
 * Created by rubymobile on 12/20/16.
 */

public class User {
    private String name;
    private int count;

    public User(){

    }

    public User(String name, int count){
        this.name = name;
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}

package com.example.lukasz.myapplication.user;

import java.io.Serializable;

/**
 * Created by Lukasz on 2016-04-15.
 */
public class User implements Serializable{

    private String username;
    private int id;

    public User(String username, int id){
        this.username=username;
        this.id=id;
    }

    public void setUsername(String username){
        this.username=username;
    }

    public String getUsername(){
        return  username;
    }

    public void setId(int id){
        this.id=id;
    }

    public int getId(){
        return  id;
    }

}

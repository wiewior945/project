package com.example.lukasz.myapplication.user;

import java.io.Serializable;

/**
 * Created by Lukasz on 2016-04-15.
 */
public class User implements Serializable{

    private String username;
    private int id, privateGroupId;

    public User(String username, int id, int privateGroupId){
        this.username=username;
        this.id=id;
        this.privateGroupId=privateGroupId;
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

    public void setPrivateGroupId(int id){
        privateGroupId=id;
    }

    public int getPrivateGroupId(){
        return privateGroupId;
    }

}

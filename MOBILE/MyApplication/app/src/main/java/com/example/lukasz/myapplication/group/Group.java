package com.example.lukasz.myapplication.group;

/**
 * Created by Lukasz on 2016-04-15.
 */
public class Group {

    private int id, adminID;
    private String name;

    public Group (int id, String name, int adminID) {
        this.id = id;
        this.name=name;
        this.adminID=adminID;
    }



    public void setID(int id){
        this.id=id;
    }

    public int getID(){
        return id;
    }

    public void setName(String name){
        this.name=name;
    }

    public String getName(){
        return name;
    }

    public void setAdminID(int adminID){
        this.adminID=adminID;
    }

    public int getAdminID(){
        return adminID;
    }

}

package com.example.lukasz.myapplication.desktop;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.PopupMenu;
import android.widget.Spinner;

import com.example.lukasz.myapplication.R;
import com.example.lukasz.myapplication.dataBase.DataBaseConnection;
import com.example.lukasz.myapplication.group.NewGroup;
import com.example.lukasz.myapplication.user.EditUser;
import com.example.lukasz.myapplication.user.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by Lukasz on 2016-04-14.
 */
public class Desktop extends Activity {

    private User user;

     @Override
    protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.desktop_layout);
         user=(User) getIntent().getSerializableExtra("user");
         //ustawić nazwę prywatnej grupy na przycisku
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    public void menu(View view){
        PopupMenu popup = new PopupMenu(this, view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu, popup.getMenu());
        popup.show();
    }

    public void newGroup(MenuItem item){
        Intent intent=new Intent(this, NewGroup.class);
        intent.putExtra("adminId", user.getId());
        startActivity(intent);
    }

    public void editUser(MenuItem item){
        Intent intent=new Intent(this, EditUser.class);
        intent.putExtra("userId",user.getId());
        startActivity(intent);
    }

    public void groups(View view){
        try {
            String jsonString = new DataBaseConnection().execute("mobileApp/group/getGroupsId.php", "userID", Integer.toString(user.getId())).get();
            JSONObject jsonGroups=new JSONObject(jsonString);
            JSONArray array=jsonGroups.getJSONArray("records");
            String[] groupsID=new String[array.length()];
            for(int i=0;i<array.length();i++){
                JSONObject obj=array.getJSONObject(i);
                groupsID[i]=obj.getString("groupID");
            }
            PopupMenu menu=new PopupMenu(this, view);
            for(String id:groupsID){
                String name=new DataBaseConnection().execute("mobileApp/group/getNameById.php", "id", id).get();
                menu.getMenu().add(name);
            }
            menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem menuItem) {
                    System.out.println(menuItem.getTitle());
                    return true;
                }
            });
            menu.show();

        } catch (InterruptedException | JSONException | ExecutionException e) {
            e.printStackTrace();
        }
    }


}

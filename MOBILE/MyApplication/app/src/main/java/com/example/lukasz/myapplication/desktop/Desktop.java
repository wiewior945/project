package com.example.lukasz.myapplication.desktop;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Spinner;

import com.example.lukasz.myapplication.R;
import com.example.lukasz.myapplication.dataBase.DataBaseConnection;
import com.example.lukasz.myapplication.group.Group;
import com.example.lukasz.myapplication.group.NewGroup;
import com.example.lukasz.myapplication.note.AddNote;
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
    private Group group;
    private Button button;
    private int privateGroupId;

     @Override
    protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.desktop_layout);
         button=(Button) findViewById(R.id.groupButton);
         user=(User) getIntent().getSerializableExtra("user");
         try { //utworzenie obiektu grupy użytkownika, grupa ta jest pokazuje się jako pierwsza
             String groupName=new DataBaseConnection().execute("mobileApp/group/getNameById.php", "id", Integer.toString(user.getPrivateGroupId())).get();
             group=new Group(user.getPrivateGroupId(), groupName, user.getId());
             privateGroupId=group.getID();
             button.setText(group.getName());

         } catch (InterruptedException | ExecutionException e) {
             e.printStackTrace();
         }
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
                    String name= (String) menuItem.getTitle();
                    button.setText(name);
                    //tutaj zmieniać grupę
                    return true;
                }
            });
            menu.show();

        } catch (InterruptedException | JSONException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    public void addNote(View view){
        Intent intent=new Intent(this, AddNote.class);
        intent.putExtra("id", user.getId());
        intent.putExtra("privateGroupId",privateGroupId);
        startActivity(intent);
    }

}

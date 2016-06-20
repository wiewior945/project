package com.example.lukasz.myapplication.desktop;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Spinner;

import com.example.lukasz.myapplication.R;
import com.example.lukasz.myapplication.dataBase.DataBaseConnection;
import com.example.lukasz.myapplication.group.Group;
import com.example.lukasz.myapplication.group.NewGroup;
import com.example.lukasz.myapplication.note.AddNote;
import com.example.lukasz.myapplication.note.DisplayNote;
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
public class Desktop extends Activity implements View.OnClickListener {

    private User user;
    private Group selectedGroup;
    private Button button;
    private int privateGroupId;
    private ViewGroup notesLayout;
    private String[] groupsID;

     @Override
    protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.desktop_layout);
         button=(Button) findViewById(R.id.groupButton);
         user=(User) getIntent().getSerializableExtra("user");
     }

    @Override
    public void onStart() {
        super.onStart();
        try {
            String groupName=new DataBaseConnection().execute("mobileApp/group/getNameById.php", "id", Integer.toString(user.getPrivateGroupId())).get();
            selectedGroup=new Group(user.getPrivateGroupId(), groupName, user.getId());
            privateGroupId=selectedGroup.getID();
            button.setText(selectedGroup.getName());

        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        notesLayout = (ViewGroup) findViewById(R.id.layoutForNotes);
        getGroups();
        notesForGroup(selectedGroup.getName());
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    // dla imagebutton menu
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

    public void getGroups(){
        try {
            String jsonString = new DataBaseConnection().execute("mobileApp/group/getGroupsId.php", "userID", Integer.toString(user.getId())).get();
            JSONObject jsonGroups=new JSONObject(jsonString);
            JSONArray array=jsonGroups.getJSONArray("records");
            groupsID=new String[array.length()];
            for(int i=0;i<array.length();i++){
                JSONObject obj=array.getJSONObject(i);
                groupsID[i]=obj.getString("groupID");
            }
        } catch (InterruptedException | JSONException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    // przycisk wyboru grupy
    public void groups(View view){
        try {
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
                    notesForGroup(name);
                    return true;
                }
            });
            menu.show();

        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    public void addNote(View view){
        Intent intent=new Intent(this, AddNote.class);
        intent.putExtra("userId", user.getId());
        intent.putExtra("privateGroupId",privateGroupId);
        startActivity(intent);
    }

    // wyświetlenie notatek dla wybranej grupy
    public void notesForGroup(String groupName){
        try {
            notesLayout.removeAllViews();
            Context context = this;
            final float scale= context.getResources().getDisplayMetrics().density;
            int pixels = (int) (50 * scale + 0.5f);
            String jsonString = new DataBaseConnection().execute("mobileApp/group/getGroupByName.php", "groupname", groupName).get();
            JSONObject json = new JSONObject(jsonString);
            JSONArray jsonArray = json.getJSONArray("records");
            JSONObject obj = jsonArray.getJSONObject(0);
            String groupId = obj.getString("id");
            String adminId = obj.getString("adminId");
            selectedGroup = new Group(Integer.parseInt(groupId), groupName, Integer.parseInt(adminId));
            jsonString = new DataBaseConnection().execute("mobileApp/note/getNotesForGroup.php", "groupId", groupId).get();
            json = new JSONObject(jsonString);
            jsonArray = json.getJSONArray("records");
            Drawable icon = this.getResources().getDrawable(R.drawable.add_note_icon);
            for(int i=0; i<jsonArray.length(); i++){
                obj=jsonArray.getJSONObject(i);
                String name=obj.getString("name");
                String id=obj.getString("noteId");
                Button button = new Button(this);
                button.setMaxWidth(pixels);
                button.setText(name);
                button.setTag(id);
                button.setOnClickListener(this);
                notesLayout.addView(button);
            }

        } catch (InterruptedException | JSONException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    //wyświetlenie notatki
    @Override
    public void onClick(View v) {
        String noteId = (String) v.getTag();
        Intent intent=new Intent(this, DisplayNote.class);
        intent.putExtra("id", user.getId());
        intent.putExtra("noteId", noteId);
        intent.putExtra("groupsId", groupsID);
        startActivity(intent);
    }
}

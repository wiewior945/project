package com.example.lukasz.myapplication.group;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.PopupMenu;

import com.example.lukasz.myapplication.R;
import com.example.lukasz.myapplication.dataBase.DataBaseConnection;
import com.example.lukasz.myapplication.desktop.Desktop;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

/**
 * Created by Lukasz on 2016-06-23.
 */
public class EditGroup extends Activity {

    private String userId, groupId;
    private EditText nameText, password1Text, password2Text;
    private CheckBox checkBox;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_group_layout);
        saveButton = (Button) findViewById(R.id.addGroupButton);
        saveButton.setText("Zapisz");
        userId = getIntent().getExtras().getString("userId");
        groupId = getIntent().getExtras().getString("groupId");
        nameText = (EditText) findViewById(R.id.groupName);
        password1Text = (EditText) findViewById(R.id.groupPassword);
        password2Text = (EditText) findViewById(R.id.groupPassword2);
        passwordTextEnabled(false);
        checkBox = (CheckBox) findViewById(R.id.publicGroupCheckBox);

        try {
            String jsonString = new DataBaseConnection().execute("mobileApp/group/getGroupById.php", "groupId",groupId).get();
            JSONObject jsonObject = new JSONObject(jsonString);
            JSONArray jsonArray = jsonObject.getJSONArray("records");
            JSONObject json = jsonArray.getJSONObject(0);
            nameText.setText(json.getString("name"));
            String isPublic = json.getString("isPublic");
            if (isPublic.equals("0")){
                checkBox.setChecked(false);
                passwordTextEnabled(true);
                String password = json.getString("password");
                password1Text.setText(password);
                password2Text.setText(password);
            }
        } catch (InterruptedException | JSONException | ExecutionException e) {
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

    public void checkBox(View view){
        boolean checked = ((CheckBox) view).isChecked();
        passwordTextEnabled(!checked);
    }

    private void passwordTextEnabled(Boolean enabled){
        password1Text.setEnabled(enabled);
        password2Text.setEnabled(enabled);
    }

    public void cancel(View view){
        finish();
    }

    public void add(View view){
        String name = nameText.getText().toString();
        if (checkBox.isChecked()) {
            update(name, "", "1");
        }
        else{
            String password = password1Text.getText().toString();
            if(password.equals("")){
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Nie podano hasła!");
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
            else{
                if(password.equals(password2Text.getText().toString())){
                    update(name, password, "0");
                }
                else{
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setMessage("Hasła nie są takie same!");
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }
            }
        }
    }

    public void update(String name, String password, String isPublic){
        new DataBaseConnection().execute("mobileApp/group/updateGroup.php", "groupId", groupId, "name", name, "password", password, "isPublic", isPublic);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Pomyślnie wprowadzono zmiany");
        AlertDialog alertDialog = builder.create();
        Desktop.updateGroupName(name);
        alertDialog.show();
        finish();
    }

    public void deleteMember(View view){
        try {
            final Context contex=this;
            PopupMenu menu=new PopupMenu(this, view);
            String jsonString = new DataBaseConnection().execute("mobileApp/group/getUsersForGroup.php", "groupId", groupId).get();
            JSONObject jsonObject = new JSONObject(jsonString);
            JSONArray jsonArray = jsonObject.getJSONArray("records");
            for(int i=0; i<jsonArray.length(); i++){
                JSONObject json = jsonArray.getJSONObject(i);
                String userId = json.getString("userId");
                if(!userId.equals(this.userId)) {
                    String name = new DataBaseConnection().execute("mobileApp/user/getUsername.php", "id", userId).get();
                    menu.getMenu().add(0, Integer.parseInt(userId), 0, name);
                }
            }
            menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem menuItem) {
                    int idd = menuItem.getItemId();
                    String id = Integer.toString(idd);
                    new DataBaseConnection().execute("mobileApp/group/deleteUserFromGroup.php", "userId", id, "groupId", groupId);
                    AlertDialog.Builder builder = new AlertDialog.Builder(contex);
                    builder.setMessage("Usunięto użytkownika");
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                    return true;
                }
            });
            menu.show();

        } catch (InterruptedException | JSONException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}

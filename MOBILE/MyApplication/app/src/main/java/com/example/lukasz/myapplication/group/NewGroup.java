package com.example.lukasz.myapplication.group;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lukasz.myapplication.R;
import com.example.lukasz.myapplication.dataBase.DataBaseConnection;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

/**
 * Created by Lukasz on 2016-04-21.
 */
public class NewGroup extends Activity {

    private EditText password, password2, groupName;
    private String adminId;

     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         setContentView(R.layout.new_group_layout);
         int id=(int) getIntent().getExtras().getInt("adminId");
         adminId= Integer.toString(id);
         groupName=(EditText) findViewById(R.id.groupName);
         password=(EditText) findViewById(R.id.groupPassword);
         password2=(EditText) findViewById(R.id.groupPassword2);
         passwordTextEnabled(false);
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
        password.setEnabled(enabled);
        password2.setEnabled(enabled);
    }

    public  void add(View view){
        String name= groupName.getText().toString();
        if(name.equals("") || name==null){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Nie podano nazwy grupy!");
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
        else {
            String checkName=null;
            try {
                checkName = new DataBaseConnection().execute("mobileApp/group/getName.php", "groupname", name).get();
            } catch (InterruptedException | ExecutionException e) {
                System.out.println("błąd przy sprawdzaniu nazwy grupy" + e);
            }
            if (checkName.equals("")) {
                if (!password.isEnabled()) {
                    save(name, true, "");
                } else {
                    String pass = password.getText().toString();
                    if (pass.equals("") || pass == null) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(this);
                        builder.setMessage("Nie podano hasła!");
                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();
                    } else {
                        String pass2 = password2.getText().toString();
                        if (!pass.equals(pass2)) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(this);
                            builder.setMessage("Hasła nie są takie same!");
                            AlertDialog alertDialog = builder.create();
                            alertDialog.show();
                        } else{
                            save(name, false, pass);
                        }
                    }
                }
            }
            else{
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Taka grupa juz istnieje!");
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        }
    }

    private void save(String groupName, Boolean bool, String password){
        int b=bool? 1:0;
        String isPublic=Integer.toString(b);
        new DataBaseConnection().execute("mobileApp/group/addGroup.php", "groupname", groupName, "adminID", adminId, "isPublic", isPublic, "password", password);
        try {
            String isCreate=new DataBaseConnection().execute("mobileApp/group/getNameAndId.php","groupname",groupName).get();
            JSONObject json=new JSONObject(isCreate);
            JSONArray array=json.getJSONArray("records");
            JSONObject getRecords=array.getJSONObject(0);
            String name=getRecords.getString("groupname");
            if(groupName.equals(name)){
                String id=getRecords.getString("id");
                new DataBaseConnection().execute("/mobileApp/group/addUserToGroup.php","userId",adminId,"groupId",id);
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Pomyślnie dodano grupę");
                builder.setCancelable(false);
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        finish();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
            else{
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Dodawanie grupy nie powiodło się");
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }

        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void cancel(View view){
        finish();
    }
}

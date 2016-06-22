package com.example.lukasz.myapplication.group;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.lukasz.myapplication.R;
import com.example.lukasz.myapplication.dataBase.DataBaseConnection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

/**
 * Created by Lukasz on 2016-06-22.
 */
public class SearchGroup extends Activity implements View.OnClickListener {

    private String userId;
    private EditText editText;
    private ViewGroup layoutForGroups;
    private JSONArray jsonArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_group_layout);
        editText = (EditText) findViewById(R.id.searchGroupEditText);
        layoutForGroups = (ViewGroup) findViewById(R.id.LayoutForSearchingGroups);
        userId = getIntent().getExtras().getString("userId");
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    public void search(View view){
        try {
            layoutForGroups.removeAllViews();
            Drawable background = getResources().getDrawable(R.drawable.rounded);
            Context context = this;
            final float scale= context.getResources().getDisplayMetrics().density;
            int pixels = (int) (5 * scale + 0.5f);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            params.setMargins(pixels,pixels,pixels,0);
            String jsonString = new DataBaseConnection().execute("mobileApp/group/searchGroup.php", "name", editText.getText().toString()).get();
            JSONObject json = new JSONObject(jsonString);
            jsonArray = json.getJSONArray("records");
            for(int i=0; i<jsonArray.length();i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Button button = new Button(this);
                button.setText(jsonObject.getString("name"));
                button.setTag(i);
                button.setTextColor(Color.WHITE);
                button.setAllCaps(false);
                button.setTextSize(25);
                button.setBackgroundResource(R.drawable.rounded);
                button.setLayoutParams(params);
                button.setOnClickListener(this);
                layoutForGroups.addView(button);
            }
        } catch (InterruptedException | JSONException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    public void cancel(View view){
        finish();
    }

    @Override
    public void onClick(View v) {

        try {
            int i = (int) v.getTag();
            JSONObject json = jsonArray.getJSONObject(i);
            String isPublic = json.getString("isPublic");
            final String groupId = json.getString("id");
            final String password = json.getString("password");
            if(isPublic.equals("0")){
                final Dialog dialog = new Dialog(this);
                dialog.setContentView(R.layout.search_group_password_dialog);

                Button cancel = (Button) dialog.findViewById(R.id.cncelSearchGroupPassword);
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });

                Button confirm = (Button) dialog.findViewById(R.id.confirmSearchGroupPasswordButton);
                confirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        EditText passText = (EditText) dialog.findViewById(R.id.searchGroupPasswordText);
                        String pass = passText.getText().toString();
                        if (pass.equals(password)) {
                            addUserToGroup(userId, groupId);
                            dialog.cancel();
                        }
                        else passText.setBackgroundColor(Color.RED);
                    }
                });
                dialog.show();
            }
            else addUserToGroup(userId, groupId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void addUserToGroup(String userId, String groupId){
        new DataBaseConnection().execute("mobileApp/group/AddUserToGroup.php", "groupId", groupId, "userId", userId);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Zostałeś dodany do grupy");
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}

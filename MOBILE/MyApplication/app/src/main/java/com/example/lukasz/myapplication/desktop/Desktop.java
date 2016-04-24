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
import com.example.lukasz.myapplication.group.NewGroup;
import com.example.lukasz.myapplication.user.EditUser;
import com.example.lukasz.myapplication.user.User;

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

         String[] text = new String[]{"jeden","dwa","trzy"};
         Spinner spinner = (Spinner) findViewById(R.id.groupSpinner);
         ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, R.layout.group_spinner_layout, text);
         spinnerArrayAdapter.setDropDownViewResource(R.layout.group_spinner_layout);
         spinner.setAdapter(spinnerArrayAdapter);
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
        intent.putExtra("adminId",user.getId());
        startActivity(intent);
    }

    public void editUser(MenuItem item){
        Intent intent=new Intent(this, EditUser.class);
        intent.putExtra("userId",user.getId());
        startActivity(intent);
    }
}

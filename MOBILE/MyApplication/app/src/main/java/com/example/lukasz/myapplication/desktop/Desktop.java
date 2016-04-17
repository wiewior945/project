package com.example.lukasz.myapplication.desktop;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.lukasz.myapplication.R;
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

    public void newNote(){

    }
}

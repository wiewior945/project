package com.example.lukasz.myapplication.desktop;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.lukasz.myapplication.R;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

/**
 * Created by Lukasz on 2016-04-14.
 */
public class Desktop extends Activity {


     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         setContentView(R.layout.desktop_layout);
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

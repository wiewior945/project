package com.example.lukasz.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

/**
 * Created by Lukasz on 2016-03-23.
 */
public class Login extends Activity {

    EditText mEdit;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        mEdit   = (EditText)findViewById(R.id.loginText);
    }

    public void login(View view) {
        System.out.println(mEdit.getText());
    }
}

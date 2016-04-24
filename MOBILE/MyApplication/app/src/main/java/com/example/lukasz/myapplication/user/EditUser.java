package com.example.lukasz.myapplication.user;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.lukasz.myapplication.R;
import com.example.lukasz.myapplication.dataBase.DataBaseConnection;

import java.util.concurrent.ExecutionException;


/**
 * Created by Lukasz on 2016-04-24.
 */
public class EditUser extends Activity {

    EditText usernameText, passwordText, password2Text, confirmPasswordText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_user_layout);
        usernameText=(EditText) findViewById(R.id.usernameText);
        passwordText=(EditText) findViewById(R.id.passwordText);
        password2Text=(EditText) findViewById(R.id.passwordText2);
        confirmPasswordText=(EditText) findViewById(R.id.confirmPasswordText);
    }

    @Override
    public void onStart() {
        super.onStart();
     }

    @Override
    public void onStop() {
        super.onStop();
    }

    public void confirm(View view){
        String confirmPassword=confirmPasswordText.getText().toString();
        if(confirmPassword.equals("")){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Nie podano starego hasła do potwierdzenia zmian");
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
        else{
            try {
                String oldPassword=new DataBaseConnection().execute("mobileApp/user/getOldPassword.php").get();
                //sprawdzic hasło, potem sprawdzać i dokonywac zmiany
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        String username=usernameText.getText().toString();
        if(!username.equals("")){
            //zmiana nazwy
        }
        String password=passwordText.getText().toString();
        if(!password.equals("")){

        }

    }

    public void cancel(View view){
        finish();
    }
}

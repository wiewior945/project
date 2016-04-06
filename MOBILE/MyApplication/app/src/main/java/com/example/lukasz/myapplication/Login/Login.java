package com.example.lukasz.myapplication.Login;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.lukasz.myapplication.DataBase.DataBaseConnection;
import com.example.lukasz.myapplication.R;

import java.util.concurrent.ExecutionException;

/**
 * Created by Lukasz on 2016-03-23.
 */
public class Login extends Activity {

    private EditText loginText, passwordText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        loginText = (EditText) findViewById(R.id.loginText);
        passwordText=(EditText) findViewById(R.id.passwordText);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }


    public void login(View view) {
        String login=loginText.getText().toString();
        try {
            if(new DataBaseConnection().execute("http://192.168.0.66:80/mobileApp/login/username.php", "username", login).get().equals(login)){
                String password=passwordText.getText().toString();
                if(new DataBaseConnection().execute("http://192.168.0.66:80/mobileApp/login/password.php", "password", password).get().equals(password)){
                    //tutaj nowa activity
                }
                else{
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setMessage("Błędne hasło");
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }
            }
            else{
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Błędna nazwa użytkownika");
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public void register(View view){
        Intent intent = new Intent(this,Register.class);
        startActivity(intent);
    }
}

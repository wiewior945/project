package com.example.lukasz.myapplication.login;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.lukasz.myapplication.dataBase.DataBaseConnection;
import com.example.lukasz.myapplication.R;
import com.example.lukasz.myapplication.desktop.Desktop;
import com.example.lukasz.myapplication.user.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

/**
 * Created by Lukasz on 2016-03-23.
 * Sprawdza dane logowania i jeśli są poprawne tworzy obiekt user, który jest przekazywany do następnego ekranu (desktop)
 */
public class Login extends Activity {

    private EditText loginText, passwordText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        loginText = (EditText) findViewById(R.id.loginText);
        passwordText=(EditText) findViewById(R.id.passwordTextView);
    }

    @Override
    public void onStart() {
        super.onStart();
        loginText.setText("");
        passwordText.setText("");
    }

    @Override
    public void onStop() {
        super.onStop();
    }


    public void login(View view) {
        String login=loginText.getText().toString();
        try {
            if(!login.equals("")){
                String password=passwordText.getText().toString();
                if(!password.equals("")){
                    if(new DataBaseConnection().execute("mobileApp/login/username.php", "email", login).get().equals(login)){
                        if(new DataBaseConnection().execute("mobileApp/login/password.php", "password", password).get().equals(password)){
                            DataBaseConnection a=new DataBaseConnection();
                            String b=a.execute("mobileApp/login/getUser.php", "email", login).get();
                            JSONObject json = new JSONObject(b);
                            JSONArray tablica=null;
                            tablica=json.getJSONArray("records");
                            JSONObject c=tablica.getJSONObject(0);
                            String jsonID=c.getString("id");
                            int id = Integer.parseInt(jsonID);
                            String jsonGroupId=c.getString("privateGroup");
                            int privateGroupId=Integer.parseInt(jsonGroupId);

                            User user = new User(login ,id, privateGroupId);
                            Intent intent = new Intent(this,Desktop.class);
                            intent.putExtra("user",user);
                            startActivity(intent);
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
                        builder.setMessage("Taki użytkownik nie istnieje. Jeśli nie masz jeszcze konta załóż je klikając w przycisk rejestracja");
                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();
                    }
                }
                else{
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setMessage("Nie podano hasła");
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }
            }
            else{
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Nie podano nazwy użytkownika");
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void register(View view){
        Intent intent = new Intent(this,Register.class);
        startActivity(intent);
    }
}
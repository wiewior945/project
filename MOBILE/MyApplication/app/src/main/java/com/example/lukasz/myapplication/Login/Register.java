package com.example.lukasz.myapplication.login;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.lukasz.myapplication.dataBase.DataBaseConnection;
import com.example.lukasz.myapplication.R;

import java.util.concurrent.ExecutionException;

/**
 * Created by Lukasz on 2016-04-04.
 */
public class Register extends Activity {

    private EditText loginText, passwordText1, passwordText2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_layout);
        loginText=(EditText) findViewById(R.id.usernameText);
        passwordText1=(EditText) findViewById(R.id.passwordText1);
        passwordText2=(EditText) findViewById(R.id.passwordText2);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    /*
        sprawdza czy nazwa utkownika nie jest pusta oraz czy nie jest zajęta, poźniej sprawdza czy hasło jest takie samo, jeśli tak
        wysyła dane do bazy. Zwracany jest komunikat przy poprawnym zapisie danych i przy błędzie
     */
    public void register(View view){
        String username = loginText.getText().toString();
        if(username==null || username.equals("")){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Nie podano nazwy użytkowika");
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
        else{
            try {
                String checkUsername = new DataBaseConnection().execute("mobileApp/register/checkUsername.php","username", username).get();
                if(!checkUsername.equals("")){  //jesli login jest zajety
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setMessage("Nazwa użytkownika jest już zajęta! Wybierz inną nazwę");
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }
                else{
                    String password1 = passwordText1.getText().toString();
                    String password2 = passwordText2.getText().toString();
                    if(!password1.equals("")){
                        if(!password1.equals(password2)){
                            AlertDialog.Builder builder = new AlertDialog.Builder(this);
                            builder.setMessage("Hasła nie są takie same!");
                            AlertDialog alertDialog = builder.create();
                            alertDialog.show();
                        }
                        else{
                            new DataBaseConnection().execute("mobileApp/register/register.php", "username", username, "password", password1).get();
                            String check = new DataBaseConnection().execute("mobileApp/register/checkUsername.php","username", username).get();
                            if(check.equals(username)){
                                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                                builder.setMessage("Pomyślnie dodano użytkownika");
                                builder.setCancelable(false);
                                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                        finish();
                                }});
                                AlertDialog alertDialog = builder.create();
                                alertDialog.show();

                            }
                            else{
                                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                                builder.setMessage("Wystąpił błąd. Spróbuj ponownie");
                                AlertDialog alertDialog = builder.create();
                                alertDialog.show();
                            }
                        }
                    }
                    else{
                        AlertDialog.Builder builder = new AlertDialog.Builder(this);
                        builder.setMessage("Nie podano hasła");
                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }
}

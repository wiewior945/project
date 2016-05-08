package com.example.lukasz.myapplication.user;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
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
    int id;
    String idString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_user_layout);
        usernameText=(EditText) findViewById(R.id.usernameText);
        passwordText=(EditText) findViewById(R.id.passwordText);
        password2Text=(EditText) findViewById(R.id.passwordText2);
        confirmPasswordText=(EditText) findViewById(R.id.confirmPasswordText);
        id=(int) getIntent().getExtras().getInt("userId");
        idString=new Integer(id).toString();
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
            alert("Nie podano hasła do potwierdzenia zmian");
        }
        else{
            try {
                String oldPassword=new DataBaseConnection().execute("mobileApp/user/getPassword.php", "id", idString).get();
                if(oldPassword.equals(confirmPasswordText.getText().toString())) {
                    Boolean change=null,error=false;
                    String password=passwordText.getText().toString();
                    if(!password.equals("")){
                        String password2=password2Text.getText().toString();
                        if(password.equals(password2)){
                            new DataBaseConnection().execute("mobileApp/user/setPassword.php", "id", idString,"password", password);
                            change=true;
                        }
                        else{
                            alert("hasła nie są takie same");
                            change = false;
                            error=true;
                        }
                    }
                    String username=usernameText.getText().toString();
                    if(!username.equals("") && !error) {
                        String checkUsername = new DataBaseConnection().execute("mobileApp/register/checkUsername.php","username", username).get();
                        if(checkUsername.equals("")){
                            new DataBaseConnection().execute("mobileApp/user/setUsername.php", "id", idString, "username", username);
                            change = true;
                        }
                        else{
                            alert("Nazwa użytkownika jest już zajęta");
                            change=false;
                        }
                    }

                    if(change==null){
                        alert("Brak danych do zmiany");
                    }
                    else if(change){
                        AlertDialog.Builder builder = new AlertDialog.Builder(this);
                        builder.setMessage("Zmiany zostały zapisane");
                        builder.setCancelable(false);
                        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                                finish();
                            }});
                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();
                    }
                }
                else{
                   alert("Hasło potwierdzające zmiany nie jest prawidłowe");
                }
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
    }

    public void cancel(View view){
        finish();
    }

    private void alert(String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}

package com.example.lukasz.myapplication.note;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.lukasz.myapplication.R;
import com.example.lukasz.myapplication.dataBase.DataBaseConnection;

import java.util.concurrent.ExecutionException;


/**
 * Created by Lukasz on 2016-05-20.
 */
public class AddNote extends Activity {

    private String id, privateGroupId;
    private EditText noteName, noteText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_note_layout);
        id=Integer.toString((int) getIntent().getExtras().getInt("id"));
        privateGroupId=Integer.toString((int) getIntent().getExtras().getInt("privateGroupId"));
        noteName=(EditText) findViewById(R.id.addNoteName);
        noteText=(EditText) findViewById(R.id.addNoteNote);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    public void add(View view){
        String name=noteName.getText().toString();
        String note=noteText.getText().toString();
        try {
            String noteId=new DataBaseConnection().execute("mobileApp/note/getId.php", "userId", id, "name", name).get();
            if(noteId.equals("")){
                new DataBaseConnection().execute("mobileApp/note/addNote.php", "userId", id, "name", name, "note", note);
                noteId=new DataBaseConnection().execute("mobileApp/note/getId.php", "userId", id, "name", name).get();
                System.out.println("@@@1 "+noteId+","+privateGroupId);
                new DataBaseConnection().execute("mobileApp/note/addNoteToGroup.php", "noteId", noteId, "groupId", privateGroupId);
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Pomyślnie dodano notatkę");
                builder.setCancelable(false);
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        finish();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
            else{
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Taka nazwa notatki jest już używana!");
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        }

        catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    public void cancel(View view){
        finish();
    }
}

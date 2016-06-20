package com.example.lukasz.myapplication.note;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.EditText;

import com.example.lukasz.myapplication.R;
import com.example.lukasz.myapplication.dataBase.DataBaseConnection;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

/**
 * Created by Lukasz on 2016-06-06.
 */
public class EditNote extends EditTools {

    private String noteId, name, note;
    EditText noteNameField, noteTextField;

   @Override
    protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.new_note_layout);
       noteId = getIntent().getExtras().getString("noteId");
       noteNameField = (EditText) findViewById(R.id.addNoteName);
       noteTextField = (EditText) findViewById(R.id.addNoteNote);
       setEditText(noteTextField);
       try {
           String jsonString = new DataBaseConnection().execute("mobileApp/note/getNoteById.php", "noteId", noteId).get();
           JSONObject json = new JSONObject(jsonString);
           JSONArray jsonArray = json.getJSONArray("records");
           JSONObject jsonObject = jsonArray.getJSONObject(0);
           name = jsonObject.getString("name");
           note = jsonObject.getString("note");
           noteNameField.setText(name);
           noteTextField.setText(Html.fromHtml(note));
           super.setNote(note);
       } catch (InterruptedException | ExecutionException | JSONException e) {
           e.printStackTrace();
       }
   }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();

    }

    public void toolList(View view){

    }

    public void cancel(View view){
        finish();
    }

    public void save(View view){
        name = noteNameField.getText().toString();
        note = noteTextField.getText().toString();
        new DataBaseConnection().execute("mobileApp/note/updateNote.php", "name", name, "note", getNote(), "noteId", noteId);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Pomyślnie zaktualizowano notatkę");
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
}

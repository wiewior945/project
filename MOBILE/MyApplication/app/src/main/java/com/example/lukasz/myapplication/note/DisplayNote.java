package com.example.lukasz.myapplication.note;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.example.lukasz.myapplication.R;
import com.example.lukasz.myapplication.dataBase.DataBaseConnection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

/**
 * Created by Lukasz on 2016-06-05.
 */
public class DisplayNote extends Activity {

    private String userId, noteId, noteAdminId, note, name;
    private String[] groupsId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_note_layout);
        userId=Integer.toString((int) getIntent().getExtras().getInt("id"));
        noteId=getIntent().getExtras().getString("noteId");
        groupsId = getIntent().getExtras().getStringArray("groupsId");
    }

    @Override
    public void onStart() {
        super.onStart();
        try {
            String jsonString = new DataBaseConnection().execute("mobileApp/note/getNoteById.php", "noteId", noteId).get();
            JSONObject jsonObject = new JSONObject(jsonString);
            JSONArray jsonArray = jsonObject.getJSONArray("records");
            JSONObject json = jsonArray.getJSONObject(0);
            name = json.getString("name");
            noteAdminId = json.getString("userId");
            note=json.getString("note");
            TextView title = (TextView) findViewById(R.id.NoteTitleText);
            title.setText(name);
            TextView noteText = (TextView) findViewById(R.id.NoteText);
            noteText.setText(Html.fromHtml(note));
        } catch (InterruptedException | ExecutionException | JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    public void cancel(View view){
        finish();
    }

    public void edit(View view){
        Intent intent=new Intent(this, EditNote.class);
        intent.putExtra("noteId", noteId);
        startActivity(intent);
    }

    public void share(View view){
        PopupMenu menu=new PopupMenu(this, view);
        try {
            for (String id : groupsId) {
                String name = new DataBaseConnection().execute("mobileApp/group/getNameById.php", "id", id).get();
                menu.getMenu().add(name);
            }
        } catch (InterruptedException e) {
                e.printStackTrace();
        } catch (ExecutionException e) {
                e.printStackTrace();
        }
        menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                shareNote(menuItem.getTitle().toString());
                return true;
            }
        });
        menu.show();
    }

    private void shareNote(String groupName){
        try {
            String jsonString = new DataBaseConnection().execute("mobileApp/group/getGroupByName.php", "groupname", groupName).get();
            JSONObject json = new JSONObject(jsonString);
            JSONArray jsonArray = json.getJSONArray("records");
            JSONObject obj = jsonArray.getJSONObject(0);
            String groupId = obj.getString("id");
            new DataBaseConnection().execute("mobileApp/note/addNoteToGroup.php", "noteId", noteId, "groupId", groupId);

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Pomyślnie udostępniono notatkę");
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        } catch (InterruptedException | JSONException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}

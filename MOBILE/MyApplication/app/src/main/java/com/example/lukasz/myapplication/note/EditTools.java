package com.example.lukasz.myapplication.note;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.lukasz.myapplication.R;

/**
 * Created by Lukasz on 2016-06-08.
 */
public class EditTools extends Activity {

    private String note, saveUrl;
    private EditText editText;
    private boolean bold=false, italic=false, underline=false;

    public void editTools(View view){
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.edit_tools_dialog_layout);

        final ImageButton boldButton = (ImageButton) dialog.findViewById(R.id.boldButton);
        boldButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bold = !bold;
                if (bold) boldButton.setBackgroundResource(R.drawable.green_rounded);
                else boldButton.setBackgroundResource(R.drawable.rounded);
            }
        });

        final ImageButton italicButton = (ImageButton) dialog.findViewById(R.id.italicButton);
        italicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                italic=!italic;
                if(italic)italicButton.setBackgroundResource(R.drawable.green_rounded);
                else italicButton.setBackgroundResource(R.drawable.rounded);
            }
        });

        final ImageButton underlineButton = (ImageButton) dialog.findViewById(R.id.underlineButton);
        underlineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                underline=!underline;
                if(underline)underlineButton.setBackgroundResource(R.drawable.green_rounded);
                else underlineButton.setBackgroundResource(R.drawable.rounded);
            }
        });

        final Button cancelButton = (Button) dialog.findViewById(R.id.cancelEditToolsButton);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               dialog.cancel();
                bold=false; italic=false; underline=false;
            }
        });

        final Button addButton = (Button) dialog.findViewById(R.id.addEditedText);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText dialogText = (EditText) dialog.findViewById(R.id.editText);
                String editedText = dialogText.getText().toString();
                note = Html.toHtml(editText.getText());
                note = note.replaceAll("</p>\n","");
                if(!editText.equals("")){
                    if(bold){
                        editedText = "<b>" + editedText + "</b>";
                    }
                    if(italic){
                        editedText = "<i>" + editedText + "</i>";
                    }
                    if(underline){
                        editedText = "<u>" + editedText + "</u>";
                    }
                }
                setNote(note+editedText+"</p>");
                display(note);
                dialog.cancel();
                bold=false; italic=false; underline=false;
            }
        });

        dialog.show();
    }

    public void display(String text){
        editText.setText(Html.fromHtml(note));
    }

    public void setNote(String note){
        this.note=note;
    }

    public String getNote(){
        return Html.toHtml(editText.getText());
    }

    public void setEditText(EditText editText){
        this.editText=editText;
    }

    public void setSaveURL(String url){
        saveUrl = url;
    }
}

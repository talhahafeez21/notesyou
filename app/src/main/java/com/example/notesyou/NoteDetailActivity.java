package com.example.notesyou;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Date;

public class NoteDetailActivity extends AppCompatActivity {
     private EditText titleEditText,DescEditText;
     private Note selectednote;
     private Button Deleted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_detail);
        getSupportActionBar().hide();
        talhaa();
        checkforeditnotes();
    }

    private void talhaa() {
                titleEditText=findViewById(R.id.titleditText);
                DescEditText=findViewById(R.id.DescEditText);
                Deleted=findViewById(R.id.DeleteNoteButton);
    }
    private  void checkforeditnotes()
    {
        Intent previousintent=getIntent();
        int passednoteid=previousintent.getIntExtra(Note.Note_edit_extra,-1);
        selectednote=Note.getNoteForId(passednoteid);
        if (selectednote != null)
        {
           titleEditText.setText(selectednote.getTitle());
           DescEditText.setText(selectednote.getDescription());
        }
        else
        {
            Deleted.setVisibility(View.INVISIBLE);
        }


    }

    public void SaveNote(View view) {
        SQLiteManager sqLiteManager =    SQLiteManager.instanceOfDatabase(this);
        String title=String.valueOf(titleEditText.getText());
        String Desc =String.valueOf(DescEditText.getText());
        if (selectednote == null)
        {
            int id=Note.noteArrayList.size();
            Note note=new Note(id,title,Desc);
            Note.noteArrayList.add(note);
            sqLiteManager.addNoteToDatabase(note);

        }
        else {
            selectednote.setTitle(title);
            selectednote.setDescription(Desc);
            sqLiteManager.updateNoteInDB(selectednote);
        }
        finish();
    }

    public void deletenote(View view) {
        selectednote.setDeleted(new Date());
        SQLiteManager sqLiteManager = SQLiteManager.instanceOfDatabase(this);
        sqLiteManager.updateNoteInDB(selectednote);
        finish();
    }
}
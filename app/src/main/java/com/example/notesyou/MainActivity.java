package com.example.notesyou;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
  private ListView notelistView;
  SearchView se;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        darwer();
        talha();
        dbtomemory();
        setAdaptor();
        onitem();
        setOnClickListener();


    }




    private void darwer()
    {
        DrawerLayout drawerLayout=findViewById(R.id.drawer);
        findViewById(R.id.img).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }
    private void talha() {
        notelistView=findViewById(R.id.noteListView);
    }
    private  void dbtomemory()
    {
        SQLiteManager sqLiteManager =SQLiteManager.instanceOfDatabase(this);
        sqLiteManager.populateNoteListArray();
    }
    private void setAdaptor() {
        NoteAdaptor noteAdaptor=new NoteAdaptor(getApplicationContext(),Note.nondeletednotes());
        notelistView.setAdapter(noteAdaptor);
    }
    private void setOnClickListener() {

        notelistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                notelistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Note selectedNote=(Note) notelistView.getItemAtPosition(position);
                        Intent edit = new Intent(getApplicationContext(),NoteDetailActivity.class);
                        edit.putExtra(Note.Note_edit_extra,selectedNote.getId());
                        startActivity(edit);
                    }
                });
            }
        });
    }
    private  void onitem()
    {
        notelistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Note note=(Note) notelistView.getItemAtPosition(position);
                Intent i= new Intent(getApplicationContext(),NoteDetailActivity.class);
                i.putExtra(Note.Note_edit_extra,note.getId());
                startActivity(i);
            }
        });
    }


    public void newNote(View view) {
        Intent i = new Intent(MainActivity.this,NoteDetailActivity.class);
        startActivity(i);
    }
    public  void OnResume()
    {
        super.onResume();
         setNotAdaptor();
    }

    private void setNotAdaptor() {
        NoteAdaptor noteAdaptor=new NoteAdaptor(getApplicationContext(),Note.nondeletednotes());
        notelistView.setAdapter(noteAdaptor);
    }
}
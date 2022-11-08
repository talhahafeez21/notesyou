package com.example.notesyou;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;

public class NoteAdaptor extends ArrayAdapter<Note> {
    public NoteAdaptor( Context context, List<Note> notes) {
        super(context,0,notes);
    }
    public View getView(int position, View convertView , ViewGroup parent)
    {
        Note note=getItem(position);
        if (convertView==null)
        {
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.note_cell,parent,false);
            TextView title=convertView.findViewById(R.id.cellTitle);
            TextView desc=convertView.findViewById(R.id.cellDesc);
            title.setText(note.getTitle());
            desc.setText(note.getDescription());
            return convertView;
        }
        return super.getView(position,convertView,parent);

    }
}

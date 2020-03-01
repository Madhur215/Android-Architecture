package com.example.architecturepractice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private NoteViewModel noteViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView notesRecyclerView = findViewById(R.id.notes_recycler_view);
        notesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        notesRecyclerView.setHasFixedSize(true);
        final notesAdapter adapter = new notesAdapter();
        notesRecyclerView.setAdapter(adapter);


        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);
        noteViewModel.getAllNotes().observe(this, new Observer<List<NoteEntity>>() {
            @Override
            public void onChanged(List<NoteEntity> noteEntities) {
                adapter.setNotes(noteEntities);
            }
        });

    }
}

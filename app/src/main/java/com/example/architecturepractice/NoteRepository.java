package com.example.architecturepractice;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class NoteRepository {

    private NoteDao noteDao;
    private LiveData<List<NoteEntity>> allNotes;

    public NoteRepository(Application application){
        NotesDatabase database = NotesDatabase.getInstance(application);
        noteDao = database.noteDao();
        allNotes = noteDao.getallNotes();
    }

    public LiveData<List<NoteEntity>> getAllNotes(){
        return allNotes;
    }

    public void insert(NoteEntity noteEntity){

    }


    private static class InsertAsyncTask extends AsyncTask<NoteEntity, Void, Void>{




        @Override
        protected Void doInBackground(NoteEntity... noteEntities) {
            return null;
        }
    }

}

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
        allNotes = noteDao.getAllNotes();
    }

    public LiveData<List<NoteEntity>> getAllNotes(){
        return allNotes;
    }

    public void insert(NoteEntity noteEntity){
        new InsertAsyncTask(noteDao).execute(noteEntity);
    }

    public void delete(NoteEntity noteEntity){
        new DeleteAsyncTask(noteDao).execute(noteEntity);
    }

    public void update(NoteEntity noteEntity){
        new UpdateAsyncTask(noteDao).execute(noteEntity);
    }

    public void deleteAllNotes(){
        new DeleteAllNotesAsyncTask(noteDao).execute();
    }

    private static class InsertAsyncTask extends AsyncTask<NoteEntity, Void, Void>{

        private NoteDao noteDao;

        private InsertAsyncTask(NoteDao noteDao){
            this.noteDao = noteDao;
        }


        @Override
        protected Void doInBackground(NoteEntity... noteEntities) {
            noteDao.insert(noteEntities[0]);
            return null;
        }
    }

    private static class DeleteAsyncTask extends AsyncTask<NoteEntity, Void, Void>{

        private NoteDao noteDao;

        private DeleteAsyncTask(NoteDao noteDao){
            this.noteDao = noteDao;
        }


        @Override
        protected Void doInBackground(NoteEntity... noteEntities) {
            noteDao.delete(noteEntities[0]);
            return null;
        }
    }

    private static class UpdateAsyncTask extends AsyncTask<NoteEntity, Void, Void>{

        private NoteDao noteDao;

        private UpdateAsyncTask(NoteDao noteDao){
            this.noteDao = noteDao;
        }


        @Override
        protected Void doInBackground(NoteEntity... noteEntities) {
            noteDao.update(noteEntities[0]);
            return null;
        }
    }

    private static class DeleteAllNotesAsyncTask extends AsyncTask<Void, Void, Void>{

        private NoteDao noteDao;

        private DeleteAllNotesAsyncTask(NoteDao noteDao){
            this.noteDao = noteDao;
        }


        @Override
        protected Void doInBackground(Void... Void) {
            noteDao.deleteAllNotes();
            return null;
        }
    }

}

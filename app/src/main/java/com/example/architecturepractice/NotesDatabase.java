package com.example.architecturepractice;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = NoteEntity.class, version = 1)
public abstract class NotesDatabase extends RoomDatabase {

    private static NotesDatabase instance;
    // This variable is created to denote that this class is singleton,
    // means we can't create multiple instances of this database but use
    // the same instance everywhere in th app.

    public abstract NoteDao noteDao();

    public static synchronized NotesDatabase getInstance(Context context){

        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    NotesDatabase.class, "notes_database")
                    .fallbackToDestructiveMigration()
                    .build();

        }
        return instance;
    }

}

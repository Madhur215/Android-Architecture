package com.example.architecturepractice;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class notesAdapter extends RecyclerView.Adapter<notesAdapter.NotesHolder> {
    private List<NoteEntity> notes = new ArrayList<>();
    private OnItemClickListener listener;

    @NonNull
    @Override
    public NotesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.notes_layout,
                parent , false);
        return new NotesHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NotesHolder holder, int position) {
        NoteEntity currentNote = notes.get(position);
        holder.title.setText(currentNote.getTitle());
        holder.description.setText(currentNote.getDescription());
        holder.priority.setText(String.valueOf(currentNote.getId()));

    }

    @Override
    public int getItemCount() {
        if(notes!=null) {
            return notes.size();
        }
        return 0;
    }

    public void setNotes(List<NoteEntity> notes) {
        this.notes = notes;
        notifyDataSetChanged();
    }

    class NotesHolder extends RecyclerView.ViewHolder{

        private TextView title;
        private TextView description;
        private TextView priority;

        public NotesHolder(View itemView){
            super(itemView);
            title = itemView.findViewById(R.id.title_text_view);
            description = itemView.findViewById(R.id.description_text_view);
            priority = itemView.findViewById(R.id.priority_text_view);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null && getAdapterPosition() != RecyclerView.NO_POSITION) {
                        listener.OnItemClick(notes.get(getAdapterPosition()));
                    }
                }
            });

        }



    }

    public NoteEntity getNoteAt(int position){
        return notes.get(position);
    }

    public interface OnItemClickListener{
        void OnItemClick(NoteEntity note);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }
}

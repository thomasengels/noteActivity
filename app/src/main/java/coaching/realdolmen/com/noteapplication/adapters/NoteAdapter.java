package coaching.realdolmen.com.noteapplication.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import coaching.realdolmen.com.noteapplication.MainActivity;
import coaching.realdolmen.com.noteapplication.R;
import coaching.realdolmen.com.noteapplication.data.Note;

/**
 * Created by thomasengels on 03/01/2019.
 */

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {
    private List<Note> notes;

    public NoteAdapter(List<Note> notes){
        this.notes = notes;
    }

    @Override
    public NoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.note_item, parent, false);
        return new NoteViewHolder(v);
    }

    @Override
    public void onBindViewHolder(NoteViewHolder holder, int position) {
        Note note = this.notes.get(position);
        holder.setNoteTitle(note.getTitle());
        holder.setNoteContent(note.getContent());
        holder.setNoteCreationDate(note.getCreationDate().toString());
    }


    @Override
    public int getItemCount() {
        return this.notes.size();
    }

    class NoteViewHolder extends RecyclerView.ViewHolder {
        private TextView noteTitle;
        private TextView noteContent;
        private TextView noteCreationDate;

        public NoteViewHolder(View itemView) {
            super(itemView);
            this.noteTitle = itemView.findViewById(R.id.noteItemTitle);
            this.noteContent = itemView.findViewById(R.id.noteItemContent);
            this.noteCreationDate = itemView.findViewById(R.id.noteItemCreationDate);
        }

        public void setNoteTitle(String noteTitle){
            this.noteTitle.setText(noteTitle);
        }

        public void setNoteContent(String noteContent){
            this.noteContent.setText(noteContent);
        }

        public void setNoteCreationDate(String creationDate){
            this.noteCreationDate.setText(creationDate);
        }
    }
}

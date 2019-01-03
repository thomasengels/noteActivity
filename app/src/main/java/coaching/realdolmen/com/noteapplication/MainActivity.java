package coaching.realdolmen.com.noteapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

import coaching.realdolmen.com.noteapplication.adapters.NoteAdapter;
import coaching.realdolmen.com.noteapplication.data.Note;
import coaching.realdolmen.com.noteapplication.data.NoteDbHelper;

public class MainActivity extends AppCompatActivity {

    private RecyclerView noteItemsRecyclerView;
    private NoteAdapter noteAdapter;
    private final int REQUEST_CODE = 0;
    private List<Note> notes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CreateNoteActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });

        this.noteItemsRecyclerView = findViewById(R.id.recyclerViewNoteItems);
        this.noteItemsRecyclerView.setLayoutManager(
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        notes = NoteDbHelper.getInstance(this).getNotes();
        this.noteAdapter = new NoteAdapter(notes);
        this.noteItemsRecyclerView.setAdapter(this.noteAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            Long id = data.getLongExtra("noteId", 0);

            Note createdNote = NoteDbHelper.getInstance(this).getNoteById(id);

            this.notes.add(createdNote);
            this.noteAdapter.notifyDataSetChanged();
        }
    }
}

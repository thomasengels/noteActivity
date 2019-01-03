package coaching.realdolmen.com.noteapplication.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by thomasengels on 04/11/2018.
 */

public class NoteDbHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "coaching";

    private static final String TABLE = "note";

    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_CONTENT = "content";
    private static final String KEY_CREATION_DATE = "creation_date";

    private static NoteDbHelper instance;

    public static synchronized NoteDbHelper getInstance(Context context){
        if (instance == null) {
            instance = new NoteDbHelper(context.getApplicationContext());
        }
        return instance;
    }

    private NoteDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE + " ( " +
                KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                KEY_TITLE + " TEXT, " +
                KEY_CONTENT + " TEXT, " +
                KEY_CREATION_DATE + " DATETIME)";

        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE);
        this.onCreate(db);
    }

    public void createNote(Note note) {
        try (SQLiteDatabase db = this.getWritableDatabase()) {
            ContentValues values = new ContentValues();
            values.put(KEY_TITLE, note.getTitle());
            values.put(KEY_CONTENT, note.getContent());
            values.put(KEY_CREATION_DATE, System.currentTimeMillis());

            db.insert(TABLE, null, values);
        }
    }

    public List<Note> getNotes() {
        List<Note> notes = new LinkedList<>();
        String query = "SELECT * FROM " + TABLE + " ORDER BY " + KEY_CREATION_DATE;

        try (SQLiteDatabase db = this.getWritableDatabase(); Cursor cursor = db.rawQuery(query, null)) {
            if (cursor.moveToFirst()) {
                do {
                    Note note = new Note();
                    note.setId(Integer.parseInt(cursor.getString(0)));
                    note.setTitle(cursor.getString(1));
                    note.setContent(cursor.getString(2));
                    note.setCreationDate(new Date(cursor.getLong(3)));
                    notes.add(note);
                } while (cursor.moveToNext());
            }
            return notes;
        }
    }
}

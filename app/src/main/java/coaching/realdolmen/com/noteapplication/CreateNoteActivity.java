package coaching.realdolmen.com.noteapplication;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class CreateNoteActivity extends AppCompatActivity {

    private EditText noteContent;
    private EditText noteTitle;
    private Button confirmBtn;
    private Button cancelBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_note);

        this.noteContent = findViewById(R.id.inputNoteContentTextView);
        this.noteTitle = findViewById(R.id.inputNoteTitleTextView);

        this.confirmBtn = findViewById(R.id.btnNoteConfirm);
        this.confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = noteTitle.getText().toString();
                String content = noteContent.getText().toString();

                Toast toast = Toast.makeText(
                        CreateNoteActivity.this,
                        String.format("Note created with title: %s - and content: %s", title, content),
                        Toast.LENGTH_SHORT);
                toast.show();
            }
        });
        this.cancelBtn = findViewById(R.id.btnNoteCancel);
        this.cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.app.AlertDialog.Builder dialogBuilder = new android.app.AlertDialog.Builder(CreateNoteActivity.this);
                dialogBuilder.setTitle("Are you sure you want to cancel?");
                dialogBuilder.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                dialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                dialogBuilder.show();
            }
        });
    }
}

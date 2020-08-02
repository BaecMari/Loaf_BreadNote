package com.example.banananote;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.List;

public class NoteAddActivity extends AppCompatActivity {

    Toolbar toolbar;
    ActionBar actionBar;

    public String Title;
    public String CreateDate;
    public String Memo;

    //
    TextView Note_Title;
    TextView Note_CreateDate;
    TextView Note_Memo;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.note_add_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.btnEdit:
                break;
            case R.id.test1:
                break;
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_add);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true);

        //toolbar.setNavigationIcon(R.drawable.button_hamburger_size);

        Note_Title = findViewById(R.id.Note_Title);
        Note_CreateDate = findViewById(R.id.Note_CreateDate);
        Note_Memo = findViewById(R.id.Note_Memo);

        Title = ((Fragment_Main)Fragment_Main.context_Frag_Main).Title;
        CreateDate = ((Fragment_Main)Fragment_Main.context_Frag_Main).CreateDate;
        Memo = ((Fragment_Main)Fragment_Main.context_Frag_Main).Memo;

        Note_Title.setText(Title);
        Note_CreateDate.setText(CreateDate);
        Note_Memo.setText(Memo);

    }
}
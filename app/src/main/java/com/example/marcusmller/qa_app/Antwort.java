package com.example.marcusmller.qa_app;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;



public class Antwort extends AppCompatActivity {

    ImageButton imageBtnClose2;
    TextView txtViewAntwort2;
    Button btnSave;
    EditText editTextAntwort;
    public static final String MY_Pref = "MYPREFF";
    public static int zahl01 = MainActivity.temo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_antwort);

        editTextAntwort = (EditText) findViewById(R.id.editTextAntwort);

        final SharedPreferences sp = getSharedPreferences(MY_Pref, 0);
        final SharedPreferences.Editor ed = sp.edit();

        btnSave = (Button) findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final SharedPreferences s = getSharedPreferences(FrageStellen.MY_Pref, 0);
                String frage = s.getString(String.valueOf(MainActivity.listPosition), "Noch keine Frage!");
                ed.putString(String.valueOf(MainActivity.listPosition), frage+" ✓\n\r" +"- "+editTextAntwort.getText().toString());
                ed.commit();
                //  ed.remove(String.valueOf(FrageStellen.zahl));
                // ed.commit();


                finish();
            }
        });

        imageBtnClose2 = (ImageButton) findViewById(R.id.imageBtnClose2);
        imageBtnClose2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        final SharedPreferences s = getSharedPreferences(FrageStellen.MY_Pref, 0);
        final String frage = s.getString(String.valueOf(MainActivity.listPosition), "Noch keine Frage!");
        txtViewAntwort2 = (TextView) findViewById(R.id.txtViewAntwort2);
        txtViewAntwort2.setText(frage+":");


        DisplayMetrics dm = new DisplayMetrics();                           //Folgender Block = größe des popup fensters
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int) (width*.8), (int)(height*.6));          //Breite: 80% und Höhe:60% des Layout#
    }
}



/*
    String frage = s.getString(String.valueOf(FrageStellen.zahl-1), "Noch keine Frage!");
                ed.putString(String.valueOf(FrageStellen.zahl-1), frage+": "+editTextAntwort.getText().toString());
                        ed.commit();
*/
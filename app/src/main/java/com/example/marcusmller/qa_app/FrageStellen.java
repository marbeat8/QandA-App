package com.example.marcusmller.qa_app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class FrageStellen extends AppCompatActivity {

    Button btnFrageStellen;
    ImageButton imageBtnClose;
    EditText editText01;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragestellen);

        editText01 = (EditText) findViewById(R.id.editText01);

        btnFrageStellen = (Button) findViewById(R.id.btnFrageStellen);
        btnFrageStellen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MainActivity.list.add(editText01.getText().toString());  //Frage in Liste stellen (bzw. aus datenbank lesen und in liste stellen)
                editText01.setText("");
                MainActivity.adapter.notifyDataSetChanged();

                finish();
            }
        });

        imageBtnClose = (ImageButton) findViewById(R.id.imageBtnClose);
        imageBtnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });

        DisplayMetrics dm = new DisplayMetrics();                           //Größe des Fensters
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int) (width*.8), (int)(height*.6));          //Breite: 80% und Höhe:60% des Layout

    }

}

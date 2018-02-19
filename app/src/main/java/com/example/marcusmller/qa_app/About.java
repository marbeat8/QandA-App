package com.example.marcusmller.qa_app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageButton;

public class About extends AppCompatActivity {

    ImageButton imageBtnClose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        imageBtnClose = (ImageButton) findViewById(R.id.imageBtnClose);
        imageBtnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();                                   //Fenster schließen
            }
        });

        DisplayMetrics dm = new DisplayMetrics();                           //Größe des Fensters
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int) (width*.8), (int)(height*.6));          //Breite: 80% und Höhe:60% des Layout
    }
}

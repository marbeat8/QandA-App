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


import static com.example.marcusmller.qa_app.MainActivity.valueList;

public class FrageStellen extends AppCompatActivity {

    Button btnFrageStellen;
    ImageButton imageBtnClose;
    EditText editText01;

    public static final String MY_Pref = "MYPREFF";
    public static int zahl =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragestellen);

        editText01 = (EditText) findViewById(R.id.editText01);

        final SharedPreferences sp = getSharedPreferences(MY_Pref, 0);
        final SharedPreferences.Editor ed = sp.edit();

        btnFrageStellen = (Button) findViewById(R.id.btnFrageStellen);
        btnFrageStellen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ed.putString(String.valueOf(zahl), editText01.getText().toString());
                ed.commit();
                zahl++;


                //refresh ab hier!
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

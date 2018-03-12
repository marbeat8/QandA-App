package com.example.marcusmller.qa_app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static android.content.ContentValues.TAG;

public class FrageStellen extends AppCompatActivity {

    Button btnFrageStellen;
    ImageButton imageBtnClose;
    EditText editText01;
    String urlAddress = "https://84-23-78-37.blue.kundencontroller.de:8443/sendQuestion.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragestellen);

        final Calendar cal = Calendar.getInstance();

        final int dayofyear = cal.get(Calendar.DAY_OF_YEAR);
        final int year = cal.get(Calendar.YEAR);
        final int dayofweek = cal.get(Calendar.DAY_OF_WEEK);
        final int dayofmonth = cal.get(Calendar.DAY_OF_MONTH);
        editText01 = (EditText) findViewById(R.id.editText01);

        btnFrageStellen = (Button) findViewById(R.id.btnFrageStellen);
        btnFrageStellen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String user = Login.eingabeMail.toString();
                String frage = editText01.getText().toString();
                Sender s = new Sender(urlAddress, frage,user);
                s.execute();
                finish();
                MainActivity.refreshListview();
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

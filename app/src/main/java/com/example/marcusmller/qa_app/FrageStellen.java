package com.example.marcusmller.qa_app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import java.util.Calendar;
import java.util.Date;

public class FrageStellen extends AppCompatActivity {

    Button btnFrageStellen;
    ImageButton imageBtnClose;
    EditText editText01;

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

                FragmentOne.list.add(Login.eingabeMail+" am "+dayofmonth+"."+dayofweek+"."+year+":\n"+editText01.getText().toString());  //Frage in Liste stellen (bzw. aus datenbank lesen und in liste stellen)
                editText01.setText("");
                FragmentOne.adapter.notifyDataSetChanged();

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

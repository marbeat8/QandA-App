package com.example.marcusmller.qa_app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class AddLink extends AppCompatActivity {

    Button btnLink;
    ImageButton imageBtnClose3;
    EditText editTextLink01;
    EditText editTextLink02;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_link);

        editTextLink01 = (EditText) findViewById(R.id.editTextLink01);
        editTextLink02 = (EditText) findViewById(R.id.editTextLink02);

        btnLink = (Button) findViewById(R.id.btnLink);
        btnLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentTwo.list.add(editTextLink01.getText().toString()+"\n"+"► "+editTextLink02.getText().toString());  //Frage in Liste stellen (bzw. aus datenbank lesen und in liste stellen)
                editTextLink01.setText("");
                editTextLink02.setText("");
                FragmentTwo.adapter.notifyDataSetChanged();

                finish();
            }
        });

        imageBtnClose3 = (ImageButton) findViewById(R.id.imageBtnClose3);
        imageBtnClose3.setOnClickListener(new View.OnClickListener() {
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

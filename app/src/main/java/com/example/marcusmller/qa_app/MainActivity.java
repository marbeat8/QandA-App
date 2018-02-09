package com.example.marcusmller.qa_app;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static com.example.marcusmller.qa_app.FrageStellen.zahl;

public class MainActivity extends AppCompatActivity   {

    public static int temo = 0;
    Button btnFrage;
    ImageButton imageBtnCloseMain;
    ListView lv01;

    static int listPosition;


    String tempfrage = "";
    public static int Temptemo=0;
    public static final List valueList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //String inhalt = f.doInBackground();
        setContentView(R.layout.activity_main);
       // txtView01 = (TextView) findViewById(R.id.txtView01);
        lv01 = (ListView) findViewById(R.id.lv01);



        ListAdapter adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, valueList);
        final TextView textView4 = (TextView) findViewById(R.id.textView4);
        new FragenAusDatenbank(textView4).execute("someParams");

        String [] aktienlisteArray = {
                "Adidas - Kurs: 73,45 €",
                "Allianz - Kurs: 145,12 €",
                "BASF - Kurs: 84,27 €",
                "Bayer - Kurs: 128,60 €",
                "Beiersdorf - Kurs: 80,55 €",
                "BMW St. - Kurs: 104,11 €",
                "Commerzbank - Kurs: 12,47 €",
                "Continental - Kurs: 209,94 €",
                "Daimler - Kurs: 84,33 €"
        };
/*
        List <String> aktienListe = new ArrayList<>(Arrays.asList(aktienlisteArray));
        ArrayList<String> werte = new ArrayList();
        ArrayAdapter <String> aktienlisteAdapter =
                new ArrayAdapter<String>(this, // Die aktuelle Umgebung (diese Activity)
                        R.layout.activity_main, // ID der XML-Layout Datei
                        R.id.lv01, // ID des TextViews
                        aktienListe); // Beispieldaten in einer ArrayList
        adapter = new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, werte);
        lv01.setAdapter(aktienlisteAdapter);
        //ListView lv01 = (ListView) rootView.findViewById(R.id.listview_aktienliste);
        */
        btnFrage = (Button) findViewById(R.id.btnFrage);
        btnFrage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, FrageStellen.class));
            }
        });



        imageBtnCloseMain = (ImageButton) findViewById(R.id.imageBtnCloseMain);
        imageBtnCloseMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                System.exit(0);
            }
        });

        lv01 = (ListView) findViewById(R.id.lv01);
        lv01.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object listItem = lv01.getItemAtPosition(position);
                listPosition = position;
                startActivity(new Intent(MainActivity.this, Antwort.class));
            }
        });
    }
}

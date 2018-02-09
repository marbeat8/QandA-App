package com.example.marcusmller.qa_app;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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
        setContentView(R.layout.activity_main);
       // txtView01 = (TextView) findViewById(R.id.txtView01);
        lv01 = (ListView) findViewById(R.id.lv01);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar); // get the reference of Toolbar
        toolbar.setTitle("Q&A App"); // set Title for Toolbar
        toolbar.setLogo(R.drawable.android); // set logo for Toolbar
        setSupportActionBar(toolbar); // Setting/replace toolbar as the ActionBar



        final ListAdapter adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, valueList);

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

        lv01 = (ListView) findViewById(R.id.lv01);                                          //Durch klicken auf das Listitem öffent das Antworten fenster
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

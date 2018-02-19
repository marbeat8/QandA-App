package com.example.marcusmller.qa_app;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity   {

    Button btnFrage;
    ImageButton imageBtnCloseMain;
    ListView lv01;
    static int listPosition;

    static ArrayList<String> list = new ArrayList<String>();
    static ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar topToolBar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(topToolBar);


        lv01 = (ListView) findViewById(R.id.lv01);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);

        /** Setting the adapter to the ListView */
        lv01.setAdapter(adapter);

        btnFrage = (Button) findViewById(R.id.btnFrage);
        btnFrage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, FrageStellen.class));       //start der Frage-Activity
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

    //Toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    //Itemauswahl in der Toolbar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.about) {
            //öffne Fenster AboutUS
            startActivity(new Intent(MainActivity.this, About.class));
        }
        return super.onOptionsItemSelected(item);
    }
}











     /*      Element durch Button in Liste hinzufügen

        Button btnAdd = (Button) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText edit = (EditText) findViewById(R.id.txtItem);
                list.add(edit.getText().toString());
                edit.setText("");
                adapter.notifyDataSetChanged();
            }
        });
        */

            /* Array Ausslesen

        lv01 = (ListView) findViewById(R.id.lv01);
        final List<String> liste = new ArrayList<String>(Arrays.asList(aktienliste));

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, liste);

        lv01.setAdapter(arrayAdapter);
*/
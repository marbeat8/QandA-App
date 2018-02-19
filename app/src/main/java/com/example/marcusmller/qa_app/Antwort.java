package com.example.marcusmller.qa_app;


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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_antwort);

        editTextAntwort = (EditText) findViewById(R.id.editTextAntwort);

        btnSave = (Button) findViewById(R.id.btnLink);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String frage = FragmentOne.list.get(FragmentOne.listPosition);
                FragmentOne.list.remove(FragmentOne.listPosition);                //alten eintrag löschen
                FragmentOne.list.add(FragmentOne.listPosition,frage+" ✔"+"\n\r"+"⇒ "+editTextAntwort.getText().toString());    // neuen eintrage hinzufügen
                editTextAntwort.setText("");                        // clear Textfeld
                FragmentOne.adapter.notifyDataSetChanged();        // Liste aktualisieren

                finish();
            }
        });

        imageBtnClose2 = (ImageButton) findViewById(R.id.imageBtnClose3);
        imageBtnClose2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();                   // Schließt das Fenster
            }
        });

        // Auf welche Frage wird geantwortet!
        String frage = String.valueOf(FragmentOne.list.get(FragmentOne.listPosition));
        txtViewAntwort2 = (TextView) findViewById(R.id.txtViewAntwort2);
        txtViewAntwort2.setText(frage);



        //Fenster definieren
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
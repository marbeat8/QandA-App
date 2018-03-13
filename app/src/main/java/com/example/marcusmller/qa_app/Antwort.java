package com.example.marcusmller.qa_app;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class Antwort extends AppCompatActivity {
    final Context context = this;
    TextView txtViewAntwort2;
    Button btnSave;
    Button buttonAbbrechen;
    EditText editTextAntwort;
    String urlAddress = "https://84-23-78-37.blue.kundencontroller.de:8443/sendAnswer.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_antwort);
        // my_child_toolbar is defined in the layout file
        Toolbar myToolbar =(Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(myToolbar);
        // Get a support ActionBar corresponding to this toolbar
        android.support.v7.app.ActionBar ab = getSupportActionBar();
        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);


        editTextAntwort = (EditText) findViewById(R.id.editTextAntwort);

        btnSave = (Button) findViewById(R.id.btnAnswere);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (editTextAntwort.getText().toString().length() == 0) {
                    // Leere Antwort
                    //Fehlermeldung
                    AlertDialog.Builder builder1;
                    builder1 = new AlertDialog.Builder(context);
                    builder1.setMessage("Bitte Antwort eingeben!");
                    builder1.setCancelable(true);
                    builder1.setPositiveButton(
                            "ok",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });

                    AlertDialog alert11 = builder1.create();
                    alert11.show();

                } else {
                    String frage = FragmentOne.list.get(FragmentOne.listPosition);
                    String antwort = editTextAntwort.getText().toString();
                    String user = Login.eingabeMail.toString();
                    FragmentOne.list.remove(FragmentOne.listPosition);                //alten eintrag löschen
                    SimpleDateFormat dateFormatGer = new SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.GERMANY);
                    String date = dateFormatGer.format(new Date());
                    FragmentOne.list.add(FragmentOne.listPosition, frage + " ✔" + "\n\r am " +date+ " Uhr ⇒ "+ antwort + " (" + user + ")");    // neuen eintrage hinzufügen
                    editTextAntwort.setText("");                        // clear Textfeld
                    FragmentOne.adapter.notifyDataSetChanged();        // Liste aktualisieren
                    // ID der Frage auslesen
                    int indexOfDP = frage.indexOf(":\n");
                    frage = frage.substring(indexOfDP+2);
                    FragenAusDatenbank dbAbfrage = new FragenAusDatenbank("https://84-23-78-37.blue.kundencontroller.de:8443/reader.php");
                    String fragenID = "";
                    dbAbfrage.setDestMethod("selectID");
                    dbAbfrage.setCOLUMN("FrageID");
                    dbAbfrage.setTABLE("fragen");
                    dbAbfrage.setCONDITION("WHERE Frage='"+frage+"'");
                    fragenID = dbAbfrage.doInBackground();
                    ArrayList<String> arrListFrageID = new ArrayList<String>();
                    arrListFrageID = dbAbfrage.jsonToArrList(fragenID,"FrageID");
                    Log.d("FragenID2: ",arrListFrageID.get(0));
                    SenderAnswer s = new SenderAnswer(urlAddress, antwort,user,arrListFrageID.get(0));
                    s.execute();
                    finish();
                }
            }
        });


        buttonAbbrechen = (Button) findViewById(R.id.btnLinkAbbrechen);
        buttonAbbrechen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();                   // Schließt das Fenster
            }
        });


        // Auf welche Frage wird geantwortet!
        //String frage = String.valueOf(FragmentOne.list.get(FragmentOne.listPosition));
        //txtViewAntwort2 = (TextView) findViewById(R.id.txtViewAntwort2);
        //txtViewAntwort2.setText(frage);

}
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                Intent upIntent = NavUtils.getParentActivityIntent(this);
                if (NavUtils.shouldUpRecreateTask(this, upIntent)) {
                    // This activity is NOT part of this app's task, so create a new task
                    // when navigating up, with a synthesized back stack.
                    TaskStackBuilder.create(this)
                            // Add all of this activity's parents to the back stack
                            .addNextIntentWithParentStack(upIntent)
                            // Navigate up to the closest parent
                            .startActivities();
                } else {
                    // This activity is part of this app's task, so simply
                    // navigate up to the logical parent activity.
                    NavUtils.navigateUpTo(this, upIntent);
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}

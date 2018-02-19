package com.example.marcusmller.qa_app;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.StrictMode;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

public class Login extends AppCompatActivity {
    final Context context = this;
    Button btnAbbrechen;
    Button btnLogin;
    EditText txtMail;
    EditText txtPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    btnAbbrechen = (Button) findViewById(R.id.btnAbbrechen);
        btnAbbrechen.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();                                    //Schließen der Anwendung durch Button "Abbrechen"
            System.exit(0);

        }
    });
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
                HashMap<String, String> hashmap = new HashMap<String, String>();
                txtMail = (EditText) findViewById(R.id.editTextMail);
                txtPass = (EditText) findViewById(R.id.editTextPass);

                final String eingabeMail = txtMail.getText().toString();
                final String eingabePass = txtPass.getText().toString();

                Map<String,Object> params = new LinkedHashMap<>();
                params.put("name", eingabeMail);
                params.put("password", eingabePass);

                String url = "https://webmail.stud.hwr-berlin.de/ajax/login?action=login";
                Connection t = new Connection(url,params);
                String test=t.doInBackground();
                if(test.toLowerCase().contains("error")){
                    // eigentlich ! fuer test ohne !if(!test.toLowerCase().contains("error")){
                    // Login erfogreich
                    startActivity(new Intent(Login.this, MainActivity.class));      //Auffruf der neunenActivity
                    finish();
                }

                else{
                    // Login fehlerhaft
                    //Fehlermeldung
                    AlertDialog.Builder builder1;
                    builder1 = new AlertDialog.Builder(context);
                    builder1.setMessage("Password oder Benutzer ist falsch!");
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
                    // System.exit(0);
                }
            }
        });
    }
}
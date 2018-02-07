package com.example.marcusmller.qa_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import static com.example.marcusmller.qa_app.FrageStellen.zahl;

public class Login extends AppCompatActivity {

    Button btnAbbrechen;
    Button btnLogin;

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
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, MainActivity.class));      //Auffruf der neunenActivity
                finish();
            }
        });

    }


}
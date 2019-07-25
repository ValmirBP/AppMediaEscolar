package com.example.mediaescolar;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

Button btn1Bi,btn2Bi,btn3Bi,btn4Bi,btnResFim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btn1Bi = findViewById(R.id.btn1Bi);
        btn2Bi = findViewById(R.id.btn2Bi);
        btn3Bi = findViewById(R.id.btn3Bi);
        btn4Bi = findViewById(R.id.btn4Bi);
        btnResFim = findViewById(R.id.btnResFim);

        btn1Bi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nxScreen =
                        new Intent(MainActivity.this,PrimeiroBiActivity.class);
                startActivity(nxScreen);
            }
        });
        btn2Bi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nxScreen =
                        new Intent(MainActivity.this,SegundoBiActivity.class);
                startActivity(nxScreen);

            }
        });

        btn3Bi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nxScreen =
                        new Intent(MainActivity.this,TerceiroBiActivity.class);
                startActivity(nxScreen);
            }
        });

        btn4Bi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nxScreen =
                        new Intent(MainActivity.this, QuartoBiActivity.class);
                startActivity(nxScreen);
            }
        });
        btnResFim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(),"btnResFim",Toast.LENGTH_LONG).show();

            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "App Media Escolar", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }
}
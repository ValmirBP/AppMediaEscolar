package com.example.mediaescolar;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Arrays;


public class MainActivity extends AppCompatActivity {

    private String array_spinner[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


// Instanciando elementos do layout

        TextView txtTit = findViewById(R.id.txtBI1);
        TextView txtMat1 = findViewById(R.id.txtMat1);
        TextView txtProv1 = findViewById(R.id.txtProv1);
        TextView txtTrab1 = findViewById(R.id.txtProv1);
        Spinner s = findViewById(R.id.Spinner01);
        TextView edtProv1 = findViewById(R.id.edtProv1);
        TextView edtTrab1 = findViewById(R.id.edtTrab1);
        TextView txtMed1 = findViewById(R.id.txtMed1);
        TextView txtSit1 = findViewById(R.id.txtSit1);
        TextView txtMedFim1 = findViewById(R.id.txtMedFim1);
        TextView txtSitFim1 = findViewById(R.id.txtSitFim1);

// Inserção de um novo tipo de fonte no layout.

        Typeface font = Typeface.createFromAsset(getAssets(), "print_bold_tt.ttf");
        txtTit.setTypeface(font);
        txtMat1.setTypeface(font);
        txtProv1.setTypeface(font);
        txtTrab1.setTypeface(font);
        //s.setTypeface(font);
        edtProv1.setTypeface(font);
        edtTrab1.setTypeface(font);
        txtMed1.setTypeface(font);
        txtSit1.setTypeface(font);
        txtMedFim1.setTypeface(font);
        txtSitFim1.setTypeface(font);

// Aqui é toda a declaração das listas do dropbox com o nome das matérias

        array_spinner = new String[]{"Biologia", "Filosofia", "Física", "Química", "História",
                "Língua Portuguesa", Constant.MATEMATICA, "Literatonura", "Geografia", "Sociologia", "Inglês"};
        Arrays.sort(array_spinner);


        ArrayAdapter adapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, array_spinner);
        s.setAdapter(adapter);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

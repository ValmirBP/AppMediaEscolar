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

// inserção de um novo tipo de fonte no layout.

        TextView txt = findViewById(R.id.txtBI1);
        Typeface font = Typeface.createFromAsset(getAssets(), "print_bold_tt.ttf");
        txt.setTypeface(font);

        TextView mat1 = findViewById(R.id.txtMat1);
        TextView prov1 = findViewById(R.id.txtProv1);
        TextView trab1 = findViewById(R.id.txtTrab1);

        Typeface font1 = Typeface.createFromAsset(getAssets(), "littlelordfontleroynf.ttf");
        mat1.setTypeface(font1);
        prov1.setTypeface(font1);
        trab1.setTypeface(font1);

// Aqui é toda a declaração das listas do dropbox com o nome das matérias

        array_spinner  = new String[]{"Biologia", "Filosofia", "Física", "Química", "História",
                "Língua Portuguesa", Constant.MATEMATICA, "Literatonura", "Geografia", "Sociologia", "Inglês"};
        Arrays.sort(array_spinner);


        Spinner s = findViewById(R.id.Spinner01);
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

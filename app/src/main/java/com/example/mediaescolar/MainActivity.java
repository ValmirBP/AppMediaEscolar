package com.example.mediaescolar;

import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;


public class MainActivity extends AppCompatActivity {

// Declarando os tipos de variáveis

    double notaProv1, notaTrab1, medfim1;
    boolean ok = true;
    private String array_spinner[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_primeiro_bi);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "App Media Escolar", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

// Instanciando elementos do layout

        TextView txtTit = findViewById(R.id.txtBI1);
        TextView txtMat1 = findViewById(R.id.txtMat1);
        TextView txtProv1 = findViewById(R.id.txtProv1);
        TextView txtTrab1 = findViewById(R.id.txtTrab1);
        Spinner s = findViewById(R.id.Spinner01);
        final EditText edtProv1 = findViewById(R.id.edtProv1);
        final EditText edtTrab1 = findViewById(R.id.edtTrab1);
        final TextView txtMed1 = findViewById(R.id.txtMed1);
        TextView txtSit1 = findViewById(R.id.txtSit1);
        final TextView txtMedFim1 = findViewById(R.id.txtMedFim1);
        final TextView txtSitFim1 = findViewById(R.id.txtSitFim1);
        Button btnCalc = findViewById(R.id.btnCalc1);

// Inserção de um novo tipo de fonte no layout.

        Typeface font = Typeface.createFromAsset(getAssets(),
                "print_bold_tt.ttf");

        txtTit.setTypeface(font);
        txtMat1.setTypeface(font);
        txtProv1.setTypeface(font);
        txtTrab1.setTypeface(font);
        edtProv1.setTypeface(font);
        edtTrab1.setTypeface(font);
        txtMed1.setTypeface(font);
        txtSit1.setTypeface(font);
        txtMedFim1.setTypeface(font);
        txtSitFim1.setTypeface(font);
        btnCalc.setTypeface(font);

// Aqui é toda a declaração das listas do dropbox com o nome das matérias

        array_spinner = new String[]{Constant.MATEMATICA, Constant.BIOLOGIA, Constant.FILOSOFIA, Constant.FISICA, Constant.GEOGRAFIA, Constant.HISTORIA,
                Constant.INGLES, Constant.LITERATURA, Constant.PORTUGUES, Constant.QUIMICA, Constant.SOCIOLOGIA, Constant.ARTES,Constant.ESPORTES};
        Arrays.sort(array_spinner);


        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.spinner_materia, array_spinner);
        s.setAdapter(adapter);

        btnCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//mudando icone de error

                Drawable ic =
                        getResources().getDrawable(R.drawable.ic_error);
                if (ic != null){
                    ic.setBounds(0,0,ic.getIntrinsicWidth(),ic.getIntrinsicHeight());
                }

// evitando o crash da aplicação

                try {

// verificando se as notas estão inseridas

                    if (edtProv1.getText().toString().length() > 0) {
                        notaProv1 = Double.parseDouble(edtProv1.getText().toString());
                    } else {
                        edtProv1.setError("Campo obrigatório",ic);
                        edtProv1.requestFocus();
                        ok = false;
                    }
                    if (edtTrab1.getText().toString().length() > 0) {
                        notaTrab1 = Double.parseDouble(edtTrab1.getText().toString());
                    } else {
                        edtTrab1.setError("Campo obrigatório",ic);
                        edtTrab1.requestFocus();
                        ok = false;
                    }

//Aquisitando notas digitadas
                    if (ok) {
                        medfim1 = (notaProv1 + notaTrab1) / 2;
                        txtMedFim1.setText(String.valueOf(medfim1));

                        if (medfim1 >= 7) txtSitFim1.setText("APROVADO");
                        else txtSitFim1.setText("REPROVADO");
                    }

                    // convertendo as minhas variáveis
                    notaProv1 = Double.parseDouble(edtProv1.getText().toString());
                    notaTrab1 = Double.parseDouble(edtTrab1.getText().toString());

                } catch (Exception e) {

                    Toast.makeText(getApplicationContext(), "Os campos indicados são obrigatórios", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_sair) {

            finish();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

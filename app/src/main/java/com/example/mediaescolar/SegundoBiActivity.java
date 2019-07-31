package com.example.mediaescolar;

import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

public class SegundoBiActivity extends AppCompatActivity {

    // Declarando os tipos de variáveis

    TextView txtTit, txtMat2, txtProv2, txtTrab2, txtMed2, txtSit2, txtMedFim2, txtSitFim2;
    EditText edtProv2, edtTrab2;
    Button btnCalc2;
    Spinner s;

    double notaProv2, notaTrab2, medfim2;
    boolean ok = true;
    int selectedPosition;
    private String array_spinner[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segundo_bi);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

// Instanciando elementos do layout

        txtTit = findViewById(R.id.txtBI2);
        txtMat2 = findViewById(R.id.txtMat2);
        txtProv2 = findViewById(R.id.txtProv2);
        txtTrab2 = findViewById(R.id.txtTrab2);
        s = findViewById(R.id.Spinner02);
        edtProv2 = findViewById(R.id.edtProv2);
        edtTrab2 = findViewById(R.id.edtTrab2);
        txtMed2 = findViewById(R.id.txtMed2);
        txtSit2 = findViewById(R.id.txtSit2);
        txtMedFim2 = findViewById(R.id.txtMedFim2);
        txtSitFim2 = findViewById(R.id.txtSitFim2);
        btnCalc2 = findViewById(R.id.btnCalc2);

// Inserção de um novo tipo de fonte no layout.

        Typeface font = Typeface.createFromAsset(getAssets(),
                "print_bold_tt.ttf");

        txtTit.setTypeface(font);
        txtMat2.setTypeface(font);
        txtProv2.setTypeface(font);
        txtTrab2.setTypeface(font);
        edtProv2.setTypeface(font);
        edtTrab2.setTypeface(font);
        txtMed2.setTypeface(font);
        txtSit2.setTypeface(font);
        txtMedFim2.setTypeface(font);
        txtSitFim2.setTypeface(font);
        btnCalc2.setTypeface(font);

// Aqui é toda a declaração das listas do dropbox com o nome das matérias

        array_spinner = new String[]{Constant.MATEMATICA, Constant.BIOLOGIA, Constant.FILOSOFIA, Constant.FISICA, Constant.GEOGRAFIA, Constant.HISTORIA,
                Constant.INGLES, Constant.LITERATURA, Constant.PORTUGUES, Constant.QUIMICA, Constant.SOCIOLOGIA, Constant.ARTES, Constant.ESPORTES};
        Arrays.sort(array_spinner);


        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.spinner_materia, array_spinner);
        s.setAdapter(adapter);

        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                selectedPosition = position;

//                https://stackoverflow.com/questions/5068115/spinner-selection-save-to-sharedpreferences-then-retrieve
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnCalc2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//mudando icone de error

                Drawable ic =
                        getResources().getDrawable(R.drawable.ic_error);
                if (ic != null) {
                    ic.setBounds(0, 0, ic.getIntrinsicWidth(), ic.getIntrinsicHeight());
                }

// evitando o crash da aplicação

                try {

// verificando se as notas estão inseridas

                    if (edtProv2.getText().toString().length() > 0) {
                        notaProv2 = Double.parseDouble(edtProv2.getText().toString());

                        if (notaProv2 > 10) {
                            ok = false;
                            Toast.makeText(getApplicationContext(), "Nota Inválida", Toast.LENGTH_SHORT).show();
                            edtProv2.requestFocus();

                        } else {
                            ok = true;
                        }

                    } else {
                        edtProv2.setError("Campo obrigatório", ic);
                        edtProv2.requestFocus();
                        ok = false;
                    }

                    if (edtTrab2.getText().toString().length() > 0) {
                        notaTrab2 = Double.parseDouble(edtTrab2.getText().toString());

                        if (notaTrab2 > 10) {
                            ok = false;
                            Toast.makeText(getApplicationContext(), "Nota Invalida", Toast.LENGTH_SHORT).show();
                            edtTrab2.requestFocus();

                        } else {
                            ok = true;
                        }

                    } else {
                        edtTrab2.setError("Campo obrigatório", ic);
                        edtTrab2.requestFocus();
                        ok = false;
                    }

//Aquisitando notas digitadas

                    if (ok) {
                        medfim2 = (notaProv2 + notaTrab2) / 2;
                        txtMedFim2.setText(String.valueOf(medfim2));

                        if (medfim2 >= 7) txtSitFim2.setText("APROVADO");
                        else txtSitFim2.setText("REPROVADO");
                    }

                    // convertendo as minhas variáveis
                    notaProv2 = Double.parseDouble(edtProv2.getText().toString());
                    notaTrab2 = Double.parseDouble(edtTrab2.getText().toString());

                    sharedPreferences();

                } catch (Exception e) {

                    Toast.makeText(getApplicationContext(), "Os campos indicados são obrigatórios", Toast.LENGTH_LONG).show();
                }
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

    private void sharedPreferences() {
        SharedPreferences medEscPref =
                getSharedPreferences(MainActivity.SHARED_PREF, 0);
        SharedPreferences.Editor medEsc =
                medEscPref.edit();

        medEsc.putString("txtMedFim2", txtMedFim2.getText().toString());
        medEsc.putString("txtSitFim2", txtSitFim2.getText().toString());
        medEsc.putString("notaProv2", String.valueOf(notaProv2));
        medEsc.putString("notaTrab2", String.valueOf(notaTrab2));
        medEsc.putString("medfim2", String.valueOf(medfim2));
        medEsc.putString("txtmat2", String.valueOf(selectedPosition));
        medEsc.putBoolean("bi2", true);

        medEsc.commit();
    }
}

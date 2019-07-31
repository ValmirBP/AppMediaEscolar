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

public class PrimeiroBiActivity extends AppCompatActivity {

// Declarandoa s variáveis

    TextView txtTit, txtMat1, txtProv1, txtTrab1, txtMed1, txtSit1, txtMedFim1, txtSitFim1;
    EditText edtProv1, edtTrab1;
    Button btnCalc;
    Spinner s;

    double notaProv1, notaTrab1, medfim1;
    int selectedPosition;
    boolean ok = true;
    private String array_spinner[];

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_primeiro_bi);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

// Instanciando elementos do layout

        txtTit = findViewById(R.id.txtBI1);
        txtMat1 = findViewById(R.id.txtMat1);
        txtProv1 = findViewById(R.id.txtProv1);
        txtTrab1 = findViewById(R.id.txtTrab1);
        s = findViewById(R.id.Spinner01);
        edtProv1 = findViewById(R.id.edtProv1);
        edtTrab1 = findViewById(R.id.edtTrab1);
        txtMed1 = findViewById(R.id.txtMed1);
        txtSit1 = findViewById(R.id.txtSit1);
        txtMedFim1 = findViewById(R.id.txtMedFim1);
        txtSitFim1 = findViewById(R.id.txtSitFim1);
        btnCalc = findViewById(R.id.btnCalc1);

// Inserção de um novo tipo de fonte no layout.

        Typeface font = Typeface.createFromAsset(getAssets(),
                "print_bold_tt.ttf");

        txtTit.setTypeface(font); txtMat1.setTypeface(font); txtProv1.setTypeface(font);
        txtTrab1.setTypeface(font); edtProv1.setTypeface(font); edtTrab1.setTypeface(font);
        txtMed1.setTypeface(font);txtSit1.setTypeface(font); txtMedFim1.setTypeface(font);
        txtSitFim1.setTypeface(font);btnCalc.setTypeface(font);

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

        btnCalc.setOnClickListener(new View.OnClickListener() {
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

                    if (edtProv1.getText().toString().length() > 0) {
                        notaProv1 = Double.parseDouble(edtProv1.getText().toString());

                        if (notaProv1 > 10){
                            ok = false;
                            Toast.makeText(getApplicationContext(),"Nota inválida",Toast.LENGTH_SHORT).show();
                            edtProv1.requestFocus();

                        } else{
                            ok = true;
                        }

                    } else {
                        edtProv1.setError("Campo obrigatório", ic);
                        edtProv1.requestFocus();
                        ok = false;
                    }

                    if (edtTrab1.getText().toString().length() > 0) {
                        notaTrab1 = Double.parseDouble(edtTrab1.getText().toString());

                        if (notaTrab1 >10){
                            ok = false;
                            Toast.makeText(getApplicationContext(),"Nota Invalida",Toast.LENGTH_SHORT).show();

                        }else{
                            ok =true;
                        }

                    } else {
                        edtTrab1.setError("Campo obrigatório", ic);
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

        medEsc.putString("txtMedFim1", txtMedFim1.getText().toString());
        medEsc.putString("txtSitFim1", txtSitFim1.getText().toString());
        medEsc.putString("notaProv1", String.valueOf(notaProv1));
        medEsc.putString("notaTrab1", String.valueOf(notaTrab1));
        medEsc.putString("medfim1", String.valueOf(medfim1));
        medEsc.putString("txtmat1", String.valueOf(selectedPosition));
        medEsc.putBoolean("bi1", true);

        medEsc.commit();
    }
}



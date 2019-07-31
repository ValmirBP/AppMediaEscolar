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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

public class TerceiroBiActivity extends AppCompatActivity {

//Declaração das variáveis

    TextView txtTit, txtMat3, txtProv3, txtTrab3, txtMed3, txtSit3, txtMedFim3, txtSitFim3;
    EditText edtProv3, edtTrab3;
    Button btnCalc3;
    Spinner s;

    double notaProv3, notaTrab3, medfim3;
    int selectedPosition;
    boolean ok = true;

    private String array_spinner[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terceiro_bi);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

// Instanciando elementos do layout

        txtTit = findViewById(R.id.txtBI3);
        txtMat3 = findViewById(R.id.txtMat3);
        txtProv3 = findViewById(R.id.txtProv3);
        txtTrab3 = findViewById(R.id.txtTrab3);
        s = findViewById(R.id.Spinner03);
        edtProv3 = findViewById(R.id.edtProv3);
        edtTrab3 = findViewById(R.id.edtTrab3);
        txtMed3 = findViewById(R.id.txtMed3);
        txtSit3 = findViewById(R.id.txtSit3);
        txtMedFim3 = findViewById(R.id.txtMedFim3);
        txtSitFim3 = findViewById(R.id.txtSitFim3);
        btnCalc3 = findViewById(R.id.btnCalc3);

// Inserção de um novo tipo de fonte no layout.

        Typeface font = Typeface.createFromAsset(getAssets(),
                "print_bold_tt.ttf");

        txtTit.setTypeface(font);
        txtMat3.setTypeface(font);
        txtProv3.setTypeface(font);
        txtTrab3.setTypeface(font);
        edtProv3.setTypeface(font);
        edtTrab3.setTypeface(font);
        txtMed3.setTypeface(font);
        txtSit3.setTypeface(font);
        txtMedFim3.setTypeface(font);
        txtSitFim3.setTypeface(font);
        btnCalc3.setTypeface(font);

// Aqui é toda a declaração das listas do dropbox com o nome das matérias

        array_spinner = new String[]{Constant.MATEMATICA, Constant.BIOLOGIA, Constant.FILOSOFIA, Constant.FISICA, Constant.GEOGRAFIA, Constant.HISTORIA,
                Constant.INGLES, Constant.LITERATURA, Constant.PORTUGUES, Constant.QUIMICA, Constant.SOCIOLOGIA, Constant.ARTES, Constant.ESPORTES};
        Arrays.sort(array_spinner);


        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.spinner_materia, array_spinner);
        s.setAdapter(adapter);

        btnCalc3.setOnClickListener(new View.OnClickListener() {
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

                    if (edtProv3.getText().toString().length() > 0) {
                        notaProv3 = Double.parseDouble(edtProv3.getText().toString());

                        if(notaProv3 > 10){
                            ok = false;
                            Toast.makeText(getApplicationContext(),"Nota Invalida",Toast.LENGTH_SHORT).show();
                            edtProv3.requestFocus();

                        }else {
                            ok = true;
                        }

                    } else {
                        edtProv3.setError("Campo obrigatório", ic);
                        edtProv3.requestFocus();
                        ok = false;
                    }

                    if (edtTrab3.getText().toString().length() > 0) {
                        notaTrab3 = Double.parseDouble(edtTrab3.getText().toString());

                        if ( notaTrab3 > 10){
                            ok = false;
                            Toast.makeText(getApplicationContext(),"Nota Invalida",Toast.LENGTH_SHORT).show();
                            edtTrab3.requestFocus();

                        }else {
                            ok = true;
                        }

                    } else {
                        edtTrab3.setError("Campo obrigatório", ic);
                        edtTrab3.requestFocus();
                        ok = false;
                    }

//Aquisitando notas digitadas

                    if (ok) {
                        medfim3 = (notaProv3 + notaTrab3) / 2;
                        txtMedFim3.setText(String.valueOf(medfim3));

                        if (medfim3 >= 7) txtSitFim3.setText("APROVADO");
                        else txtSitFim3.setText("REPROVADO");
                    }

                    // convertendo as minhas variáveis
                    notaProv3 = Double.parseDouble(edtProv3.getText().toString());
                    notaTrab3 = Double.parseDouble(edtTrab3.getText().toString());

                    sharedPerferences();

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

    private void sharedPerferences() {
        SharedPreferences medEscPref =
                getSharedPreferences(MainActivity.SHARED_PREF, 0);
        SharedPreferences.Editor medEsc =
                medEscPref.edit();

        medEsc.putString("txtMedFim3", txtMedFim3.getText().toString());
        medEsc.putString("txtSitFim3", txtSitFim3.getText().toString());
        medEsc.putString("notaProv3", String.valueOf(notaProv3));
        medEsc.putString("notaTrab3", String.valueOf(notaTrab3));
        medEsc.putString("medfim3", String.valueOf(medfim3));
        medEsc.putString("txtmat3", String.valueOf(selectedPosition));
        medEsc.putBoolean("bi3", true);

        medEsc.commit();

    }
}

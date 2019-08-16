package com.example.mediaescolar;

import android.app.AlertDialog;
import android.content.Intent;
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

import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.util.Arrays;

import static com.example.mediaescolar.MainActivity.SHARED_PREF;

public class QuartoBiActivity extends AppCompatActivity {

// Declarando os tipos de variáveis

    TextView txtTit, txtMat4, txtProv4, txtTrab4, txtMed4, txtSit4, txtMedFim4, txtSitFim4;
    EditText edtProv4, edtTrab4;
    Button btnCalc4;
    Spinner s;

    double notaProv4, notaTrab4, medfim4;
    int selectedPosition;
    boolean ok = true;

    private String array_spinner[];
    private AlertDialog info;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quarto_bi);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //todo consertar nullpointer msg
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("Voltar");

// Instanciando elementos do layout

        s = findViewById(R.id.Spinner04);
        txtTit = findViewById(R.id.txtBI4);
        txtMat4 = findViewById(R.id.txtMat4);
        txtProv4 = findViewById(R.id.txtProv4);
        txtTrab4 = findViewById(R.id.txtTrab4);
        edtProv4 = findViewById(R.id.edtProv4);
        edtTrab4 = findViewById(R.id.edtTrab4);
        txtMed4 = findViewById(R.id.txtMed4);
        txtSit4 = findViewById(R.id.txtSit4);
        txtMedFim4 = findViewById(R.id.txtMedFim4);
        txtSitFim4 = findViewById(R.id.txtSitFim4);
        btnCalc4 = findViewById(R.id.btnCalc4);

// Inserção de um novo tipo de fonte no layout.

        Typeface font = Typeface.createFromAsset(getAssets(),
                "print_bold_tt.ttf");

        txtTit.setTypeface(font);
        txtMat4.setTypeface(font);
        txtProv4.setTypeface(font);
        txtTrab4.setTypeface(font);
        edtProv4.setTypeface(font);
        edtTrab4.setTypeface(font);
        txtMed4.setTypeface(font);
        txtSit4.setTypeface(font);
        txtMedFim4.setTypeface(font);
        txtSitFim4.setTypeface(font);
        btnCalc4.setTypeface(font);

// Aqui é toda a declaração das listas do dropbox com o nome das matérias

        array_spinner = new String[]{Constant.MATEMATICA, Constant.BIOLOGIA, Constant.FILOSOFIA, Constant.FISICA, Constant.GEOGRAFIA, Constant.HISTORIA,
                Constant.INGLES, Constant.LITERATURA, Constant.PORTUGUES, Constant.QUIMICA, Constant.SOCIOLOGIA, Constant.ARTES, Constant.ESPORTES};
        Arrays.sort(array_spinner);

// restringindo o tamanho do spinner

        try {
            Field popup = Spinner.class.getDeclaredField("mPopup");
            popup.setAccessible(true);

            // Get private mPopup member variable and try cast to ListPopupWindow
            android.widget.ListPopupWindow popupWindow = (android.widget.ListPopupWindow) popup.get(s);

            // Set popupWindow height to 500px
            popupWindow.setHeight(500);
        } catch (NoClassDefFoundError | ClassCastException | NoSuchFieldException | IllegalAccessException e) {
            // silently fail...
        }

        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.spinner_materia, array_spinner);
        s.setAdapter(adapter);

        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                selectedPosition = position;

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnCalc4.setOnClickListener(new View.OnClickListener() {
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

                    if (edtProv4.getText().toString().length() > 0) {
                        notaProv4 = Double.parseDouble(edtProv4.getText().toString());

                        if (notaProv4 > 10) {
                            ok = false;
                            Toast.makeText(getApplicationContext(), "Nota Invalida", Toast.LENGTH_SHORT).show();
                            info_notas();
                            edtProv4.requestFocus();


                        } else {
                            ok = true;
                        }

                    } else {
                        edtProv4.setError("Campo obrigatório", ic);
                        edtProv4.requestFocus();
                        ok = false;
                    }
                    if (edtTrab4.getText().toString().length() > 0) {
                        notaTrab4 = Double.parseDouble(edtTrab4.getText().toString());

                        if (notaTrab4 > 10) {
                            ok = true;
                            Toast.makeText(getApplicationContext(), "Nota Invalida", Toast.LENGTH_SHORT).show();
                            info_notas();
                            edtTrab4.requestFocus();

                        } else {
                            ok = true;
                        }

                    } else {
                        edtTrab4.setError("Campo obrigatório", ic);
                        edtTrab4.requestFocus();
                        ok = false;
                    }

//Aquisitando notas digitadas
                    if (ok) {
                        medfim4 = (notaProv4 + notaTrab4) / 2;

                        DecimalFormat fmt = new DecimalFormat("0.0");
                        String string = fmt.format(medfim4);
                        String[] part = string.split("[,]");
                        String r4 = part[0] + "." + part[1];

                        txtMedFim4.setText(r4);

                        if (medfim4 >= 7) txtSitFim4.setText("APROVADO");
                        else txtSitFim4.setText("REPROVADO");
                    }

                    // convertendo as minhas variáveis
                    notaProv4 = Double.parseDouble(edtProv4.getText().toString());
                    notaTrab4 = Double.parseDouble(edtTrab4.getText().toString());

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
                Snackbar.make(view, "Dados apagados", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                clearBi4();
            }
        });

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                startActivity(new Intent(this, MainActivity.class));
                finishAffinity();
                break;
            default:
                break;
        }
        return true;
    }

    public void info_notas() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.important);
        builder.setMessage(R.string.info_nota);
        info = builder.create();
        info.show();
    }


    private void sharedPerferences() {
        SharedPreferences medEscPref =
                getSharedPreferences(MainActivity.SHARED_PREF, 0);
        SharedPreferences.Editor medEsc =
                medEscPref.edit();

        medEsc.putString("txtMedFim4", txtMedFim4.getText().toString());
        medEsc.putString("txtSitFim4", txtSitFim4.getText().toString());
        medEsc.putString("notaProv4", String.valueOf(notaProv4));
        medEsc.putString("notaTrab4", String.valueOf(notaTrab4));
        medEsc.putString("medfim4", String.valueOf(medfim4));
        medEsc.putString("txtmat4", String.valueOf(selectedPosition));
        medEsc.putBoolean("bi4", true);

        medEsc.commit();

    }

    private void clearBi4() {

        edtProv4.setText("");
        edtTrab4.setText("");
        txtSitFim4.setText("Sua situação é:");
        txtMedFim4.setText("");
        s.setSelection(0);
        medfim4 = 0;
        notaTrab4 = 0;
        notaProv4 = 0;

        clearSharedPreferences();
    }

    private void clearSharedPreferences() {
        SharedPreferences medEscPref = getSharedPreferences(SHARED_PREF, 0);
        SharedPreferences.Editor editor = medEscPref.edit();
        editor.clear();
        editor.commit();
    }


}

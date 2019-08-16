package com.example.mediaescolar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.Arrays;


public class MainActivity extends AppCompatActivity {

    Button btn1Bi, btn2Bi, btn3Bi, btn4Bi, btnResFim;

    String txtMedFim1, txtSitFim1, txtMedFim4, txtSitFim4, txtMedFim2,
            txtSitFim2, txtMedFim3, txtSitFim3;

    double notaProv1, notaTrab1, medfim1, notaProv2, notaTrab2, medfim2,
            notaProv3, notaTrab3, medfim3, notaProv4, notaTrab4, medfim4,
            resFim;

    int txtmat1 = 0, txtmat2 = 0, txtmat3 = 0, txtmat4 = 0;

    boolean bi1, bi2, bi3, bi4;

    public static final String SHARED_PREF = "medEscPref";

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

        btn2Bi.setEnabled(bi2);
        btn3Bi.setEnabled(bi3);
        btn4Bi.setEnabled(bi4);

        clearSharedPreferences();

        btn1Bi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nxScreen =
                        new Intent(MainActivity.this, PrimeiroBiActivity.class);
                startActivity(nxScreen);
            }
        });

        btn2Bi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nxScreen =
                        new Intent(MainActivity.this, SegundoBiActivity.class);
                startActivity(nxScreen);

            }
        });

        btn3Bi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nxScreen =
                        new Intent(MainActivity.this, TerceiroBiActivity.class);
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

                DecimalFormat fmt2 = new DecimalFormat("0.0");
                String string2 = fmt2.format(resFim);
                String[] part2 = string2.split("[,]");
                String r = part2[0] + "." + part2[1];

                if (resFim > 7) {
                    Toast.makeText(getApplicationContext(), "Aluno aprovado por média", Toast.LENGTH_LONG).show();
                    String test = " sua média final é:  " + (r);//==> uma forma de limpar o código
                    btnResFim.setText(test);
                } else {
                    Toast.makeText(getApplicationContext(), "Aluno reprovado por média", Toast.LENGTH_LONG).show();
                    String test = " sua média final é:  " + (r);//==> uma forma de limpar o código
                    btnResFim.setText(test);

                }
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Dados apagados ", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                clearSharedPreferences();
            }
        });

        result();
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

    @Override
    public void onResume() {
        super.onResume();

        readPref();
        result();

    }



    private void readPref() {
        SharedPreferences medEscPref =
                getSharedPreferences(MainActivity.SHARED_PREF, 0);

        bi1 = medEscPref.getBoolean("bi1", false);
        txtmat1 = Integer.parseInt(medEscPref.getString("txtmat1", "0"));
        txtSitFim1 = medEscPref.getString("txtSitFim1", "");
        txtMedFim1 = medEscPref.getString("txtMedFim1", "");
        notaTrab1 = Double.parseDouble(medEscPref.getString("notaTrab1", "0.0"));
        notaProv1 = Double.parseDouble(medEscPref.getString("notaProv1", "0.0"));
        medfim1 = Double.parseDouble(medEscPref.getString("medfim1", "0.0"));

        bi2 = medEscPref.getBoolean("bi2", false);
        txtmat2 = Integer.parseInt(medEscPref.getString("txtmat2", "0"));
        txtSitFim2 = medEscPref.getString("txtSitFim2", "");
        txtMedFim2 = medEscPref.getString("txtMedFim2", "");
        notaTrab2 = Double.parseDouble(medEscPref.getString("notaTrab2", "0.0"));
        notaProv2 = Double.parseDouble(medEscPref.getString("notaProv2", "0.0"));
        medfim2 = Double.parseDouble(medEscPref.getString("medfim2", "0.0"));

        bi3 = medEscPref.getBoolean("bi3", false);
        txtmat3 = Integer.parseInt(medEscPref.getString("txtmat3", "0"));
        txtSitFim3 = medEscPref.getString("txtSitFim3", "");
        txtMedFim3 = medEscPref.getString("txtMedFim3", "");
        notaTrab3 = Double.parseDouble(medEscPref.getString("notaTrab3", "0.0"));
        notaProv3 = Double.parseDouble(medEscPref.getString("notaProv3", "0.0"));
        medfim3 = Double.parseDouble(medEscPref.getString("medfim3", "0.0"));

        bi4 = medEscPref.getBoolean("bi4", false);
        txtmat4 = Integer.parseInt(medEscPref.getString("txtmat4", "0"));
        txtSitFim4 = medEscPref.getString("txtSitFim4", "");
        txtMedFim4 = medEscPref.getString("txtMedFim4", "");
        notaTrab4 = Double.parseDouble(medEscPref.getString("notaTrab4", "0.0"));
        notaProv4 = Double.parseDouble(medEscPref.getString("notaProv4", "0.0"));
        medfim4 = Double.parseDouble(medEscPref.getString("medfim4", "0.0"));
    }

    private void result() {

//declaração e instancia do array

        String array_spinner[];

        array_spinner = new String[]{Constant.MATEMATICA, Constant.BIOLOGIA, Constant.FILOSOFIA, Constant.FISICA, Constant.GEOGRAFIA, Constant.HISTORIA,
                Constant.INGLES, Constant.LITERATURA, Constant.PORTUGUES, Constant.QUIMICA, Constant.SOCIOLOGIA, Constant.ARTES, Constant.ESPORTES};
        Arrays.sort(array_spinner);

        if (bi1) {

// limitando o número de casas decimais e transformando , em .

            DecimalFormat fmt = new DecimalFormat("0.0");
            String string = fmt.format(medfim1);
            String[] part = string.split("[,]");
            String r1 = part[0] + "." + part[1];

            btn1Bi.setText(array_spinner[txtmat1] + " - 1º Bimestre " + " - " + txtSitFim1 + " - Nota " + r1); //==> aquisição da posição e valor  inserido no arry para leitura de shared pref
            btn1Bi.setEnabled(false);
            btn1Bi.setTextColor(getResources().getColor(R.color.text_desable));
            btn2Bi.setEnabled(bi1);
            btn2Bi.setTextColor(getResources().getColor(R.color.text_enable));
        }

        if (bi2) {

            DecimalFormat fmt = new DecimalFormat("0.0");
            String string = fmt.format(medfim2);
            String[] part = string.split("[,]");
            String r2 = part[0] + "." + part[1];

            btn2Bi.setText(array_spinner[txtmat2] + " - 2º Bimestre " + " - " + txtSitFim2 + " - Nota " + r2); //==> aquisição da posição e valor  inserido no arry para leitura de shared pref
            btn2Bi.setEnabled(false);
            btn2Bi.setTextColor(getResources().getColor(R.color.text_desable));
            btn3Bi.setEnabled(bi2);
            btn3Bi.setTextColor(getResources().getColor(R.color.text_enable));
        }

        if (bi3) {

            DecimalFormat fmt = new DecimalFormat("0.0");
            String string = fmt.format(medfim3);
            String[] part = string.split("[,]");
            String r3 = part[0] + "." + part[1];

            btn3Bi.setText(array_spinner[txtmat3] + " - 3º Bimestre " + " - " + txtSitFim3 + " - Nota " + r3); //==> aquisição da posição e valor  inserido no arry para leitura de shared pref
            btn3Bi.setEnabled(false);
            btn3Bi.setTextColor(getResources().getColor(R.color.text_desable));
            btn4Bi.setEnabled(bi3);
            btn4Bi.setTextColor(getResources().getColor(R.color.text_enable));
        }

        if (bi4) {

            DecimalFormat fmt = new DecimalFormat("0.0");
            String string = fmt.format(medfim4);
            String[] part = string.split("[,]");
            String r4 = part[0] + "." + part[1];

            btn4Bi.setText(array_spinner[txtmat4] + " - 4º Bimestre " + " - " + txtSitFim4 + " - Nota " + r4); //==> aquisição da posição e valor  inserido no arry para leitura de shared pref
            btn4Bi.setEnabled(false);
            btn4Bi.setTextColor(getResources().getColor(R.color.text_desable));
            btnResFim.setEnabled(true);
            btnResFim.setTextColor(getResources().getColor(R.color.text_enable));

            resFim = (medfim1 + medfim2 + medfim3 + medfim4) / 4;

            DecimalFormat fmt2 = new DecimalFormat("0.0");
            String string2 = fmt2.format(resFim);
            String[] part2 = string2.split("[,]");
            String r = part2[0] + "." + part2[1];

            if (resFim > 7) {
                Toast.makeText(getApplicationContext(), "Aluno aprovado por média", Toast.LENGTH_LONG).show();
                String test = " sua média final é:  " + (r);//==> uma forma de limpar o código
                btnResFim.setText(test);
            } else {
                Toast.makeText(getApplicationContext(), "Aluno reprovado por média", Toast.LENGTH_LONG).show();
                String test = " sua média final é:  " + (r);//==> uma forma de limpar o código
                btnResFim.setText(test);

            }
        }
    }

    private void clearSharedPreferences() {
        SharedPreferences medEscPref = getSharedPreferences(SHARED_PREF, 0);
        SharedPreferences.Editor editor = medEscPref.edit();
        editor.clear();
        editor.commit();
        clearMenu();
    }

    private void clearMenu() {

        btnResFim.setEnabled(false);
        btn4Bi.setEnabled(false);
        btn3Bi.setEnabled(false);
        btn2Bi.setEnabled(false);
        btn1Bi.setEnabled(true);

        btnResFim.setText("RESULTADO FINAL");
        btn4Bi.setText("4º Bimestre");
        btn3Bi.setText("3º Bimestre");
        btn2Bi.setText("2º Bimestre");
        btn1Bi.setText("1º Bimestre");

        btn1Bi.setTextColor(getResources().getColor(R.color.text_enable));
        btn2Bi.setTextColor(getResources().getColor(R.color.text_desable));
        btn3Bi.setTextColor(getResources().getColor(R.color.text_desable));
        btn4Bi.setTextColor(getResources().getColor(R.color.text_desable));
        btnResFim.setTextColor(getResources().getColor(R.color.text_desable));
    }
}
package com.example.appimc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText etPeso;
    private EditText etAltura;
    private TextView tvResultado;
    private TextView tvResultadoDecimal;
    private Button btnCalcular;
    private Button btnLimpar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //mapeamento dos botões
        etPeso = (EditText) findViewById(R.id.etPeso);
        etAltura = (EditText) findViewById(R.id.etAltura);
        tvResultado = (TextView) findViewById(R.id.tvResultado);
        btnCalcular = (Button) findViewById(R.id.btCalcular);
        btnLimpar = (Button) findViewById(R.id.btLimpar);
        tvResultadoDecimal = (TextView) findViewById(R.id.tvResultadoDecimal);

        btnLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnLimparOnClick();
            }
        });
        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnCalcularOnClick();
            }
        });
        btnLimpar.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(getApplicationContext(), "Limpa a tela ", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        btnCalcular.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(getApplicationContext(), "Calcula o seu IMC", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }
    private void btnLimparOnClick(){
        tvResultado.setText("Resultado do seu IMC");
        etPeso.setText("");
        etAltura.setText("");
        tvResultadoDecimal.setText("0.0");
    }
    private void btnCalcularOnClick(){
        if (etPeso.getText().toString().equals("")) {
            Toast.makeText(this, getString(R.string.erropeso), Toast.LENGTH_LONG).show();
            etPeso.requestFocus();
            return;
        }
        if (etAltura.getText().toString().equals("")) {
            Toast.makeText(getApplicationContext(), getString(R.string.erroaltura), Toast.LENGTH_LONG).show();
            etAltura.requestFocus();
            return;
        }

        Double peso = Double.parseDouble(etPeso.getText().toString());
        Double altura = Double.parseDouble(etAltura.getText().toString());
        Double imc = peso / (altura * altura);

        if(imc <= 18.5)
            tvResultado.setText("Baixo Peso");
        else if(imc > 18.5 && imc < 25)
            tvResultado.setText("Peso Normal");
        else if(imc >= 25 && imc < 30)
            tvResultado.setText("Excesso de peso");
        else if(imc >= 30)
            tvResultado.setText("Obesidade");
        else
            tvResultado.setText("erro");

        tvResultadoDecimal.setText(imc.toString());
    }
}
package com.example.gastosapp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.gastosapp.R;
import com.example.gastosapp.model.Gasto;

import java.io.Serializable;

public class FormGastoActivity extends AppCompatActivity {

    private EditText editValor;
    private EditText editData;
    private EditText editDescricao;
    private EditText editCategoria;
    private EditText editFormaPagamento;
    private Button formButtonSalva;
    private Button buttonSalvar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_gasto);

        carregaCampos();

        buttonSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Gasto gasto =  pegaGastosDoFormulario();
                Intent intent = new Intent(FormGastoActivity.this,
                        MainActivity.class);
                intent.putExtra("SALVAR_GASTO", gasto);
                setResult(2,intent);
                finish();
            }
        });

    }

  private Gasto pegaGastosDoFormulario() {
        double valor = Double.parseDouble(editValor.getText().toString());
        String data = editData.getText().toString();
        String descricao = editDescricao.getText().toString();
        String categoria = editCategoria.getText().toString();
        String formaPagamento = editFormaPagamento.getText().toString();

        Gasto gasto = new Gasto(valor,data,descricao,categoria,formaPagamento);

        return gasto;
    }
    
    private void carregaCampos() {
        editValor = findViewById(R.id.formeditGasto);
        editData = findViewById(R.id.formeditData);
        editDescricao = findViewById(R.id.formeeditDescricao);
        editCategoria = findViewById(R.id.formeditCategoria);
        editFormaPagamento = findViewById(R.id.formeditFormaPagamento);
        formButtonSalva = findViewById(R.id.formButtonSalva);
    }
}

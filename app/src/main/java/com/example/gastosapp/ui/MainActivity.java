package com.example.gastosapp.ui;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.gastosapp.R;
import com.example.gastosapp.dao.GastoDAo;
import com.example.gastosapp.model.Gasto;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView textGastos;
    private ListView listViewGasto;
    private FloatingActionButton fabNovoGasto;
    private  ArrayAdapter<Gasto> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

     configuraListView();
        clickDoButton();

    }


    private void clickDoButton() {
        fabNovoGasto.findViewById(R.id.fabNovoGastos);

      fabNovoGasto.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Intent intent = new Intent(getApplicationContext(), FormGastoActivity.class);
              startActivityForResult(intent,1);
              clickDoButton();
          }
      });
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1 && resultCode==2 && data.hasExtra("salva gasto")){
           // salvaGastosFormulario();
            Gasto gasto =(Gasto)data.getSerializableExtra("salva Gasto");
            new GastoDAo().inseri(gasto);

            adapter.notifyDataSetChanged();
        }
    }


    private void configuraListView() {
        listViewGasto = findViewById(R.id.mainListViewGastos);
        adapter = new ArrayAdapter<Gasto>(this,
                android.R.layout.simple_list_item_1,new GastoDAo().recuperTodosGastos());
        listViewGasto.setAdapter(adapter);
    }

    private void salvaGastosFormulario() {
        Intent intent = getIntent();
        Gasto gasto = (Gasto)intent.getSerializableExtra("Salva_Gastos");

        new GastoDAo().inseri(gasto);
    }
}

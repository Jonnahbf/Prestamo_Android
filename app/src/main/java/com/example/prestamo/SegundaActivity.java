package com.example.prestamo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.net.Inet4Address;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SegundaActivity extends AppCompatActivity {

    private Spinner interes;
    private TextView monto_a_pagar;
    private EditText monto;
    private EditText plazo;
    private TextView cuota;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda);
        String date = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
        TextView fecha = findViewById(R.id.textView_fecha);
        plazo = findViewById(R.id.editText_plazo);
        monto = findViewById(R.id.editText_monto);
        monto_a_pagar = findViewById(R.id.textViewMontoPagar);
        interes = findViewById(R.id.spinner_interes);
        cuota = findViewById(R.id.textViewMontoCuota);
        fecha.setText(date);
        plazo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                pago();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        //On TextChange en el campo monto
        monto.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                pago();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        plazo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                pago();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        interes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                pago();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    public void pago(){
        int aux = 0;
        if(monto.getText().toString().length()!=0)
            aux=Integer.valueOf(monto.getText().toString());
        int aux_interes = Integer.valueOf(interes.getSelectedItem().toString());
        //Log.d("interes", interes.getSelectedItem().toString());
        Double total = Double.valueOf(aux + ((aux*aux_interes)/100));
        String monto_string = String.valueOf(total);
        monto_a_pagar.setText(monto_string);
        Double aux_plazo = 0.0;
        if(plazo.getText().toString().length()!=0){
            int plazo_aux = Integer.valueOf(plazo.getText().toString());
            aux_plazo = Double.valueOf((total) / (plazo_aux));
            cuota.setText(String.valueOf(aux_plazo));

        }
        else{
            cuota.setText(String.valueOf(total));
        }



    }

}

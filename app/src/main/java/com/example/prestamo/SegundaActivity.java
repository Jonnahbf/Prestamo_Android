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
import java.util.Calendar;
import java.util.GregorianCalendar;

import java.net.Inet4Address;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class SegundaActivity extends AppCompatActivity {

    private Spinner interes;
    private TextView monto_a_pagar;
    private EditText monto;
    private EditText plazo;
    private TextView cuota;
    private TextView fecha_fin;
    private String date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda);
        date = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
        TextView fecha = findViewById(R.id.textView_fecha); //Obtenemos el TextView de fecha
        plazo = findViewById(R.id.editText_plazo); //Obtenemos el EditText de plazo
        monto = findViewById(R.id.editText_monto); //Obtenemos el EditText de monto
        monto_a_pagar = findViewById(R.id.textViewMontoPagar); //Obtenemos el TextView de Monto a pagar
        interes = findViewById(R.id.spinner_interes); //Obtenemos el spinner de interes
        fecha_fin = findViewById(R.id.textView_fechaFin); //Obtenemos el TextView de fecha fin
        cuota = findViewById(R.id.textViewMontoCuota); //Obtenemos el TextView de cuota
        fecha.setText(date);
       fecha_de_pago();
        plazo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                pago();
                fecha_de_pago();
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
        int aux = 0; //Variable auxiliar que almacenara el valor ingresado en monto
        if(monto.getText().toString().length()!=0) //Verificamos que el EditText no este en 0
            aux=Integer.valueOf(monto.getText().toString());
        int aux_interes = Integer.valueOf(interes.getSelectedItem().toString()); //Variable auxiliar que almacenara el interes que se le aplicara al monto
        int plazo_interes = 0;
        if(plazo.getText().length()!=0){
            plazo_interes = Integer.valueOf(plazo.getText().toString());
        }
        int interes_final = ((aux*aux_interes)/100) * plazo_interes;
        Double total = Double.valueOf(aux + interes_final); //Creamos una variable que almacenara el monto total ya con el interes calculado
        String monto_string = String.valueOf(total); //Convertimos el total a string para poder imprimirlo en un TextView
        monto_a_pagar.setText(monto_string); //Escribimos el total ya convertido a String en el TextView
        Double aux_plazo = 0.0; //Definimos una variable Double que almacenara la cantidad de dinero que tendra que abonar el cliente por mes
        if(plazo.getText().toString().length()!=0){ //Verificamos que el plazo no sea 0
            int plazo_aux = Integer.valueOf(plazo.getText().toString()); //Obtenemos el plazo que se ha escogido en el spinner
            aux_plazo = Double.valueOf((total) / (plazo_aux)); //Dividimos el total/plazo para obtener las cuotas que dara el cliente mensual
            cuota.setText(String.valueOf(aux_plazo)); //Mostramos el valor del plazo en un TextView

        }
        else{
            cuota.setText(String.valueOf(total)); //Si el plazo es 0, el cliente tendra que pagar el total en una sola cuota
        }
    }

    public void fecha_de_pago(){
        Calendar fechaActual = Calendar.getInstance();
        String fecha_hoy;
        int año = fechaActual.get(Calendar.YEAR);
        int mes = fechaActual.get(Calendar.MONTH);
        mes++;
        int aux;
        int dia = fechaActual.get(Calendar.DAY_OF_MONTH);
        if(plazo.getText().toString().isEmpty()){
            mes++;
            fecha_hoy = (String.valueOf(dia)) + "/" + (String.valueOf(mes)) + "/" + (String.valueOf(año));
            fecha_fin.setText(fecha_hoy);
        }
        else{
            aux = Integer.valueOf(plazo.getText().toString());
            fechaActual.add(Calendar.MONTH, aux);
            año = fechaActual.get(Calendar.YEAR);
            mes = fechaActual.get(Calendar.MONTH);
            mes++;
            dia = fechaActual.get(Calendar.DAY_OF_MONTH);
            fecha_hoy = (String.valueOf(dia)) + "/" + (String.valueOf(mes)) + "/" + (String.valueOf(año));
            fecha_fin.setText(fecha_hoy);
        }

    }

}

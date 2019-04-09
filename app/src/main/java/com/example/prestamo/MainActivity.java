package com.example.prestamo;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View v){
        EditText nombre = findViewById(R.id.editText_nombre);
        String cadena_nombre = nombre.getEditableText().toString();
        EditText telefono = findViewById(R.id.editText_telefono);
        String cadena_telefono = telefono.getEditableText().toString();
        EditText cedula = findViewById(R.id.editText_cedula);
        String cadena_cedula = cedula.getEditableText().toString();
        EditText direccion = findViewById(R.id.editText_direccion);
        String cadena_direccion = direccion.getEditableText().toString();
        if(cadena_nombre.isEmpty()){
            nombre.setError("Debe llenar este campo");
        }
        else if(cadena_telefono.isEmpty()){
            telefono.setError("Debe llenar este campo");
        }
        else if(cadena_cedula.isEmpty()){
            cedula.setError("Debe llenar este campo");
        }
        else if(cadena_direccion.isEmpty()){
            direccion.setError("Debe llenar este campo");
        }
        else{
            Intent inte = new Intent(getBaseContext(), SegundaActivity.class);
            startActivity(inte);
        }

    }
}

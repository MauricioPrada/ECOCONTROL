package com.ecocontrol.app.activity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.ecocontrol.app.R;
import com.ecocontrol.app.database.DatabaseHelper;

public class EmpresaActivity extends AppCompatActivity {

    private EditText etNombreEmpresa;
    private EditText etUbicacionEmpresa;
    private TextView tvListaEmpresas;

    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empresa);

        etNombreEmpresa = findViewById(R.id.etNombreEmpresa);
        etUbicacionEmpresa = findViewById(R.id.etUbicacionEmpresa);
        tvListaEmpresas = findViewById(R.id.tvListaEmpresas);

        Button btnGuardarEmpresa = findViewById(R.id.btnGuardarEmpresa);
        Button btnVolver = findViewById(R.id.btnVolver);

        databaseHelper = new DatabaseHelper(this);

        btnGuardarEmpresa.setOnClickListener(v -> guardarEmpresa());

        btnVolver.setOnClickListener(v -> finish());

        mostrarEmpresas();
    }

    private void guardarEmpresa() {

        String nombre = etNombreEmpresa.getText().toString().trim();
        String ubicacion = etUbicacionEmpresa.getText().toString().trim();

        if (nombre.isEmpty()) {
            etNombreEmpresa.setError("Ingrese el nombre de la empresa");
            return;
        }

        if (ubicacion.isEmpty()) {
            etUbicacionEmpresa.setError("Ingrese la ubicación");
            return;
        }

        long resultado = databaseHelper.insertarEmpresa(
                nombre,
                ubicacion
        );

        if (resultado != -1) {

            Toast.makeText(
                    this,
                    "Empresa registrada correctamente",
                    Toast.LENGTH_SHORT
            ).show();

            etNombreEmpresa.setText("");
            etUbicacionEmpresa.setText("");

            mostrarEmpresas();

        } else {

            Toast.makeText(
                    this,
                    "No fue posible registrar la empresa",
                    Toast.LENGTH_SHORT
            ).show();
        }
    }

    private void mostrarEmpresas() {

        Cursor cursor = databaseHelper.obtenerEmpresas();

        if (cursor.getCount() == 0) {
            tvListaEmpresas.setText("No hay empresas registradas.");
            cursor.close();
            return;
        }

        StringBuilder texto = new StringBuilder();

        while (cursor.moveToNext()) {

            int id = cursor.getInt(
                    cursor.getColumnIndexOrThrow("id_empresa")
            );

            String nombre = cursor.getString(
                    cursor.getColumnIndexOrThrow("nombre")
            );

            String ubicacion = cursor.getString(
                    cursor.getColumnIndexOrThrow("ubicacion")
            );

            texto.append("ID: ")
                    .append(id)
                    .append("\n");

            texto.append("Empresa: ")
                    .append(nombre)
                    .append("\n");

            texto.append("Ubicación: ")
                    .append(ubicacion)
                    .append("\n\n");
        }

        cursor.close();

        tvListaEmpresas.setText(texto.toString());
    }
}
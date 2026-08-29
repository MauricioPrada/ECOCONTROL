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

public class MedicionActivity extends AppCompatActivity {

    private EditText etFechaMedicion;
    private EditText etNivelContaminacion;
    private EditText etIdFuenteMedicion;
    private TextView tvListaMediciones;

    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicion);

        etFechaMedicion = findViewById(R.id.etFechaMedicion);
        etNivelContaminacion = findViewById(R.id.etNivelContaminacion);
        etIdFuenteMedicion = findViewById(R.id.etIdFuenteMedicion);
        tvListaMediciones = findViewById(R.id.tvListaMediciones);

        Button btnGuardarMedicion = findViewById(R.id.btnGuardarMedicion);
        Button btnVolver = findViewById(R.id.btnVolver);

        databaseHelper = new DatabaseHelper(this);

        btnGuardarMedicion.setOnClickListener(v -> guardarMedicion());

        btnVolver.setOnClickListener(v -> finish());

        mostrarMediciones();
    }

    private void guardarMedicion() {

        String fecha = etFechaMedicion.getText().toString().trim();
        String nivelTexto = etNivelContaminacion.getText().toString().trim();
        String idFuenteTexto = etIdFuenteMedicion.getText().toString().trim();

        if (fecha.isEmpty()) {
            etFechaMedicion.setError("Ingrese la fecha");
            return;
        }

        if (nivelTexto.isEmpty()) {
            etNivelContaminacion.setError("Ingrese el nivel de contaminación");
            return;
        }

        if (idFuenteTexto.isEmpty()) {
            etIdFuenteMedicion.setError("Ingrese el ID de la fuente");
            return;
        }

        double nivelContaminacion;
        int idFuente;

        try {
            nivelContaminacion = Double.parseDouble(nivelTexto);
        } catch (NumberFormatException e) {
            etNivelContaminacion.setError("Ingrese un nivel válido");
            return;
        }

        try {
            idFuente = Integer.parseInt(idFuenteTexto);
        } catch (NumberFormatException e) {
            etIdFuenteMedicion.setError("Ingrese un ID válido");
            return;
        }

        long resultado = databaseHelper.insertarMedicion(
                fecha,
                nivelContaminacion,
                idFuente
        );

        if (resultado != -1) {

            Toast.makeText(
                    this,
                    "Medición registrada correctamente",
                    Toast.LENGTH_SHORT
            ).show();

            etFechaMedicion.setText("");
            etNivelContaminacion.setText("");
            etIdFuenteMedicion.setText("");

            mostrarMediciones();

        } else {

            Toast.makeText(
                    this,
                    "No fue posible registrar la medición",
                    Toast.LENGTH_SHORT
            ).show();
        }
    }

    private void mostrarMediciones() {

        Cursor cursor = databaseHelper.obtenerMediciones();

        if (cursor.getCount() == 0) {
            tvListaMediciones.setText("No hay mediciones registradas.");
            cursor.close();
            return;
        }

        StringBuilder texto = new StringBuilder();

        while (cursor.moveToNext()) {

            int id = cursor.getInt(
                    cursor.getColumnIndexOrThrow("id_medicion")
            );

            String fecha = cursor.getString(
                    cursor.getColumnIndexOrThrow("fecha")
            );

            double nivel = cursor.getDouble(
                    cursor.getColumnIndexOrThrow("nivel_contaminacion")
            );

            int idFuente = cursor.getInt(
                    cursor.getColumnIndexOrThrow("id_fuente")
            );

            texto.append("ID: ")
                    .append(id)
                    .append("\n");

            texto.append("Fecha: ")
                    .append(fecha)
                    .append("\n");

            texto.append("Nivel de contaminación: ")
                    .append(nivel)
                    .append("\n");

            texto.append("Fuente ID: ")
                    .append(idFuente)
                    .append("\n\n");
        }

        cursor.close();

        tvListaMediciones.setText(texto.toString());
    }
}
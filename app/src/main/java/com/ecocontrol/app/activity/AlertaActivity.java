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

public class AlertaActivity extends AppCompatActivity {

    private EditText etTipoAlerta;
    private EditText etFechaAlerta;
    private EditText etIdMedicionAlerta;
    private TextView tvListaAlertas;

    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alerta);

        etTipoAlerta = findViewById(R.id.etTipoAlerta);
        etFechaAlerta = findViewById(R.id.etFechaAlerta);
        etIdMedicionAlerta = findViewById(R.id.etIdMedicionAlerta);
        tvListaAlertas = findViewById(R.id.tvListaAlertas);

        Button btnGuardarAlerta = findViewById(R.id.btnGuardarAlerta);
        Button btnVolver = findViewById(R.id.btnVolver);

        databaseHelper = new DatabaseHelper(this);

        btnGuardarAlerta.setOnClickListener(v -> guardarAlerta());

        btnVolver.setOnClickListener(v -> finish());

        mostrarAlertas();
    }

    private void guardarAlerta() {

        String tipoAlerta = etTipoAlerta.getText().toString().trim();
        String fecha = etFechaAlerta.getText().toString().trim();
        String idMedicionTexto =
                etIdMedicionAlerta.getText().toString().trim();

        if (tipoAlerta.isEmpty()) {
            etTipoAlerta.setError("Ingrese el tipo de alerta");
            return;
        }

        if (fecha.isEmpty()) {
            etFechaAlerta.setError("Ingrese la fecha");
            return;
        }

        if (idMedicionTexto.isEmpty()) {
            etIdMedicionAlerta.setError(
                    "Ingrese el ID de la medición"
            );
            return;
        }

        int idMedicion;

        try {
            idMedicion = Integer.parseInt(idMedicionTexto);
        } catch (NumberFormatException e) {
            etIdMedicionAlerta.setError("Ingrese un ID válido");
            return;
        }

        long resultado = databaseHelper.insertarAlerta(
                tipoAlerta,
                fecha,
                idMedicion
        );

        if (resultado != -1) {

            Toast.makeText(
                    this,
                    "Alerta registrada correctamente",
                    Toast.LENGTH_SHORT
            ).show();

            etTipoAlerta.setText("");
            etFechaAlerta.setText("");
            etIdMedicionAlerta.setText("");

            mostrarAlertas();

        } else {

            Toast.makeText(
                    this,
                    "No fue posible registrar la alerta",
                    Toast.LENGTH_SHORT
            ).show();
        }
    }

    private void mostrarAlertas() {

        Cursor cursor = databaseHelper.obtenerAlertas();

        if (cursor.getCount() == 0) {
            tvListaAlertas.setText("No hay alertas registradas.");
            cursor.close();
            return;
        }

        StringBuilder texto = new StringBuilder();

        while (cursor.moveToNext()) {

            int id = cursor.getInt(
                    cursor.getColumnIndexOrThrow("id_alerta")
            );

            String tipo = cursor.getString(
                    cursor.getColumnIndexOrThrow("tipo_alerta")
            );

            String fecha = cursor.getString(
                    cursor.getColumnIndexOrThrow("fecha")
            );

            int idMedicion = cursor.getInt(
                    cursor.getColumnIndexOrThrow("id_medicion")
            );

            texto.append("ID: ")
                    .append(id)
                    .append("\n");

            texto.append("Tipo: ")
                    .append(tipo)
                    .append("\n");

            texto.append("Fecha: ")
                    .append(fecha)
                    .append("\n");

            texto.append("Medición ID: ")
                    .append(idMedicion)
                    .append("\n\n");
        }

        cursor.close();

        tvListaAlertas.setText(texto.toString());
    }
}
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

public class FuenteActivity extends AppCompatActivity {

    private EditText etTipoFuente;
    private EditText etDescripcionFuente;
    private EditText etIdEmpresaFuente;
    private TextView tvListaFuentes;

    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fuente);

        etTipoFuente = findViewById(R.id.etTipoFuente);
        etDescripcionFuente = findViewById(R.id.etDescripcionFuente);
        etIdEmpresaFuente = findViewById(R.id.etIdEmpresaFuente);
        tvListaFuentes = findViewById(R.id.tvListaFuentes);

        Button btnGuardarFuente = findViewById(R.id.btnGuardarFuente);
        Button btnVolver = findViewById(R.id.btnVolver);

        databaseHelper = new DatabaseHelper(this);

        btnGuardarFuente.setOnClickListener(v -> guardarFuente());

        btnVolver.setOnClickListener(v -> finish());

        mostrarFuentes();
    }

    private void guardarFuente() {

        String tipo = etTipoFuente.getText().toString().trim();
        String descripcion = etDescripcionFuente.getText().toString().trim();
        String idEmpresaTexto = etIdEmpresaFuente.getText().toString().trim();

        if (tipo.isEmpty()) {
            etTipoFuente.setError("Ingrese el tipo de fuente");
            return;
        }

        if (descripcion.isEmpty()) {
            etDescripcionFuente.setError("Ingrese una descripción");
            return;
        }

        if (idEmpresaTexto.isEmpty()) {
            etIdEmpresaFuente.setError("Ingrese el ID de la empresa");
            return;
        }

        int idEmpresa;

        try {
            idEmpresa = Integer.parseInt(idEmpresaTexto);
        } catch (NumberFormatException e) {
            etIdEmpresaFuente.setError("Ingrese un ID válido");
            return;
        }

        long resultado = databaseHelper.insertarFuente(
                tipo,
                descripcion,
                idEmpresa
        );

        if (resultado != -1) {

            Toast.makeText(
                    this,
                    "Fuente registrada correctamente",
                    Toast.LENGTH_SHORT
            ).show();

            etTipoFuente.setText("");
            etDescripcionFuente.setText("");
            etIdEmpresaFuente.setText("");

            mostrarFuentes();

        } else {

            Toast.makeText(
                    this,
                    "No fue posible registrar la fuente",
                    Toast.LENGTH_SHORT
            ).show();
        }
    }

    private void mostrarFuentes() {

        Cursor cursor = databaseHelper.obtenerFuentes();

        if (cursor.getCount() == 0) {
            tvListaFuentes.setText("No hay fuentes registradas.");
            cursor.close();
            return;
        }

        StringBuilder texto = new StringBuilder();

        while (cursor.moveToNext()) {

            int id = cursor.getInt(
                    cursor.getColumnIndexOrThrow("id_fuente")
            );

            String tipo = cursor.getString(
                    cursor.getColumnIndexOrThrow("tipo")
            );

            String descripcion = cursor.getString(
                    cursor.getColumnIndexOrThrow("descripcion")
            );

            int idEmpresa = cursor.getInt(
                    cursor.getColumnIndexOrThrow("id_empresa")
            );

            texto.append("ID: ")
                    .append(id)
                    .append("\n");

            texto.append("Tipo: ")
                    .append(tipo)
                    .append("\n");

            texto.append("Descripción: ")
                    .append(descripcion)
                    .append("\n");

            texto.append("Empresa ID: ")
                    .append(idEmpresa)
                    .append("\n\n");
        }

        cursor.close();

        tvListaFuentes.setText(texto.toString());
    }
}
package com.ecocontrol.app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.ecocontrol.app.activity.EmpresaActivity;
import com.ecocontrol.app.activity.FuenteActivity;
import com.ecocontrol.app.activity.MedicionActivity;
import com.ecocontrol.app.activity.AlertaActivity;
import com.ecocontrol.app.activity.ReporteActivity;
public class MainActivity extends AppCompatActivity {

    private Button btnEmpresas;
    private Button btnFuentes;
    private Button btnMediciones;
    private Button btnAlertas;
    private Button btnReportes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnEmpresas = findViewById(R.id.btnEmpresas);
        btnFuentes = findViewById(R.id.btnFuentes);
        btnMediciones = findViewById(R.id.btnMediciones);
        btnAlertas = findViewById(R.id.btnAlertas);
        btnReportes = findViewById(R.id.btnReportes);

        // Abrir módulo de Empresas
        btnEmpresas.setOnClickListener(v -> {
            Intent intent = new Intent(
                    MainActivity.this,
                    EmpresaActivity.class
            );
            startActivity(intent);
        });

        // Abrir módulo de Fuentes de contaminación
        btnFuentes.setOnClickListener(v -> {
            Intent intent = new Intent(
                    MainActivity.this,
                    FuenteActivity.class
            );
            startActivity(intent);
        });

        // Módulos que construiremos después
        btnMediciones.setOnClickListener(v -> {
            Intent intent = new Intent(
                    MainActivity.this,
                    MedicionActivity.class
            );
            startActivity(intent);
        });

        btnAlertas.setOnClickListener(v -> {
            Intent intent = new Intent(
                    MainActivity.this,
                    AlertaActivity.class
            );
            startActivity(intent);
        });

        btnReportes.setOnClickListener(v -> {
            Intent intent = new Intent(
                    MainActivity.this,
                    ReporteActivity.class
            );
            startActivity(intent);
        });
    }

    private void mostrarModuloPendiente(String modulo) {

        Toast.makeText(
                this,
                modulo + " - módulo en construcción",
                Toast.LENGTH_SHORT
        ).show();
    }
}
package com.ecocontrol.app.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.ecocontrol.app.R;
import com.ecocontrol.app.database.DatabaseHelper;

public class ReporteActivity extends AppCompatActivity {

    private TextView tvReporteEmpresas;
    private TextView tvReporteFuentes;
    private TextView tvReporteMediciones;
    private TextView tvReporteAlertas;
    private TextView tvEstadoGeneral;

    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reporte);

        tvReporteEmpresas = findViewById(R.id.tvReporteEmpresas);
        tvReporteFuentes = findViewById(R.id.tvReporteFuentes);
        tvReporteMediciones = findViewById(R.id.tvReporteMediciones);
        tvReporteAlertas = findViewById(R.id.tvReporteAlertas);
        tvEstadoGeneral = findViewById(R.id.tvEstadoGeneral);

        Button btnActualizarReporte = findViewById(R.id.btnActualizarReporte);
        Button btnVolver = findViewById(R.id.btnVolver);

        databaseHelper = new DatabaseHelper(this);

        btnActualizarReporte.setOnClickListener(v -> actualizarReporte());

        btnVolver.setOnClickListener(v -> finish());

        actualizarReporte();
    }

    private void actualizarReporte() {

        int empresas = databaseHelper.contarEmpresas();
        int fuentes = databaseHelper.contarFuentes();
        int mediciones = databaseHelper.contarMediciones();
        int alertas = databaseHelper.contarAlertas();

        tvReporteEmpresas.setText(
                "Empresas registradas: " + empresas
        );

        tvReporteFuentes.setText(
                "Fuentes registradas: " + fuentes
        );

        tvReporteMediciones.setText(
                "Mediciones registradas: " + mediciones
        );

        tvReporteAlertas.setText(
                "Alertas registradas: " + alertas
        );

        if (empresas == 0 &&
                fuentes == 0 &&
                mediciones == 0 &&
                alertas == 0) {

            tvEstadoGeneral.setText(
                    "No existen registros en el sistema."
            );

        } else {

            tvEstadoGeneral.setText(
                    "EcoControl cuenta con información registrada " +
                            "y disponible para seguimiento."
            );
        }

        Toast.makeText(
                this,
                "Reporte actualizado correctamente",
                Toast.LENGTH_SHORT
        ).show();
    }
}
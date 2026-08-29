package com.ecocontrol.app;

import com.ecocontrol.app.model.Alerta;
import com.ecocontrol.app.model.Empresa;
import com.ecocontrol.app.model.FuenteContaminacion;
import com.ecocontrol.app.model.Medicion;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class EcoControlUnitTest {

    @Test
    public void crearEmpresa_correctamente() {

        Empresa empresa = new Empresa(
                1,
                "EcoIndustria SAS",
                "Bogotá"
        );

        assertNotNull(empresa);
        assertEquals(1, empresa.getIdEmpresa());
        assertEquals("EcoIndustria SAS", empresa.getNombre());
        assertEquals("Bogotá", empresa.getUbicacion());
    }

    @Test
    public void crearFuenteContaminacion_correctamente() {

        FuenteContaminacion fuente = new FuenteContaminacion(
                1,
                "Emisión atmosférica",
                "Chimenea industrial",
                1
        );

        assertNotNull(fuente);
        assertEquals(1, fuente.getIdFuente());
        assertEquals("Emisión atmosférica", fuente.getTipo());
        assertEquals("Chimenea industrial", fuente.getDescripcion());
        assertEquals(1, fuente.getIdEmpresa());
    }

    @Test
    public void crearMedicion_correctamente() {

        Medicion medicion = new Medicion(
                1,
                "2026-08-29",
                75.5,
                1
        );

        assertNotNull(medicion);
        assertEquals(1, medicion.getIdMedicion());
        assertEquals("2026-08-29", medicion.getFecha());

        assertEquals(
                75.5,
                medicion.getNivelContaminacion(),
                0.01
        );

        assertEquals(1, medicion.getIdFuente());
    }

    @Test
    public void crearAlerta_correctamente() {

        Alerta alerta = new Alerta(
                1,
                "Nivel alto",
                "2026-08-29",
                1
        );

        assertNotNull(alerta);
        assertEquals(1, alerta.getIdAlerta());
        assertEquals("Nivel alto", alerta.getTipoAlerta());
        assertEquals("2026-08-29", alerta.getFecha());
        assertEquals(1, alerta.getIdMedicion());
    }
}
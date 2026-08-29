package com.ecocontrol.app.model;

public class Medicion {

    private int idMedicion;
    private String fecha;
    private double nivelContaminacion;
    private int idFuente;

    public Medicion() {
    }

    public Medicion(int idMedicion, String fecha,
                    double nivelContaminacion, int idFuente) {
        this.idMedicion = idMedicion;
        this.fecha = fecha;
        this.nivelContaminacion = nivelContaminacion;
        this.idFuente = idFuente;
    }

    public int getIdMedicion() {
        return idMedicion;
    }

    public void setIdMedicion(int idMedicion) {
        this.idMedicion = idMedicion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public double getNivelContaminacion() {
        return nivelContaminacion;
    }

    public void setNivelContaminacion(double nivelContaminacion) {
        this.nivelContaminacion = nivelContaminacion;
    }

    public int getIdFuente() {
        return idFuente;
    }

    public void setIdFuente(int idFuente) {
        this.idFuente = idFuente;
    }

    @Override
    public String toString() {
        return fecha + " - Nivel: " + nivelContaminacion;
    }
}

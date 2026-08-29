package com.ecocontrol.app.model;

public class Empresa {

    private int idEmpresa;
    private String nombre;
    private String ubicacion;

    public Empresa() {
    }

    public Empresa(int idEmpresa, String nombre, String ubicacion) {
        this.idEmpresa = idEmpresa;
        this.nombre = nombre;
        this.ubicacion = ubicacion;
    }

    public int getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(int idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    @Override
    public String toString() {
        return nombre + " - " + ubicacion;
    }
}
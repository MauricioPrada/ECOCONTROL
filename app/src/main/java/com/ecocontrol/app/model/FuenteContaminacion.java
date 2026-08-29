package com.ecocontrol.app.model;

public class FuenteContaminacion {

    private int idFuente;
    private String tipo;
    private String descripcion;
    private int idEmpresa;

    public FuenteContaminacion() {
    }

    public FuenteContaminacion(int idFuente, String tipo,
                               String descripcion, int idEmpresa) {
        this.idFuente = idFuente;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.idEmpresa = idEmpresa;
    }

    public int getIdFuente() {
        return idFuente;
    }

    public void setIdFuente(int idFuente) {
        this.idFuente = idFuente;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(int idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    @Override
    public String toString() {
        return tipo + " - " + descripcion;
    }
}
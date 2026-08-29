package com.ecocontrol.app.model;

public class Alerta {

    private int idAlerta;
    private String tipoAlerta;
    private String fecha;
    private int idMedicion;

    public Alerta() {
    }

    public Alerta(int idAlerta, String tipoAlerta,
                  String fecha, int idMedicion) {
        this.idAlerta = idAlerta;
        this.tipoAlerta = tipoAlerta;
        this.fecha = fecha;
        this.idMedicion = idMedicion;
    }

    public int getIdAlerta() {
        return idAlerta;
    }

    public void setIdAlerta(int idAlerta) {
        this.idAlerta = idAlerta;
    }

    public String getTipoAlerta() {
        return tipoAlerta;
    }

    public void setTipoAlerta(String tipoAlerta) {
        this.tipoAlerta = tipoAlerta;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getIdMedicion() {
        return idMedicion;
    }

    public void setIdMedicion(int idMedicion) {
        this.idMedicion = idMedicion;
    }

    @Override
    public String toString() {
        return tipoAlerta + " - " + fecha;
    }
}

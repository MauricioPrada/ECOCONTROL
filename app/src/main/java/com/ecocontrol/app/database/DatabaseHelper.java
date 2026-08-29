package com.ecocontrol.app.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "ecocontrol.db";
    private static final int DATABASE_VERSION = 1;

    // Tablas
    public static final String TABLA_EMPRESA = "empresa";
    public static final String TABLA_FUENTE = "fuente_contaminacion";
    public static final String TABLA_MEDICION = "medicion";
    public static final String TABLA_ALERTA = "alerta";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // Tabla Empresa
        db.execSQL(
                "CREATE TABLE " + TABLA_EMPRESA + " (" +
                        "id_empresa INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "nombre TEXT NOT NULL, " +
                        "ubicacion TEXT NOT NULL)"
        );

        // Tabla Fuente de contaminación
        db.execSQL(
                "CREATE TABLE " + TABLA_FUENTE + " (" +
                        "id_fuente INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "tipo TEXT NOT NULL, " +
                        "descripcion TEXT NOT NULL, " +
                        "id_empresa INTEGER NOT NULL)"
        );

        // Tabla Medición
        db.execSQL(
                "CREATE TABLE " + TABLA_MEDICION + " (" +
                        "id_medicion INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "fecha TEXT NOT NULL, " +
                        "nivel_contaminacion REAL NOT NULL, " +
                        "id_fuente INTEGER NOT NULL)"
        );

        // Tabla Alerta
        db.execSQL(
                "CREATE TABLE " + TABLA_ALERTA + " (" +
                        "id_alerta INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "tipo_alerta TEXT NOT NULL, " +
                        "fecha TEXT NOT NULL, " +
                        "id_medicion INTEGER NOT NULL)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLA_ALERTA);
        db.execSQL("DROP TABLE IF EXISTS " + TABLA_MEDICION);
        db.execSQL("DROP TABLE IF EXISTS " + TABLA_FUENTE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLA_EMPRESA);

        onCreate(db);
    }

    // ---------------- EMPRESAS ----------------

    public long insertarEmpresa(String nombre, String ubicacion) {

        SQLiteDatabase db = getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put("nombre", nombre);
        valores.put("ubicacion", ubicacion);

        return db.insert(TABLA_EMPRESA, null, valores);
    }

    public Cursor obtenerEmpresas() {

        SQLiteDatabase db = getReadableDatabase();

        return db.rawQuery(
                "SELECT * FROM " + TABLA_EMPRESA +
                        " ORDER BY id_empresa DESC",
                null
        );
    }

    // ---------------- FUENTES ----------------

    public long insertarFuente(
            String tipo,
            String descripcion,
            int idEmpresa) {

        SQLiteDatabase db = getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put("tipo", tipo);
        valores.put("descripcion", descripcion);
        valores.put("id_empresa", idEmpresa);

        return db.insert(TABLA_FUENTE, null, valores);
    }

    public Cursor obtenerFuentes() {

        SQLiteDatabase db = getReadableDatabase();

        return db.rawQuery(
                "SELECT * FROM " + TABLA_FUENTE +
                        " ORDER BY id_fuente DESC",
                null
        );
    }

    // ---------------- MEDICIONES ----------------

    public long insertarMedicion(
            String fecha,
            double nivelContaminacion,
            int idFuente) {

        SQLiteDatabase db = getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put("fecha", fecha);
        valores.put("nivel_contaminacion", nivelContaminacion);
        valores.put("id_fuente", idFuente);

        return db.insert(TABLA_MEDICION, null, valores);
    }

    public Cursor obtenerMediciones() {

        SQLiteDatabase db = getReadableDatabase();

        return db.rawQuery(
                "SELECT * FROM " + TABLA_MEDICION +
                        " ORDER BY id_medicion DESC",
                null
        );
    }

    // ---------------- ALERTAS ----------------

    public long insertarAlerta(
            String tipoAlerta,
            String fecha,
            int idMedicion) {

        SQLiteDatabase db = getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put("tipo_alerta", tipoAlerta);
        valores.put("fecha", fecha);
        valores.put("id_medicion", idMedicion);

        return db.insert(TABLA_ALERTA, null, valores);
    }

    public Cursor obtenerAlertas() {

        SQLiteDatabase db = getReadableDatabase();

        return db.rawQuery(
                "SELECT * FROM " + TABLA_ALERTA +
                        " ORDER BY id_alerta DESC",
                null
        );
    }

    // ---------------- REPORTES ----------------

    public int contarEmpresas() {
        return contarRegistros(TABLA_EMPRESA);
    }

    public int contarFuentes() {
        return contarRegistros(TABLA_FUENTE);
    }

    public int contarMediciones() {
        return contarRegistros(TABLA_MEDICION);
    }

    public int contarAlertas() {
        return contarRegistros(TABLA_ALERTA);
    }

    private int contarRegistros(String tabla) {

        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT COUNT(*) FROM " + tabla,
                null
        );

        int cantidad = 0;

        if (cursor.moveToFirst()) {
            cantidad = cursor.getInt(0);
        }

        cursor.close();

        return cantidad;
    }
}
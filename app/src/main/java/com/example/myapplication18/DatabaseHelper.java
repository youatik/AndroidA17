package com.example.myapplication18;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "HypothequeDatabase";

    // Table Name
    private static final String TABLE_NAME = "resultats";

    // Columns
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_TAUX = "taux";
    private static final String COLUMN_ANNEES = "annees";
    private static final String COLUMN_MONTANT = "montant";
    private static final String COLUMN_MENSUALITE = "mensualite";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_SQL = "CREATE TABLE " + TABLE_NAME + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_TAUX + " DOUBLE,"
                + COLUMN_ANNEES + " INTEGER,"
                + COLUMN_MONTANT + " DOUBLE,"
                + COLUMN_MENSUALITE + " DOUBLE" + ")";
        db.execSQL(CREATE_TABLE_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    // Insert a new result into the database
    public void addResult(double taux, int annees, double montant, double mensualite) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_TAUX, taux);
        values.put(COLUMN_ANNEES, annees);
        values.put(COLUMN_MONTANT, montant);
        values.put(COLUMN_MENSUALITE, mensualite);

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    // Fetch all saved results
    // Fetch all saved results
    public List<Resultat> getAllResults() {
        List<Resultat> resultList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Resultat result = new Resultat();
                result.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)));
                result.setTaux(cursor.getDouble(cursor.getColumnIndex(COLUMN_TAUX)));
                result.setAnnees(cursor.getInt(cursor.getColumnIndex(COLUMN_ANNEES)));
                result.setMontant(cursor.getDouble(cursor.getColumnIndex(COLUMN_MONTANT)));
                result.setMensualite(cursor.getDouble(cursor.getColumnIndex(COLUMN_MENSUALITE)));

                resultList.add(result);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return resultList;
    }


}

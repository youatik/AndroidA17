package com.example.myapplication18;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class ResultatsActivity extends AppCompatActivity {

    private DatabaseHelper db;  // Assuming the same DatabaseHelper class provided earlier
    private TableLayout table;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultats);  // Assuming this is the name of your XML file

        db = new DatabaseHelper(this);
        table = findViewById(R.id.TableResultats);  // Assuming you added an ID to your TableLayout in the XML

        populateTable();

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();  // Retour par la barre de navigation pour les appareils qui l'ont
        return true;
    }
    public void onRetourClicked(View view) {
        finish();  // Retour par bouton Retour
    }
    private void populateTable() {
        List<Resultat> results = db.getAllResults();
        for (Resultat result : results) {
            TableRow row = new TableRow(this);

            TextView mensualite = new TextView(this);
            mensualite.setText(String.valueOf(result.getMensualite()));
            row.addView(mensualite);

            TextView hypotheque = new TextView(this);
            hypotheque.setText(String.valueOf(result.getMontant()));
            row.addView(hypotheque);

            TextView taux = new TextView(this);
            taux.setText(String.valueOf(result.getTaux()));
            row.addView(taux);

            TextView annees = new TextView(this);
            annees.setText(String.valueOf(result.getAnnees()));
            row.addView(annees);

            table.addView(row);
        }
    }
}

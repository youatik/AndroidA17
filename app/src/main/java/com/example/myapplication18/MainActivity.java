package com.example.myapplication18;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;


public class MainActivity extends AppCompatActivity {

    private EditText tauxInteretAnnuel;
    private EditText dureePret;
    private EditText montantHypotheque;

    // Result fields
    private EditText annualInterestDisplay;
    private EditText loanAmountDisplay;
    private EditText monthlyPaymentDisplay;
    private EditText totalPaymentDisplay;
    private EditText differenceDisplay;

    private CalculateurHypotheque calculateur = new CalculateurHypotheque();
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Input Fields
        tauxInteretAnnuel = findViewById(R.id.tauxInteretAnnuel);
        dureePret = findViewById(R.id.dureePret);
        montantHypotheque = findViewById(R.id.montantHypotheque);

        // Result fields
        annualInterestDisplay = findViewById(R.id.annualInterestDisplay);
        loanAmountDisplay = findViewById(R.id.loanAmountDisplay);
        monthlyPaymentDisplay = findViewById(R.id.monthlyPaymentDisplay);
        totalPaymentDisplay = findViewById(R.id.totalPaymentDisplay);
        differenceDisplay = findViewById(R.id.differenceDisplay);

        dbHelper = new DatabaseHelper(this);
    }

    public void onCalculateClicked(View view) {
        // Parsing input values
        double taux = Double.parseDouble(tauxInteretAnnuel.getText().toString());
        int annees = Integer.parseInt(dureePret.getText().toString());
        double montant = Double.parseDouble(montantHypotheque.getText().toString());

        // Validate input values
        if (validateInputs(taux, annees)) {
            // Calculate results using the CalculateurHypothèque class
            double mensualite = calculateur.calculerMensualite(taux, annees, montant);
            double totalPaiement = mensualite * 12 * annees; // monthly payments * 12 months * number of years
            double difference = totalPaiement - montant;

            // Displaying results
            annualInterestDisplay.setText("Taux d'intérêt annuel: " + String.format(Locale.FRANCE, "%.2f", taux) + "%");
            loanAmountDisplay.setText("Montant de l’hypothèque: " + String.format(Locale.FRANCE, "$%.2f", montant));
            monthlyPaymentDisplay.setText("Montant à payer chaque mois: " + String.format(Locale.FRANCE, "$%.2f", mensualite));
            totalPaymentDisplay.setText("Montant total à payer: " + String.format(Locale.FRANCE, "$%.2f", totalPaiement));
            differenceDisplay.setText("Différence montant emprunté et total: " + String.format(Locale.FRANCE, "$%.2f", difference));

        } else {
            // If validation fails, display an error toast
            Toast.makeText(this, "Le taux doit être entre 0.5 et 19.5 % et la durée, 5, 10, 15 ou 25 ans!", Toast.LENGTH_LONG).show();
        }
    }


    public void onRecommencerClicked(View view) {
        // Clear input and result fields
        tauxInteretAnnuel.setText("");
        dureePret.setText("");
        montantHypotheque.setText("");
        annualInterestDisplay.setText("");
        loanAmountDisplay.setText("");
        monthlyPaymentDisplay.setText("");
        totalPaymentDisplay.setText("");
        differenceDisplay.setText("");
    }

    public void onSaveClicked(View view) {
        //"Taux d'intérêt annuel: 10,00%" devient "10.00"
        String tauxString = annualInterestDisplay.getText().toString()
                .replace("Taux d'intérêt annuel: ", "")
                .replace("%", "")
                .replace(",", ".")
                .trim();
        double taux = Double.parseDouble(tauxString);

        // "Montant de l’hypothèque: $123,45" devient "123.45"
        String montantString = loanAmountDisplay.getText().toString()
                .replace("Montant de l’hypothèque: ", "")
                .replace("$", "")
                .replace(",", ".")
                .trim();
        double montant = Double.parseDouble(montantString);

        int annees = Integer.parseInt(dureePret.getText().toString().trim());

        // "Montant à payer chaque mois: $456,78" devient "456.78"
        String mensualiteString = monthlyPaymentDisplay.getText().toString()
                .replace("Montant à payer chaque mois: ", "")
                .replace("$", "")
                .replace(",", ".")
                .trim();
        double mensualite = Double.parseDouble(mensualiteString);

        dbHelper.addResult(taux, annees, montant, mensualite);
        Toast.makeText(this, "Résultats sauvegardés!", Toast.LENGTH_SHORT).show();
    }




    public void onSavedCalculationsClicked(View view) {
        // Navigate to another activity that displays saved results in a table
        Intent intent = new Intent(MainActivity.this, ResultatsActivity.class);
        startActivity(intent);
    }

    private boolean validateInputs(double taux, int annees) {
        boolean isTauxValid = taux >= 0.5 && taux <= 19.5;
        boolean isAnneesValid = annees == 5 || annees == 10 || annees == 15 || annees == 25;
        return isTauxValid && isAnneesValid;
    }
}


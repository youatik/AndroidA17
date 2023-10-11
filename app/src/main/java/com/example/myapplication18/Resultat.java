package com.example.myapplication18;


public class Resultat {

    private int id;
    private double taux;
    private int annees;
    private double montant;
    private double mensualite;

    // Constructors, getters, setters, etc.

    public Resultat() {
    }

    public Resultat(int id, double taux, int annees, double montant, double mensualite) {
        this.id = id;
        this.taux = taux;
        this.annees = annees;
        this.montant = montant;
        this.mensualite = mensualite;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getTaux() {
        return taux;
    }

    public void setTaux(double taux) {
        this.taux = taux;
    }

    public int getAnnees() {
        return annees;
    }

    public void setAnnees(int annees) {
        this.annees = annees;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public double getMensualite() {
        return mensualite;
    }

    public void setMensualite(double mensualite) {
        this.mensualite = mensualite;
    }
}

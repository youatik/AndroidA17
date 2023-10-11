package com.example.myapplication18;

public class CalculateurHypotheque {

    public double calculerMensualite(double taux, int nbreAnn, double montantHypot) {
        double tim = (taux/100) / 12.0;
        return (tim * montantHypot) / (1 - Math.pow(1 + tim, -12.0 * nbreAnn));
    }

}

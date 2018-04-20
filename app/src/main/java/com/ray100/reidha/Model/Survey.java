package com.ray100.reidha.Model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Survey {
    private String nom;
    private String prenom;
    private String dateNaissance;
    private String situationFamilliale;
    private String cin;

    public Survey(){

    }

    public Survey(String nom, String prenom,
                  String dateNaissance, String situationFamilliale,
                  String cin){
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.situationFamilliale = situationFamilliale;
        this.cin = cin;

    }


    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getSituationFamilliale() {
        return situationFamilliale;
    }

    public void setSituationFamilliale(String situationFamilliale) {
        this.situationFamilliale = situationFamilliale;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("uid", cin);
        result.put("nom", nom);
        result.put("prenom", prenom);
        result.put("Date Naissance", dateNaissance);
        result.put("Situation Familliale", situationFamilliale);

        return result;
    }
}

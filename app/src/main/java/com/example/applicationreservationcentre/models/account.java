package com.example.applicationreservationcentre.models;

public class account {

    String email;
    String password;
    String nom;
    String tele;
    String ref;
    String anniv;
    String adresse;
    String sexe;
    String ville;

    public account(String email, String password, String nom, String tele, String ref, String anniv, String adresse, String sexe, String ville) {
        this.email = email;
        this.password = password;
        this.nom = nom;
        this.tele = tele;
        this.ref = ref;
        this.anniv = anniv;
        this.adresse = adresse;
        this.sexe = sexe;
        this.ville = ville;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getTele() {
        return tele;
    }

    public void setTele(String tele) {
        this.tele = tele;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getAnniv() {
        return anniv;
    }

    public void setAnniv(String anniv) {
        this.anniv = anniv;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }
}

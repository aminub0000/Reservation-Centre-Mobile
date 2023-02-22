package com.example.applicationreservationcentre.models;

public class reservation {
    String image_centre;
    String nom_reservation;
    String nom_centre;
    String date_reservation;
    String reservation_heure_debut;
    String reservation_heure_fin;
    String key;
    String resultat;
    public String getImage_centre() {
        return image_centre;
    }

    public void setImage_centre(String image_centre) {
        this.image_centre = image_centre;
    }

    public reservation() {
    }

    public reservation(String image_centre, String nom_reservation, String nom_centre, String date_reservation, String reservation_heure_debut, String reservation_heure_fin, String key, String resultat) {
        this.image_centre = image_centre;
        this.nom_reservation = nom_reservation;
        this.nom_centre = nom_centre;
        this.date_reservation = date_reservation;
        this.reservation_heure_debut = reservation_heure_debut;
        this.reservation_heure_fin = reservation_heure_fin;
        this.key = key;
        this.resultat = resultat;
    }

    public String getResultat() {
        return resultat;
    }

    public void setResultat(String resultat) {
        this.resultat = resultat;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getNom_reservation() {
        return nom_reservation;
    }

    public void setNom_reservation(String nom_reservation) {
        this.nom_reservation = nom_reservation;
    }

    public String getNom_centre() {
        return nom_centre;
    }

    public void setNom_centre(String nom_centre) {
        this.nom_centre = nom_centre;
    }

    public String getDate_reservation() {
        return date_reservation;
    }

    public void setDate_reservation(String date_reservation) {
        this.date_reservation = date_reservation;
    }

    public String getReservation_heure_debut() {
        return reservation_heure_debut;
    }

    public void setReservation_heure_debut(String reservation_heure_debut) {
        this.reservation_heure_debut = reservation_heure_debut;
    }

    public String getReservation_heure_fin() {
        return reservation_heure_fin;
    }

    public void setReservation_heure_fin(String reservation_heure_fin) {
        this.reservation_heure_fin = reservation_heure_fin;
    }
}

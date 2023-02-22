package com.example.applicationreservationcentre.models;

public class centre_info {
    String[] image_centre;
    String centre_name;
    String centre_maptext;
    String centre_tele;
    String email_tele;
    String des;
    boolean wifi;
    boolean datashow;
    int nb_classroom;
    int hair;

    public centre_info(String[] image_centre, String centre_name, String centre_maptext, String centre_tele, String email_tele, String des, boolean wifi, boolean datashow, int nb_classroom, int hair) {
        this.image_centre = image_centre;
        this.centre_name = centre_name;
        this.centre_maptext = centre_maptext;
        this.centre_tele = centre_tele;
        this.email_tele = email_tele;
        this.des = des;
        this.wifi = wifi;
        this.datashow = datashow;
        this.nb_classroom = nb_classroom;
        this.hair = hair;
    }

    public int getNb_classroom() {
        return nb_classroom;
    }

    public void setNb_classroom(int nb_classroom) {
        this.nb_classroom = nb_classroom;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public int getHair() {
        return hair;
    }

    public void setHair(int hair) {
        this.hair = hair;
    }

    public centre_info() {
    }

    public String getImage_centre(int i) {
        return image_centre[i];
    }

    public void setImage_centre(String[] image_centre) {
        this.image_centre = image_centre;
    }

    public String[] getImage_centre() {
        return image_centre;
    }

    public String getCentre_tele() {
        return centre_tele;
    }

    public void setCentre_tele(String centre_tele) {
        this.centre_tele = centre_tele;
    }

    public String getEmail_tele() {
        return email_tele;
    }

    public void setEmail_tele(String email_tele) {
        this.email_tele = email_tele;
    }

    public String getCentre_name() {
        return centre_name;
    }

    public void setCentre_name(String centre_name) {
        this.centre_name = centre_name;
    }

    public String getCentre_maptext() {
        return centre_maptext;
    }

    public void setCentre_maptext(String centre_maptext) {
        this.centre_maptext = centre_maptext;
    }

    public boolean isWifi() {
        return wifi;
    }

    public void setWifi(boolean wifi) {
        this.wifi = wifi;
    }

    public boolean isDatashow() {
        return datashow;
    }

    public void setDatashow(boolean datashow) {
        this.datashow = datashow;
    }
}

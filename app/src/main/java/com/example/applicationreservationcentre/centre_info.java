package com.example.applicationreservationcentre;

public class centre_info {
    int image_centre;
    String centre_name;
    String centre_maptext;
    String des;
    boolean wifi;
    boolean datashow;
    int nb_classroom;
    int hair;

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

    public centre_info(int image_centre, String centre_name, String centre_maptext, boolean wifi, boolean datashow, int nb_classroom, int hair,String des) {
        this.image_centre = image_centre;
        this.centre_name = centre_name;
        this.centre_maptext = centre_maptext;
        this.wifi = wifi;
        this.datashow = datashow;
        this.nb_classroom = nb_classroom;
        this.hair = hair;
        this.des = des;
    }

    public int getImage_centre() {
        return image_centre;
    }

    public void setImage_centre(int image_centre) {
        this.image_centre = image_centre;
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

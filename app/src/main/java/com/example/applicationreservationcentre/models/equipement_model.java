package com.example.applicationreservationcentre.models;

public class equipement_model {
    boolean wifi;
    boolean datashow;
    int chairs;

    public equipement_model(boolean wifi, boolean datashow, int chairs, int tables) {
        this.wifi = wifi;
        this.datashow = datashow;
        this.chairs = chairs;
        this.tables = tables;
    }
    public equipement_model() {
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

    public int getChairs() {
        return chairs;
    }

    public void setChairs(int chairs) {
        this.chairs = chairs;
    }

    public int getTables() {
        return tables;
    }

    public void setTables(int tables) {
        this.tables = tables;
    }

    int tables;
}

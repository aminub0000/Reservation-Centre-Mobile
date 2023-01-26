package com.example.applicationreservationcentre;

public class offre {
    int image;
    String offre;

    public offre(int image, String offre) {
        this.image = image;
        this.offre = offre;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getOffre() {
        return offre;
    }

    public void setOffre(String offre) {
        this.offre = offre;
    }
}

package com.example.applicationreservationcentre.models;

public class compoment_ {
    static String __id = "";
    static String __image = "";
    static String __nom = "";
    static String __email = "";

    public compoment_() {
    }

    public String get__id(){
        return  __id;
    }
    public String get__nom(){
        return  __nom;
    }
    public String get__img(){
        return  __image;
    }
    public String get__email(){
        return  __email;
    }
    public void set__nom(String nom){
        __nom = nom;
    }
    public void set__email(String email){
        __email = email;
    }
    public void set__img(String img){
        __image = img;
    }
    public void set__id(String id){
        __id = id;
    }
}

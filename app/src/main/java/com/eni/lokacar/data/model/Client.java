package com.eni.lokacar.data.model;

public class Client {
    private int id;
    private String nom;
    private String prenom;
    private String telephone;

    public Client(int id, String nom, String prenom, String telephone) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.telephone = telephone;
    }
}

package com.eni.lokacar.data.model;

import java.util.ArrayList;

public class Location {
    private int id;
    private ArrayList<String> photoAvant;
    private ArrayList<String> photoApres;
    private Vehicule vehicule ;
    private Client client;
    private int nbJours;
    private Float prix;
    private boolean isRendu;

    public Location(int id, ArrayList<String> photoAvant, ArrayList<String> photoApres, Vehicule vehicule, Client client, int nbJours, Float prix, boolean isRendu) {
        this.id = id;
        this.photoAvant = photoAvant;
        this.photoApres = photoApres;
        this.vehicule = vehicule;
        this.client = client;
        this.nbJours = nbJours;
        this.prix = prix;
        this.isRendu = isRendu;
    }

    public Location(ArrayList<String> photoAvant, ArrayList<String> photoApres, Vehicule vehicule, Client client, int nbJours, Float prix, boolean isRendu) {
        this.photoAvant = photoAvant;
        this.photoApres = photoApres;
        this.vehicule = vehicule;
        this.client = client;
        this.nbJours = nbJours;
        this.prix = prix;
        this.isRendu = isRendu;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<String> getPhotoAvant() {
        return photoAvant;
    }

    public void setPhotoAvant(ArrayList<String> photoAvant) {
        this.photoAvant = photoAvant;
    }

    public ArrayList<String> getPhotoApres() {
        return photoApres;
    }

    public void setPhotoApres(ArrayList<String> photoApres) {
        this.photoApres = photoApres;
    }

    public Vehicule getVehicule() {
        return vehicule;
    }

    public void setVehicule(Vehicule vehicule) {
        this.vehicule = vehicule;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public int getNbJours() {
        return nbJours;
    }

    public void setNbJours(int nbJours) {
        this.nbJours = nbJours;
    }

    public Float getPrix() {
        return prix;
    }

    public void setPrix(Float prix) {
        this.prix = prix;
    }

    public boolean isRendu() {
        return isRendu;
    }

    public void setRendu(boolean rendu) {
        isRendu = rendu;
    }
}

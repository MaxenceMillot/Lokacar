package com.eni.lokacar.data.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.Date;

@Entity
public class Location {
    @PrimaryKey
    private int id;
    private ArrayList<String> photoAvant;
    private ArrayList<String> photoApres;
    private Date dateDebut;
    private Date dateFin;
    private Vehicule vehicule ;
    private Client client;
    private int nbJours;
    private Float prix;

    public Location(ArrayList<String> photoAvant, ArrayList<String> photoApres, Date dateDebut, Date dateFin, Vehicule vehicule, Client client, int nbJours, Float prix) {
        this.photoAvant = photoAvant;
        this.photoApres = photoApres;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.vehicule = vehicule;
        this.client = client;
        this.nbJours = nbJours;
        this.prix = prix;
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

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
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
}

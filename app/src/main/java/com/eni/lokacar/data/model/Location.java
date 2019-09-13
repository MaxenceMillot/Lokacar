package com.eni.lokacar.data.model;

import androidx.room.Entity;
import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity
public class Location {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "location_id")
    private int id;
    @Embedded(prefix = "loc_")
    private Vehicule vehicule;
    @Embedded(prefix = "loc_")
    private Client client;
    private Date dateDebut;
    private Date dateFin;
    private int nbJours;
    private Float prix;

    public Location(Vehicule vehicule, Client client, Date dateDebut, Date dateFin, int nbJours, Float prix) {
        this.vehicule = vehicule;
        this.client = client;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.nbJours = nbJours;
        this.prix = prix;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Location{" +
                "id=" + id +
                ", vehicule=" + vehicule +
                ", client=" + client +
                ", dateDebut=" + dateDebut +
                ", dateFin=" + dateFin +
                ", nbJours=" + nbJours +
                ", prix=" + prix +
                '}';
    }
}

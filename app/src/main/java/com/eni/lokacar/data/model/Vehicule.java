package com.eni.lokacar.data.model;

public class Vehicule {
    private int id;
    private String modele;
    private String marque;
    private String plaque;
    private float prixJour;
    private String photo;
    private int nbPorte;
    private int nbPlace;
    private String carburant;
    private int critair;
    private boolean attelage;
    private boolean isCitadine;
    private boolean isDispo;

    public Vehicule(int id, String modele, String marque, String plaque, float prixJour, String photo, int nbPorte, int nbPlace, String carburant, int critair, boolean attelage, boolean isCitadine, boolean isDispo) {
        this.id = id;
        this.modele = modele;
        this.marque = marque;
        this.plaque = plaque;
        this.prixJour = prixJour;
        this.photo = photo;
        this.nbPorte = nbPorte;
        this.nbPlace = nbPlace;
        this.carburant = carburant;
        this.critair = critair;
        this.attelage = attelage;
        this.isCitadine = isCitadine;
        this.isDispo = isDispo;
    }

    public Vehicule(String modele, String marque, String plaque, float prixJour, String photo, int nbPorte, int nbPlace, String carburant, int critair, boolean attelage, boolean isCitadine, boolean isDispo) {
        this.modele = modele;
        this.marque = marque;
        this.plaque = plaque;
        this.prixJour = prixJour;
        this.photo = photo;
        this.nbPorte = nbPorte;
        this.nbPlace = nbPlace;
        this.carburant = carburant;
        this.critair = critair;
        this.attelage = attelage;
        this.isCitadine = isCitadine;
        this.isDispo = isDispo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getPlaque() {
        return plaque;
    }

    public void setPlaque(String plaque) {
        this.plaque = plaque;
    }

    public float getPrixJour() {
        return prixJour;
    }

    public void setPrixJour(float prixJour) {
        this.prixJour = prixJour;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getNbPorte() {
        return nbPorte;
    }

    public void setNbPorte(int nbPorte) {
        this.nbPorte = nbPorte;
    }

    public int getNbPlace() {
        return nbPlace;
    }

    public void setNbPlace(int nbPlace) {
        this.nbPlace = nbPlace;
    }

    public String getCarburant() {
        return carburant;
    }

    public void setCarburant(String carburant) {
        this.carburant = carburant;
    }

    public int getCritair() {
        return critair;
    }

    public void setCritair(int critair) {
        this.critair = critair;
    }

    public boolean isAttelage() {
        return attelage;
    }

    public void setAttelage(boolean attelage) {
        this.attelage = attelage;
    }

    public boolean isCitadine() {
        return isCitadine;
    }

    public void setCitadine(boolean citadine) {
        isCitadine = citadine;
    }

    public boolean isDispo() {
        return isDispo;
    }

    public void setDispo(boolean dispo) {
        isDispo = dispo;
    }
}

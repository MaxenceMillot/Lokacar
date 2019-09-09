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
}

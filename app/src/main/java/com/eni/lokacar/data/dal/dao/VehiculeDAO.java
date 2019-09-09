package com.eni.lokacar.data.dal.dao;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.eni.lokacar.data.model.Vehicule;

import java.util.List;

public interface VehiculeDAO {

    // dispo
    @Query("SELECT * FROM Vehicule" +
            " WHERE Vehicule.isDispo = :isDispo" +
            " AND Vehicule.prixJour >= :prixMin" +
            " AND Vehicule.prixJour <= :prixMax" +
            " AND Vehicule.nbPlace >= :placesMin" +
            " AND Vehicule.nbPlace <= :placesMax" +
            " AND Vehicule.nbPorte >= :portesMin" +
            " AND Vehicule.nbPorte <= :portesMax" +
            " AND Vehicule.carburant = :carburant" +
            " AND Vehicule.critair <= :critair")
    List<Vehicule> getAllDispo(Boolean isDispo, int prixMin, int prixMax, int placesMin, int placesMax, int portesMin, int portesMax, String carburant, int critair);

    // dispo + attelage
    @Query("SELECT * FROM Vehicule" +
            " WHERE Vehicule.isDispo = :isDispo" +
            " AND Vehicule.attelage = :attelage" +
            " AND Vehicule.prixJour >= :prixMin" +
            " AND Vehicule.prixJour <= :prixMax" +
            " AND Vehicule.nbPlace >= :placesMin" +
            " AND Vehicule.nbPlace <= :placesMax" +
            " AND Vehicule.nbPorte >= :portesMin" +
            " AND Vehicule.nbPorte <= :portesMax" +
            " AND Vehicule.carburant = :carburant" +
            " AND Vehicule.critair <= :critair")
    List<Vehicule> getAllDispoAttelage(Boolean isDispo, int prixMin, int prixMax, int placesMin, int placesMax, int portesMin, int portesMax, String carburant, int critair, Boolean attelage);

    // dispo + citadine
    @Query("SELECT * FROM Vehicule" +
            " WHERE Vehicule.isDispo = :isDispo" +
            " AND Vehicule.isCitadine = :isCitadine" +
            " AND Vehicule.prixJour >= :prixMin" +
            " AND Vehicule.prixJour <= :prixMax" +
            " AND Vehicule.nbPlace >= :placesMin" +
            " AND Vehicule.nbPlace <= :placesMax" +
            " AND Vehicule.nbPorte >= :portesMin" +
            " AND Vehicule.nbPorte <= :portesMax" +
            " AND Vehicule.carburant = :carburant" +
            " AND Vehicule.critair <= :critair")
    List<Vehicule> getAllDispo(Boolean isDispo, int prixMin, int prixMax, int placesMin, int placesMax, int portesMin, int portesMax, String carburant, int critair, Boolean isCitadine);

    // dispo + attelage + citadine
    @Query("SELECT * FROM Vehicule" +
            " WHERE Vehicule.isDispo = :isDispo" +
            " AND Vehicule.attelage = :attelage" +
            " AND Vehicule.isCitadine = :isCitadine" +
            " AND Vehicule.prixJour >= :prixMin" +
            " AND Vehicule.prixJour <= :prixMax" +
            " AND Vehicule.nbPlace >= :placesMin" +
            " AND Vehicule.nbPlace <= :placesMax" +
            " AND Vehicule.nbPorte >= :portesMin" +
            " AND Vehicule.nbPorte <= :portesMax" +
            " AND Vehicule.carburant = :carburant" +
            " AND Vehicule.critair <= :critair")
    List<Vehicule> getAllDispo(Boolean isDispo, int prixMin, int prixMax, int placesMin, int placesMax, int portesMin, int portesMax, String carburant, int critair, Boolean attelage, Boolean isCitadine);

    @Insert
    void insert(Vehicule vehicule);

    @Insert
    void insertAll(Vehicule... vehicule);

    @Delete
    void delete(Vehicule vehicule);
}

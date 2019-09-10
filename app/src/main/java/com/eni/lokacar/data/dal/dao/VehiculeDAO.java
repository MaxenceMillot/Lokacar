package com.eni.lokacar.data.dal.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.eni.lokacar.data.model.Vehicule;

import java.util.List;

@Dao
public interface VehiculeDAO {

    // dispo
    @Query("SELECT * FROM Vehicule" +
            " WHERE isDispo = :isDispo" +
            " AND prixJour >= :prixMin" +
            " AND prixJour <= :prixMax" +
            " AND nbPlace >= :placesMin" +
            " AND nbPlace <= :placesMax" +
            " AND nbPorte >= :portesMin" +
            " AND nbPorte <= :portesMax" +
            " AND carburant = :carburant" +
            " AND critair <= :critair")
    List<Vehicule> getAllDispo(Boolean isDispo, int prixMin, int prixMax, int placesMin, int placesMax, int portesMin, int portesMax, String carburant, int critair);

    // dispo + attelage
    @Query("SELECT * FROM Vehicule" +
            " WHERE isDispo = :isDispo" +
            " AND attelage = :attelage" +
            " AND prixJour >= :prixMin" +
            " AND prixJour <= :prixMax" +
            " AND nbPlace >= :placesMin" +
            " AND nbPlace <= :placesMax" +
            " AND nbPorte >= :portesMin" +
            " AND nbPorte <= :portesMax" +
            " AND carburant = :carburant" +
            " AND critair <= :critair")
    List<Vehicule> getAllDispoAttelage(Boolean isDispo, int prixMin, int prixMax, int placesMin, int placesMax, int portesMin, int portesMax, String carburant, int critair, Boolean attelage);

    // dispo + citadine
    @Query("SELECT * FROM Vehicule" +
            " WHERE isDispo = :isDispo" +
            " AND isCitadine = :isCitadine" +
            " AND prixJour >= :prixMin" +
            " AND prixJour <= :prixMax" +
            " AND nbPlace >= :placesMin" +
            " AND nbPlace <= :placesMax" +
            " AND nbPorte >= :portesMin" +
            " AND nbPorte <= :portesMax" +
            " AND carburant = :carburant" +
            " AND critair <= :critair")
    List<Vehicule> getAllDispoCitadine(Boolean isDispo, int prixMin, int prixMax, int placesMin, int placesMax, int portesMin, int portesMax, String carburant, int critair, Boolean isCitadine);

    // dispo + attelage + citadine
    @Query("SELECT * FROM Vehicule" +
            " WHERE isDispo = :isDispo" +
            " AND attelage = :attelage" +
            " AND isCitadine = :isCitadine" +
            " AND prixJour >= :prixMin" +
            " AND prixJour <= :prixMax" +
            " AND nbPlace >= :placesMin" +
            " AND nbPlace <= :placesMax" +
            " AND nbPorte >= :portesMin" +
            " AND nbPorte <= :portesMax" +
            " AND carburant = :carburant" +
            " AND critair <= :critair")
    List<Vehicule> getAllDispoAttelageCitadine(Boolean isDispo, int prixMin, int prixMax, int placesMin, int placesMax, int portesMin, int portesMax, String carburant, int critair, Boolean attelage, Boolean isCitadine);

    @Insert
    void insert(Vehicule vehicule);

    @Insert
    void insertAll(Vehicule... vehicule);

    @Update
    void updateVehicule(Vehicule... vehicule);

    @Delete
    void delete(Vehicule vehicule);
}

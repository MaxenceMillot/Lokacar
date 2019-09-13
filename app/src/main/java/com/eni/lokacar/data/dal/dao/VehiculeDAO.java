package com.eni.lokacar.data.dal.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.eni.lokacar.data.model.Vehicule;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Dao
public interface VehiculeDAO {

    @Query("SELECT * FROM Vehicule")
    List<Vehicule> getAll();
    // dispo
    @Query("SELECT * FROM Vehicule" +
            " WHERE isDispo = 1" +
            " AND isCitadine = :isCitadine" +
            " AND prixJour >= :prixMin" +
            " AND prixJour <= :prixMax" +
            " AND nbPlace >= :placesMin" +
            " AND nbPlace <= :placesMax" +
            " AND nbPorte >= :portesMin" +
            " AND nbPorte <= :portesMax" +
            " AND carburant LIKE :carburant" +
            " AND critair <= :critair")
    List<Vehicule> getFilterDispo(int prixMin, int prixMax, int placesMin, int placesMax, int portesMin, int portesMax, String carburant, int critair, boolean isCitadine);

    // dispo + attelage
    @Query("SELECT * FROM Vehicule" +
            " WHERE isDispo = 1" +
            " AND isCitadine = :isCitadine" +
            " AND attelage = 1" +
            " AND prixJour >= :prixMin" +
            " AND prixJour <= :prixMax" +
            " AND nbPlace >= :placesMin" +
            " AND nbPlace <= :placesMax" +
            " AND nbPorte >= :portesMin" +
            " AND nbPorte <= :portesMax" +
            " AND carburant LIKE :carburant" +
            " AND critair <= :critair")
    List<Vehicule> getFilterAttelage(int prixMin, int prixMax, int placesMin, int placesMax, int portesMin, int portesMax, String carburant, int critair, boolean isCitadine);


    // dispo
    @Query("SELECT * FROM Vehicule" +
            " WHERE isDispo = 0")
    List<Vehicule> getFilterIndispo();

    @Insert
    long insert(Vehicule vehicule);

    @Insert
    void insertAll(Vehicule... vehicule);

    @Update
    void updateVehicule(Vehicule... vehicule);

    @Delete
    void delete(Vehicule vehicule);
}

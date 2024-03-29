package com.eni.lokacar.data.dal.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.eni.lokacar.data.model.Location;

import java.util.Date;
import java.util.List;

@Dao
public interface LocationDAO {
    @Query("SELECT * FROM Location")
    List<Location> getAll();

    @Query("SELECT * FROM Location" +
            " WHERE Location.loc_vehicule_id = :idVehicule"+
            " AND dateFin IS NULL")
    Location getLastByVehicule(int idVehicule);

    @Query("SELECT SUM(prix) FROM Location" +
            " WHERE dateFin >= :intervalDebut"+
            " AND dateFin <= :intervalFin")
    float getChiffreAffaire(long intervalDebut, long intervalFin);

    @Insert
    long  insert(Location location);

    @Insert
    void insertAll(Location... location);


    @Update
    void updateLocation(Location... location);

    @Delete
    void delete(Location location);
}

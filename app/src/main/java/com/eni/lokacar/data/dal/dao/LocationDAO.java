package com.eni.lokacar.data.dal.dao;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.eni.lokacar.data.model.Client;
import com.eni.lokacar.data.model.Location;
import com.eni.lokacar.data.model.Vehicule;

import java.util.List;

public interface LocationDAO {
    @Query("SELECT * FROM Location" +
            " WHERE vehicule = :vehicule" +
            " AND isTermine = 0")
    List<Client> getOneByVehicule(Vehicule vehicule);

    @Insert
    void  insert(Location location);

    @Insert
    void insertAll(Location... location);


    @Update
    void updateLocation(Location... location);

    @Delete
    void delete(Location location);
}

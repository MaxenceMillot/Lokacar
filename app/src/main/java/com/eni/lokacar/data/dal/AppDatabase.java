package com.eni.lokacar.data.dal;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.eni.lokacar.data.dal.dao.ClientDAO;
import com.eni.lokacar.data.dal.dao.LocationDAO;
import com.eni.lokacar.data.dal.dao.VehiculeDAO;
import com.eni.lokacar.data.model.Client;
import com.eni.lokacar.data.model.Location;
import com.eni.lokacar.data.model.Vehicule;

@Database(entities = {Vehicule.class, Location.class, Client.class}, version= 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ClientDAO clientDAO();
    public abstract LocationDAO locationDAO();
    public abstract VehiculeDAO vehiculeDAO();
}

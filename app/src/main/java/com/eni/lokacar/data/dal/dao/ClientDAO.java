package com.eni.lokacar.data.dal.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.eni.lokacar.data.model.Client;

import java.util.List;

@Dao
public interface ClientDAO {
    @Query("SELECT * FROM Client")
    List<Client> getAll();

    @Query("SELECT * FROM Client" +
            " WHERE client_id = :id")
    Client getClientById(int id);

    @Insert
    long  insert(Client client);

    @Insert
    void insertAll(Client... client);

    @Insert
    void updateClient(Client... client);

    @Delete
    void delete(Client client);
}

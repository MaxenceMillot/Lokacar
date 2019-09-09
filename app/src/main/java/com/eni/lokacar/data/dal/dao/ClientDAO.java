package com.eni.lokacar.data.dal.dao;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.eni.lokacar.data.model.Client;

import java.util.List;

public interface ClientDAO {
    @Query("SELECT * FROM Client")
    List<Client> getAll();

    @Insert
    void  insert(Client client);

    @Insert
    void insertAll(Client... client);

    @Delete
    void delete(Client client);
}

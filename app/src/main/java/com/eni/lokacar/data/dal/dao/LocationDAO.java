package com.eni.lokacar.data.dal.dao;

import androidx.room.Delete;
import androidx.room.Insert;

import com.eni.lokacar.data.model.Location;

public interface LocationDAO {

    @Insert
    void  insert(Location location);

    @Insert
    void insertAll(Location... location);

    @Delete
    void delete(Location location);
}

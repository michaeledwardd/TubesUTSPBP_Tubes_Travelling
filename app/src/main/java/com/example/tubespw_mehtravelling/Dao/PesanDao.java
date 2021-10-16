package com.example.tubespw_mehtravelling.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.tubespw_mehtravelling.pesanDestinasi.Pesan;

import java.util.List;

@Dao
public interface PesanDao {

    @Query("SELECT * FROM pesan")
    List<Pesan> getAll();

    @Insert
    void insert(Pesan pesan);

    @Update
    void update(Pesan pesan);

    @Delete
    void delete(Pesan pesan);

}
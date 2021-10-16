package com.example.tubespw_mehtravelling.Database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.tubespw_mehtravelling.Dao.PesanDao;
import com.example.tubespw_mehtravelling.pesanDestinasi.Pesan;

@Database(entities = {Pesan.class}, version = 1)
public abstract class AppDatabasePesan extends RoomDatabase {
    public abstract PesanDao pesanDao();
}

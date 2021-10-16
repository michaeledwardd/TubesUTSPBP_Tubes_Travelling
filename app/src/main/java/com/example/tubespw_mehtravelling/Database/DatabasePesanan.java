package com.example.tubespw_mehtravelling.Database;

import android.content.Context;

import androidx.room.Room;

public class DatabasePesanan {
    private Context context;
    private static DatabasePesanan databasePesanan;

    private AppDatabasePesan database;

    private DatabasePesanan(Context context){
        this.context = context;
        database = Room.databaseBuilder(context, AppDatabasePesan.class, "pesan").build();
    }

    public static synchronized DatabasePesanan getInstance(Context context){
        if (databasePesanan==null){
            databasePesanan = new DatabasePesanan(context);
        }
        return databasePesanan;
    }

    public AppDatabasePesan getDatabase(){
        return database;
    }
}

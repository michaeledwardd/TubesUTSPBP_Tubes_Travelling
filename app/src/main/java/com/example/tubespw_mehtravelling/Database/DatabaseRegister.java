package com.example.tubespw_mehtravelling.Database;

import android.content.Context;

import androidx.room.Room;

public class DatabaseRegister {
    private Context context;
    private static DatabaseRegister databaseRegister;

    private AppDatabase database;

    public DatabaseRegister(Context context)
    {
        this.context=context;
        database = Room.databaseBuilder(context,AppDatabase.class,"user").allowMainThreadQueries().build();
    }

    public static synchronized DatabaseRegister getInstance(Context context)
    {
        if(databaseRegister == null)
        {
            databaseRegister = new DatabaseRegister(context);
        }
        return databaseRegister;
    }

    public AppDatabase getDatabase()
    {
        return database;
    }
}


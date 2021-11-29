package com.example.tubespw_mehtravelling.profile.database;

import android.content.Context;

import androidx.room.Room;

public class DatabaseClientUser {
    private Context context;
    private static DatabaseClientUser databaseClientUser;

    private com.example.tubespw_mehtravelling.profile.database.AppDatabaseUser database;

    private DatabaseClientUser(Context context){
        this.context = context;
        database = Room.databaseBuilder(context, com.example.tubespw_mehtravelling.profile.database.AppDatabaseUser .class, "user").build();
    }

    public static synchronized DatabaseClientUser getInstance(Context context){
        if (databaseClientUser ==null){
            databaseClientUser = new DatabaseClientUser(context);
        }
        return databaseClientUser;
    }

    public com.example.tubespw_mehtravelling.profile.database.AppDatabaseUser  getDatabaseUser() {return database; }
}

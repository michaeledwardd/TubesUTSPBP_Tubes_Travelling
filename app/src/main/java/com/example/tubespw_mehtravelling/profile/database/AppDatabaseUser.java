package com.example.tubespw_mehtravelling.profile.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.tubespw_mehtravelling.profile.model.User;

@Database(entities = {User.class}, version = 1)
public abstract class AppDatabaseUser extends RoomDatabase {
    public abstract SignUpDao signUpDAO();
}

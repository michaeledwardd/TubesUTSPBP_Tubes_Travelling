package com.example.tubespw_mehtravelling.Database;

import android.content.SharedPreferences;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.tubespw_mehtravelling.Dao.UserDao;
import com.example.tubespw_mehtravelling.Model.User;

@Database(entities = {User.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
}

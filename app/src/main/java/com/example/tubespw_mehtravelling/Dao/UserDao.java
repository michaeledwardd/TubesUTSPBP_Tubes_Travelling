package com.example.tubespw_mehtravelling.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import com.example.tubespw_mehtravelling.Model.User;
import java.util.List;
@Dao
public interface UserDao {
    @Query("SELECT * FROM user WHERE username=:username AND password=:password")
    User attemptLogin(String username, String password);
    @Insert
    void registerUser(User user);
}
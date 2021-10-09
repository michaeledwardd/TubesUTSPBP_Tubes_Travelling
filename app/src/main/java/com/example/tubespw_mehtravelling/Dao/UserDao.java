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
    @Query("SELECT * FROM user")
    List<User> getAll();

    @Insert
    void insertUser(User user);

    @Query("SELECT * FROM user WHERE username = :username AND password = :password")
    boolean checkLogin(String username,String password);

    @Query("SELECT nama FROM user WHERE username = :username AND password = :password")
    boolean getUserName(String username,String password);

    @Update
    void updateUser(User user);

    @Delete
    void deleteUser(User user);
}
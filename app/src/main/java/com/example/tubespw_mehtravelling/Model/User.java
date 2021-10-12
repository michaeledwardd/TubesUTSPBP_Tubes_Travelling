package com.example.tubespw_mehtravelling.Model;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user")
public class User {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name="nama")
    private String nama;

    @ColumnInfo(name="alamat")
    private String alamat;

    @ColumnInfo(name="username")
    private String username;

    @ColumnInfo(name="password")
    private String password;

    public User(int id, String nama, String alamat, String username, String password) {
        this.id = id;
        this.nama = nama;
        this.alamat = alamat;
        this.username = username;
        this.password = password;

    }

    public User(){
        //nothing
    }
    public int getId()
    {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama()
    {
        return nama;
    }

    public void setNama(String nama)
    {
        this.nama=nama;
    }

    public String getAlamat()
    {
        return alamat;
    }

    public void setAlamat(String alamat)
    {
        this.alamat=alamat;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username=username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password=password;
    }

}
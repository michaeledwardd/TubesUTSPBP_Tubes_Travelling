package com.example.tubespw_mehtravelling.pesanDestinasi;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Pesan implements Serializable {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "nama")
    public String nama;

    @ColumnInfo(name = "tanggal")
    public String tanggal;

    @ColumnInfo(name = "lama")
    public int lama;

    @ColumnInfo(name = "tipe")
    public String tipe;

    public int getId() {
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
        this.nama = nama;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public Integer getLama()
    {
        return lama;
    }

    public void setLama(int lama )
    {
        this.lama = lama;
    }

    public String getTipe()
    {
        return tipe;
    }

    public void setTipe(String tipe )
    {
        this.tipe = tipe;
    }



}


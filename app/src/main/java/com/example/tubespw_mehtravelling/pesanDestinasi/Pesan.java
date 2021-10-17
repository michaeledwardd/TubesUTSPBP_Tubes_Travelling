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

//    @ColumnInfo(name = "status")
//    public String status;

    @ColumnInfo(name = "tanggal")
    public String tanggal;

    @ColumnInfo(name = "lama")
    public int lama;

    @ColumnInfo(name = "tipe")
    public String tipe;

    public Pesan(String nama, String tanggal,int lama, String tipe) {
        this.nama = nama;
//        this.status = "belum";
        this.tanggal = tanggal;
        this.lama=lama;
        this.tipe = tipe;
    }
    public Pesan()
    {

    }

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

//    public String getStatus() {
//        return status;
//    }
//
//    public void setStatus(String status) {
//        this.status = status;
//    }

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


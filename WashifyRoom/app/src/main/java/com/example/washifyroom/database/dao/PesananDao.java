package com.example.washifyroom.database.dao;

import androidx.room.Query;
import androidx.room.Insert;

import com.example.washifyroom.database.entitas.Pesanan;

import java.util.List;
import androidx.room.Dao;

@Dao
public interface PesananDao {
    @Query("SELECT  * FROM pesanan")
    List<Pesanan> getAll();
    @Query("INSERT INTO pesanan(nomorId , tanggalPemesanan, estimasiDelivery) VALUES(:nomorId , :tanggalPemesanan , :estimasiDelivery)")
    void insertAll(int nomorId, String tanggalPemesanan, String estimasiDelivery);

    @Query("SELECT * FROM pesanan WHERE nomorId = :nomorId")
    Pesanan getPesananById(int nomorId);}

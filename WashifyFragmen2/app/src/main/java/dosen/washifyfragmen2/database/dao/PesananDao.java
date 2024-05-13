package dosen.washifyfragmen2.database.dao;

import androidx.room.Dao;
import androidx.room.Query;

import dosen.washifyfragmen2.database.entitas.Pesanan;

import java.util.List;

@Dao
public interface PesananDao {
    @Query("SELECT  * FROM pesanan")
    List<Pesanan> getAll();
    @Query("INSERT INTO pesanan(nomorId , tanggalPemesanan, estimasiDelivery) VALUES(:nomorId , :tanggalPemesanan , :estimasiDelivery)")
    void insertAll(int nomorId, String tanggalPemesanan, String estimasiDelivery);

    @Query("SELECT * FROM pesanan WHERE nomorId = :nomorId")
    Pesanan getPesananById(int nomorId);}

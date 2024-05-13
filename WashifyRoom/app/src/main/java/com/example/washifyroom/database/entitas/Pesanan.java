package com.example.washifyroom.database.entitas;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Pesanan {
    @PrimaryKey
    public int nomorId;
    public String tanggalPemesanan;
    public String estimasiDelivery;


}

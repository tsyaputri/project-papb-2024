package com.example.washifyroom;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.washifyroom.adapter.PesananAdapter;
import com.example.washifyroom.database.AppDatabase;
import com.example.washifyroom.database.entitas.Pesanan;
import com.example.washifyroom.thread.PesananFetcher;

import java.util.ArrayList;
import java.util.List;

public class RiwayatPesanan extends AppCompatActivity {

    private PesananAdapter pesananAdapter;
    private PesananFetcher pesananFetcher;
    private AppDatabase database;
    private List<Pesanan> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_riwayatpesanan);

        database = AppDatabase.getInstance(getApplicationContext());
        list.clear();
        list.addAll(database.pesananDao().getAll());

        RecyclerView rvPesanan = findViewById(R.id.rvPesanan);
        rvPesanan.setLayoutManager(new LinearLayoutManager(this));
        pesananAdapter = new PesananAdapter(this, list);
        rvPesanan.setAdapter(pesananAdapter);

        pesananFetcher = new PesananFetcher(this, pesananAdapter);
        String url = "https://mocki.io/v1/42c19998-349d-4add-955a-84e913b167e3";
        pesananFetcher.fetchDataFromUrl(url);
    }

}

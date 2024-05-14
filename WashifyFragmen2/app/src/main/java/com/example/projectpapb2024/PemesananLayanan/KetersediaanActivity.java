package com.example.projectpapb2024.PemesananLayanan;


import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectpapb2024.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class KetersediaanActivity extends AppCompatActivity {
    private Spinner spinnerJenisPakaian;
    private Button buttonCekKetersediaan;
    private TextView textViewHasilKetersediaan;
    private Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ketersediaan);

        spinnerJenisPakaian = findViewById(R.id.spinnerJenisPakaian);
        buttonCekKetersediaan = findViewById(R.id.buttonCekKetersediaan);
        textViewHasilKetersediaan = findViewById(R.id.textViewHasilKetersediaan);

        retrofit = new Retrofit.Builder()
                .baseUrl("https://mocki.io/") // Ganti dengan URL server Anda
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        setupSpinner();

        buttonCekKetersediaan.setOnClickListener(v -> {
            String jenisPakaian = spinnerJenisPakaian.getSelectedItem().toString();
            cekKetersediaanLayanan(jenisPakaian);
        });
    }

    private void setupSpinner() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.jenis_pakaian_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerJenisPakaian.setAdapter(adapter);
    }

    private void cekKetersediaanLayanan(String jenisPakaian) {
        LaundryServiceAPI service = retrofit.create(LaundryServiceAPI.class);
        Call<KetersediaanResponse> call;

        // Memilih endpoint yang sesuai berdasarkan jenis pakaian
        switch (jenisPakaian) {
            case "Pakaian Biasa":
                call = service.cekKetersediaanPakaianBiasa(jenisPakaian);
                break;
            case "Pakaian Berat":
                call = service.cekKetersediaanPakaianBerat(jenisPakaian);
                break;
            case "Pakaian Khusus":
                call = service.cekKetersediaanPakaianKhusus(jenisPakaian);
                break;
            default:
                // Menampilkan pesan error jika jenis pakaian tidak valid
                Toast.makeText(this, "Jenis pakaian tidak valid", Toast.LENGTH_SHORT).show();
                return;
        }

        // Menjalankan panggilan API dan menangani respons
        call.enqueue(new Callback<KetersediaanResponse>() {
            @Override
            public void onResponse(Call<KetersediaanResponse> call, Response<KetersediaanResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    // Menampilkan pesan dari server
                    KetersediaanResponse ketersediaan = response.body();
                    textViewHasilKetersediaan.setText(ketersediaan.getPesan());
                    textViewHasilKetersediaan.setVisibility(TextView.VISIBLE);
                } else {
                    // Menampilkan pesan error dari server jika respons tidak berhasil
                    Toast.makeText(KetersediaanActivity.this, "Error: " + response.code() + " " + response.message(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<KetersediaanResponse> call, Throwable t) {
                // Menampilkan pesan error jika terjadi kegagalan dalam menjalankan panggilan API
                Toast.makeText(KetersediaanActivity.this, "Network error: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

}

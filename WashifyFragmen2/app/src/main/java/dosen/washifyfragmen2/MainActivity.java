package dosen.washifyfragmen2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentTransaction;

import dosen.washifyfragmen2.fragmen.Fitur3Fragment;
import dosen.washifyfragmen2.fragmen.RiwayatFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView ivFitur1,ivFitur2,ivFitur3,ivFitur4,ivFiturRiwayat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ivFitur1 = findViewById(R.id.fitur3);
        ivFitur2 = findViewById(R.id.fitur2);
        ivFitur3 = findViewById(R.id.imageView9);
        ivFitur4 = findViewById(R.id.imageView6);
        ivFiturRiwayat = findViewById(R.id.fiturRiwayat);

        ivFitur1.setOnClickListener(this);
        ivFitur2.setOnClickListener(this);
        ivFitur3.setOnClickListener(this);
        ivFitur4.setOnClickListener(this);
        ivFiturRiwayat.setOnClickListener(this);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new RiwayatFragment()).commit();
    }


    @Override
    public void onClick(View v) {

        // Buat transaksi fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (v.getId() == R.id.fitur3) {
            Fitur3Fragment fragmen = new Fitur3Fragment();
            transaction.replace(R.id.fragment_container, fragmen);
            // Tambahkan transaksi ke dalam back stack
            transaction.addToBackStack(null);
            // Commit transaksi
            transaction.commit();

        } else if (v.getId() == R.id.fitur2) {

        }else if (v.getId() == R.id.imageView9) {

        }else if (v.getId() == R.id.imageView6) {

        }else if (v.getId() == R.id.fiturRiwayat) {
            // Ganti dengan RiwayatFragment
            RiwayatFragment riwayatFragment = new RiwayatFragment();
            transaction.replace(R.id.fragment_container, riwayatFragment);

            // Tambahkan transaksi ke dalam back stack
            transaction.addToBackStack(null);

            // Commit transaksi
            transaction.commit();
        }
    }
}
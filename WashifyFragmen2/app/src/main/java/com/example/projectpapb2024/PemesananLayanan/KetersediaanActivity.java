package com.example.coba.pemesananlayanan;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import android.view.MenuItem;
import com.example.coba.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class KetersediaanActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ketersediaan);

        bottomNavigation = findViewById(R.id.bottom_navigation);
        // Initial fragment load
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new CekFragment()).commit();

        bottomNavigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;
                String tag = null;

                switch (item.getItemId()) {
                    case R.id.nav_cek:
                        selectedFragment = new CekFragment();
                        tag = null;  // no tag needed here
                        break;
                    case R.id.nav_hasil:
                        selectedFragment = new HasilFragment();
                        tag = "hasil";  // use the tag for the result fragment
                        break;
                }

                if (selectedFragment != null) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment, tag).commit();
                }
                return true;
            }
        });
    }

    public void showResult(String message) {
        HasilFragment hasilFragment = (HasilFragment) getSupportFragmentManager().findFragmentByTag("hasil");
        if (hasilFragment != null && hasilFragment.isVisible()) {
            hasilFragment.updateResult(message);
        } else {
            // Replace with new fragment if not available
            hasilFragment = new HasilFragment();
            Bundle args = new Bundle();
            args.putString("result", message);
            hasilFragment.setArguments(args);
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, hasilFragment, "hasil").commit();
        }
    }
}

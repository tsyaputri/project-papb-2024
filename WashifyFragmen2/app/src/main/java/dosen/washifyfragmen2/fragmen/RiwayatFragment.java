package dosen.washifyfragmen2.fragmen;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import dosen.washifyfragmen2.R;
import dosen.washifyfragmen2.adapter.PesananAdapter;
import dosen.washifyfragmen2.database.AppDatabase;
import dosen.washifyfragmen2.database.entitas.Pesanan;
import dosen.washifyfragmen2.thread.PesananFetcher;

import java.util.ArrayList;
import java.util.List;

public class RiwayatFragment extends Fragment {

    private PesananAdapter pesananAdapter;
    private PesananFetcher pesananFetcher;
    private AppDatabase database;
    private List<Pesanan> list = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_riwayat, container, false);

        database = AppDatabase.getInstance(requireContext());
        list.clear();
        list.addAll(database.pesananDao().getAll());

        RecyclerView rvPesanan = rootView.findViewById(R.id.rvPesanan);
        rvPesanan.setLayoutManager(new LinearLayoutManager(requireContext()));
        pesananAdapter = new PesananAdapter(requireContext(), list);
        rvPesanan.setAdapter(pesananAdapter);

        pesananFetcher = new PesananFetcher(requireContext(), pesananAdapter);
        String url = "https://mocki.io/v1/42c19998-349d-4add-955a-84e913b167e3";
        pesananFetcher.fetchDataFromUrl(url);

        return rootView;
    }
}

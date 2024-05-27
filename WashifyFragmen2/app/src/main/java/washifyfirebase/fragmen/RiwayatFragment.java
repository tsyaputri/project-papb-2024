package projek.washifyfirebase.fragmen;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import projek.washifyfirebase.R;
import projek.washifyfirebase.adapter.PesananAdapter;
import projek.washifyfirebase.thread.PesananFetcher;

public class RiwayatFragment extends Fragment {

    private RecyclerView recyclerView;
    private PesananAdapter adapter;
    private PesananFetcher pesananFetcher;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_riwayat, container, false);

        recyclerView = view.findViewById(R.id.rvPesanan);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new PesananAdapter(getContext());
        recyclerView.setAdapter(adapter);

        pesananFetcher = new PesananFetcher(getContext(), adapter);
        pesananFetcher.fetchDataFromFirebase();

        return view;
    }

}

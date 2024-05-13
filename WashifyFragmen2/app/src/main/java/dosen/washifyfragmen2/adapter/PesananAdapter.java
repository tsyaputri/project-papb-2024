package dosen.washifyfragmen2.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import dosen.washifyfragmen2.Pelacakan;
import dosen.washifyfragmen2.R;
import dosen.washifyfragmen2.database.entitas.Pesanan;

import java.util.List;

public class PesananAdapter extends RecyclerView.Adapter<PesananAdapter.VH> {

    private final Context context;
    private List<Pesanan> list;

    public PesananAdapter(Context context, List<Pesanan> pesananList) {
        this.context = context;
        this.list = pesananList;
    }


    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.riwayat_pesanan, parent, false);
        return new VH(view);
    }

    public void setData(List<Pesanan> pesananList) {
        this.list = pesananList;
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        holder.tvIdOrder.setText("Order #" + list.get(position).nomorId);
        holder.tvTanggalPesan.setText(list.get(position).tanggalPemesanan);
        holder.tvEstimasiDelivery.setText("Estimated Delivery in " + list.get(position).estimasiDelivery);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class VH extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvIdOrder;
        TextView tvTanggalPesan;
        TextView tvEstimasiDelivery;

        public VH(@NonNull View itemView) {
            super(itemView);
            tvIdOrder = itemView.findViewById(R.id.tvIdOrder);
            tvTanggalPesan = itemView.findViewById(R.id.tvTanggalPesan);
            tvEstimasiDelivery = itemView.findViewById(R.id.tvEstimasiDelivery);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Pesanan pesanan = list.get(getAdapterPosition());
            String message = "Pesanan " + pesanan.nomorId + " ditekan";
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(context, Pelacakan.class);
            intent.putExtra("id", pesanan.nomorId);
            intent.putExtra("tanggal", pesanan.tanggalPemesanan);
            intent.putExtra("estimasi", pesanan.estimasiDelivery);
            context.startActivity(intent);
        }
    }
}

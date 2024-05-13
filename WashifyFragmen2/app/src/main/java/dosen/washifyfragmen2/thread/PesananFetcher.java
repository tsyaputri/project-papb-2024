package dosen.washifyfragmen2.thread;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import dosen.washifyfragmen2.adapter.PesananAdapter;
import dosen.washifyfragmen2.database.AppDatabase;
import dosen.washifyfragmen2.database.entitas.Pesanan;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PesananFetcher {
    private static final String TAG = "PesananFetcher";
    private Context context;
    private PesananAdapter adapter;
    private List<Pesanan> pesananList = new ArrayList<>();
    private AppDatabase database;

    public PesananFetcher(Context context, PesananAdapter adapter) {
        this.context = context;
        this.adapter = adapter;
    }

    public void fetchDataFromUrl(String url) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                RequestQueue queue = Volley.newRequestQueue(context);
                database = AppDatabase.getInstance(context);
                pesananList.clear();


                JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                        Request.Method.GET, url, null,
                        new Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(JSONArray response) {
                                for (int i = 0; i < response.length(); i++) {
                                    try {
                                        JSONObject jsonObject = response.getJSONObject(i);
                                        int nomorId = jsonObject.getInt("nomorId");
                                        String tanggalPemesanan = jsonObject.getString("tanggalPemesanan");
                                        String estimasiDelivery = jsonObject.getString("estimasiDelivery");

                                        // Lakukan pengecekan apakah data sudah ada sebelumnya sebelum disisipkan
                                        Pesanan existingPesanan = database.pesananDao().getPesananById(nomorId);
                                        if (existingPesanan == null) {
                                            database.pesananDao().insertAll(nomorId, tanggalPemesanan, estimasiDelivery);
                                        } else {
                                            // Lakukan penanganan konflik di sini (contoh: abaikan atau perbarui data)
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }

                                new Handler(Looper.getMainLooper()).post(new Runnable() {
                                    @Override
                                    public void run() {
                                        pesananList.addAll(database.pesananDao().getAll());
                                        adapter.setData(pesananList);
                                        adapter.notifyDataSetChanged();
                                    }
                                });
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.e(TAG, "Volley Error: " + error.toString());

                                new Handler(Looper.getMainLooper()).post(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(context, "Failed to fetch data", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        }
                );

                queue.add(jsonArrayRequest);
                Looper.loop();
            }
        }).start();
    }
}
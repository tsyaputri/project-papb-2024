package com.example.projectpapb2024.PemesananLayanan;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.lifecycle.ViewModelProvider;

import com.example.projectpapb2024.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainPickupSchedule extends AppCompatActivity {

    private ScheduleViewModel scheduleViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.laundry_pickup);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final ScheduleAdapter adapter = new ScheduleAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        scheduleViewModel = new ViewModelProvider(this).get(ScheduleViewModel.class);
        scheduleViewModel.getAllSchedules().observe(this, schedules -> {
            adapter.setSchedules(schedules);
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            PickupSchedule schedule = new PickupSchedule(0, "New Customer", System.currentTimeMillis(), "Scheduled", "No Notes");
            scheduleViewModel.insert(schedule);
        });
    }
}

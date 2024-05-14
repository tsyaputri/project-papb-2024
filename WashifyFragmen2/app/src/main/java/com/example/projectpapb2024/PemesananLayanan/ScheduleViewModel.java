package com.example.projectpapb2024.PemesananLayanan;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import java.util.List;

public class ScheduleViewModel extends ViewModel {
    private ScheduleRepository repository;
    private LiveData<List<PickupSchedule>> allSchedules;

    public ScheduleViewModel(ScheduleRepository repository) {
        this.repository = repository;
        allSchedules = repository.getAllSchedules();
    }

    LiveData<List<PickupSchedule>> getAllSchedules() {
        return allSchedules;
    }

    public void insert(PickupSchedule schedule) {
        repository.insert(schedule);
    }

    public void update(PickupSchedule schedule) {
        repository.update(schedule);
    }

    public void delete(PickupSchedule schedule) {
        repository.delete(schedule);
    }
}

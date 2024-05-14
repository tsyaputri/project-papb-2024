package com.example.projectpapb2024.PemesananLayanan;

import androidx.lifecycle.LiveData;
import java.util.List;

public class ScheduleRepository {
    private ScheduleDAO scheduleDao;
    private LiveData<List<PickupSchedule>> allSchedules;

    public ScheduleRepository(ScheduleDAO scheduleDao) {
        this.scheduleDao = scheduleDao;
        allSchedules = scheduleDao.getAllSchedules();
    }

    public LiveData<List<PickupSchedule>> getAllSchedules() {
        return allSchedules;
    }

    public void insert(PickupSchedule schedule) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            scheduleDao.insertSchedule(schedule);
        });
    }

    public void update(PickupSchedule schedule) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            scheduleDao.updateSchedule(schedule);
        });
    }

    public void delete(PickupSchedule schedule) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            scheduleDao.deleteSchedule(schedule);
        });
    }
}

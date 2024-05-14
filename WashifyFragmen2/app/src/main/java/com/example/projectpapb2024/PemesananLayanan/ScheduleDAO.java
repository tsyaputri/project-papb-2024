package com.example.projectpapb2024.PemesananLayanan;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import androidx.lifecycle.LiveData;

import java.util.List;

@Dao
public interface ScheduleDAO {
    @Insert
    void insertSchedule(PickupSchedule schedule);

    @Update
    void updateSchedule(PickupSchedule schedule);

    @Delete
    void deleteSchedule(PickupSchedule schedule);

    @Query("SELECT * FROM pickup_schedules")
    LiveData<List<PickupSchedule>> getAllSchedules();
}

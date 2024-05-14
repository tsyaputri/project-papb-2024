package com.example.projectpapb2024.PemesananLayanan;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "pickup_schedules")
public class PickupSchedule {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String customerName;
    private long pickupTime;
    private String status;
    private String notes;

    public PickupSchedule(int id, String customerName, long pickupTime, String status, String notes) {
        this.id = id;
        this.customerName = customerName;
        this.pickupTime = pickupTime;
        this.status = status;
        this.notes = notes;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public long getPickupTime() {
        return pickupTime;
    }

    public void setPickupTime(long pickupTime) {
        this.pickupTime = pickupTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}

package com.example.projectpapb2024.PemesananLayanan;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectpapb2024.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.http.GET;

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.ScheduleViewHolder> {

    private List<PickupSchedule> schedules;

    @Override
    public ScheduleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_pickup, parent, false);
        return new ScheduleViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ScheduleViewHolder holder, int position) {
        PickupSchedule current = schedules.get(position);
        holder.scheduleItemView.setText(current.getCustomerName() + " - " + current.getPickupTime());
    }

    @Override
    public int getItemCount() {
        return schedules == null ? 0 : schedules.size();
    }

    public void setSchedules(List<PickupSchedule> schedules) {
        this.schedules = schedules;
        notifyDataSetChanged();
    }

    static class ScheduleViewHolder extends RecyclerView.ViewHolder {
        private final TextView scheduleItemView;

        public ScheduleViewHolder(View itemView) {
            super(itemView);
            scheduleItemView = itemView.findViewById(R.id.textView);
        }
    }
}

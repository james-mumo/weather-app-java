package com.jamesmumo.weatherapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.ViewHolder> {
    private Context context;
    private ArrayList<WeatherModal> weatherModalArrayList;

    public WeatherAdapter(Context context, ArrayList<WeatherModal> weatherModalArrayList) {
        this.context = context;
        this.weatherModalArrayList = weatherModalArrayList;
    }

    @NonNull
    @Override
    public WeatherAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.weather_rv_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WeatherAdapter.ViewHolder holder, int position) {
        WeatherModal modal = weatherModalArrayList.get(position);
        holder.tempId.setText(modal.getTemperature() + "°C");
        Picasso.get().load("http".concat(modal.getIcon())).into(holder.conditionImg);
        holder.windSpeed.setText((modal.getWindspeed() + "Km/h"));

        SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd hh-mm");
        SimpleDateFormat output = new SimpleDateFormat("hh: mm aa");

        try {
            Date t = input.parse(modal.getTime());
            holder.timeId.setText(output.format(t));
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return weatherModalArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView timeId, windSpeed, tempId;
        private ImageView conditionImg;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            conditionImg = itemView.findViewById(R.id.conditionImg);
            tempId = itemView.findViewById(R.id.tempId);
            timeId = itemView.findViewById(R.id.timeId);
            windSpeed = itemView.findViewById(R.id.windSpeed);

        }
    }
}

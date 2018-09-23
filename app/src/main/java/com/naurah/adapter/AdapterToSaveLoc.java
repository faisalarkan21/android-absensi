package com.naurah.adapter;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.NetworkImageView;
//import com.example.naurahhidayah.absensigundar.Mhs_LocationFragment;
import com.example.naurahhidayah.absensigundar.SaveLocation;
import com.example.naurahhidayah.absensigundar.R;
import com.naurah.model.Schedule;
import com.naurah.utils.SessionManager;

public class AdapterToSaveLoc extends RecyclerView.Adapter<AdapterToSaveLoc.MyViewHolder> {


    private LayoutInflater inflater;
    private List<Schedule> scheduleItems;
    private Context mContext;
    SessionManager session;
    boolean isLogMhs;
    boolean isHistoryMhs;

    public AdapterToSaveLoc(Activity activity, List<Schedule> scheduleItems, Boolean isLogMhs) {
        this.mContext = activity;
        this.scheduleItems = scheduleItems;
        this.session = new SessionManager(activity.getApplication());
        this.isLogMhs = isLogMhs;
        this.isHistoryMhs = isHistoryMhs;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_row, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        final Schedule m = scheduleItems.get(position);
        int pertemuanMhs = 0;
        holder.title.setText(m.getTitle());
        holder.dosen.setText(m.getDosen());
        holder.year.setText(m.getYear());
        int pertemuanDosen = Integer.parseInt(m.getPertemuanDosen());

        if (m.getPertemuanMhs() != null){
            pertemuanMhs = Integer.parseInt(m.getPertemuanMhs());
        }




        if (pertemuanDosen == 0) {
            holder.genre.setText(m.getPlaceAndTime() + "- Belum Ada Pertemuan (Not Allowed)");

        } else if (pertemuanDosen > pertemuanMhs) {
            holder.genre.setText(m.getPlaceAndTime() + "- Pertemuan Ke - " + (pertemuanDosen) + " (Allowed)");
        }else {
            holder.genre.setText(m.getPlaceAndTime() + "- Pertemuan Ke - " + (pertemuanDosen) + " (Sudah Absen)");
        }

        Log.d("testlog", String.valueOf(isLogMhs));

        // buat absen dosen,....

    }

    @Override
    public int getItemCount() {
        return scheduleItems.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView dosen;
        public TextView genre;
        public TextView year;
        public NetworkImageView thumbNail;


        public MyViewHolder(View view) {
            super(view);

            title = (TextView) view.findViewById(R.id.title);
            dosen = (TextView) view.findViewById(R.id.dosen);
            genre = (TextView) view.findViewById(R.id.genre);
            year = (TextView) view.findViewById(R.id.releaseYear);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int position = getAdapterPosition();
                    final Schedule m = scheduleItems.get(position);

                    int pertemuanDosen = Integer.parseInt(m.getPertemuanDosen());
                    int pertemuanMhs = Integer.parseInt(m.getPertemuanMhs());


                    if (pertemuanDosen == 0) {

                        Toast.makeText(mContext.getApplicationContext(), "Dosen Belum Mengijinkan Absen", Toast.LENGTH_SHORT).show();
                    } else if (pertemuanDosen > pertemuanMhs) {


                        Log.d("inidia", Integer.toString(position));
                        session.setIdJadwal(m.getIdJadwal());
                        Intent i = new Intent(mContext, SaveLocation.class);
                        mContext.startActivity(i);

                    } else {
                        Toast.makeText(mContext.getApplicationContext(), "Anda Sudah Absen Untuk Pertemuan Tersebut", Toast.LENGTH_SHORT).show();
                    }


                }
            });
        }
    }

}
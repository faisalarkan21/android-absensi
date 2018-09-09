package com.naurah.adapter;

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

import com.android.volley.toolbox.NetworkImageView;
import com.example.naurahhidayah.absensigundar.DosenLogMhs;
import com.example.naurahhidayah.absensigundar.R;
import com.naurah.model.Schedule;
import com.naurah.utils.SessionManager;

import java.util.List;

//import com.example.naurahhidayah.absensigundar.Mhs_LocationFragment;

public class AdapterLogInDosenMhsInit extends RecyclerView.Adapter<AdapterLogInDosenMhsInit.MyViewHolder> {


    private LayoutInflater inflater;
    private List<Schedule> scheduleItems;
    private Context mContext;
    SessionManager session;
    boolean isLogMhs;
    boolean isHistoryMhs;

    public AdapterLogInDosenMhsInit(Activity activity, List<Schedule> scheduleItems, Boolean isLogMhs) {
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

        holder.title.setText(m.getTitle());
        holder.dosen.setText(m.getDosen());
        holder.genre.setText(m.getPlaceAndTime());
        holder.year.setText(m.getYear());
        Log.d("testlog", String.valueOf(isLogMhs));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("inidia", Integer.toString(position));
                    session.setIdJadwal(m.getIdJadwal());
                    Intent i = new Intent(mContext, DosenLogMhs.class);
                    i.putExtra("kelas", m.getDosen());
                    mContext.startActivity(i);
                }
            });
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
        }
    }

}
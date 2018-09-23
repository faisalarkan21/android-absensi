package com.naurah.adapter;

import android.app.Activity;
import android.app.ProgressDialog;
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
import com.example.naurahhidayah.absensigundar.DosenLog;
import com.example.naurahhidayah.absensigundar.LogMahasiswa;
import com.example.naurahhidayah.absensigundar.R;
import com.naurah.model.Schedule;
import com.naurah.service.APIService;
import com.naurah.utils.SessionManager;

import java.util.List;

/**
 * Created by faisal on 9/16/18.
 */

public class AdapterListPetemuanMhsInit extends RecyclerView.Adapter<AdapterListPetemuanMhsInit.MyViewHolder>  {

    private LayoutInflater inflater;
    private List<Schedule> dsnItems;
    private Context mContext;
    SessionManager session;
    boolean isLogMhs;
    ProgressDialog progressDialog;
    APIService mApiService;
    private APIService ApiUtils;

    public AdapterListPetemuanMhsInit(Activity activity, List<Schedule> mhsItems, Boolean isLogMhs) {
        this.mContext = activity;
        this.dsnItems = mhsItems;
        this.session = new SessionManager(activity.getApplication());
        this.isLogMhs = isLogMhs;
    }


    @NonNull
    @Override
    public AdapterListPetemuanMhsInit.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_row, parent, false);
        return new AdapterListPetemuanMhsInit.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterListPetemuanMhsInit.MyViewHolder holder, final int position) {
        final Schedule m = dsnItems.get(position);

        holder.title.setText(m.getPertemuan());

                    Log.d("inidia", Integer.toString(position));




    }

    @Override
    public int getItemCount() {
        return dsnItems.size();
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


            view.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();

                    Intent i = new Intent(mContext, LogMahasiswa.class);
                    i.putExtra("pertemuan", String.valueOf(position));
                    mContext.startActivity(i);



                }
            });




        }
    }


}

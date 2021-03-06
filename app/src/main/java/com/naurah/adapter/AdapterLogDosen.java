package com.naurah.adapter;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.ArrayMap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.NetworkImageView;
import com.example.naurahhidayah.absensigundar.R;
import com.naurah.model.Mahasiswa;
import com.naurah.model.Schedule;
import com.naurah.service.APIService;
import com.naurah.utils.SessionManager;

import org.json.JSONObject;

import java.util.List;
import java.util.Map;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;

//import com.example.naurahhidayah.absensigundar.Mhs_LocationFragment;

public class AdapterLogDosen extends RecyclerView.Adapter<AdapterLogDosen.MyViewHolder> {


    private LayoutInflater inflater;
    private List<Schedule> dsnItems;
    private Context mContext;
    SessionManager session;
    boolean isLogMhs;
    ProgressDialog progressDialog;
    APIService mApiService;
    private APIService ApiUtils;

    public AdapterLogDosen(Activity activity, List<Schedule> mhsItems, Boolean isLogMhs) {
        this.mContext = activity;
        this.dsnItems = mhsItems;
        this.session = new SessionManager(activity.getApplication());
        this.isLogMhs = isLogMhs;
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
        final Schedule m = dsnItems.get(position);

        holder.title.setText(m.getDosen());
        holder.dosen.setText("Kelas : " + m.getYear());
//        holder.genre.setText("Pertemuan Ke : " + m.getPertemuan());
        holder.year.setText(m.getPlaceAndTime());
        if (m.getTime().equals("Belum Input Lokasi")) {
            holder.genre.setText("");
        }else if (m.getIdLog() == null  || m.getVerified() == null) {

            holder.genre.setText("Belum Terverifikasi");
        } else if (m.getIdLog() == null  || !m.getVerified() ) {
            holder.genre.setText("Anda Tidak Datang");
        } else {
            holder.genre.setText("Anda Sudah Terverifikasi");
        }

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
        }
    }

}
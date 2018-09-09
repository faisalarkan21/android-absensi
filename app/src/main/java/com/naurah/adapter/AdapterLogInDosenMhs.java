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

public class AdapterLogInDosenMhs extends RecyclerView.Adapter<AdapterLogInDosenMhs.MyViewHolder> implements View.OnClickListener {


    private LayoutInflater inflater;
    private List<Mahasiswa> mhsItems;
    private Context mContext;
    SessionManager session;
    boolean isLogMhs;
    ProgressDialog progressDialog;
    APIService mApiService;
    private APIService ApiUtils;

    public AdapterLogInDosenMhs(Activity activity, List<Mahasiswa> mhsItems, Boolean isLogMhs) {
        this.mContext = activity;
        this.mhsItems = mhsItems;
        this.session = new SessionManager(activity.getApplication());
        this.isLogMhs = isLogMhs;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_row, parent, false);
        v.setOnClickListener(this);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        final Mahasiswa m = mhsItems.get(position);

        holder.title.setText(m.getNama());
        holder.dosen.setText("NPM :" + m.getNpm());
//        holder.genre.setText(m.getKelas());
        holder.year.setText(m.getTime());

    }

    @Override
    public int getItemCount() {
        return mhsItems.size();
    }

    @Override
    public void onClick(View v) {

        final Map<String, Object> jsonParams = new ArrayMap<>();
        jsonParams.put("npm", session.getIdNpm());
        final RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), (new JSONObject(jsonParams)).toString());
        session = new SessionManager(mContext);
        mApiService = com.naurah.utils.ApiUtils.getAPIService();


        android.app.AlertDialog.Builder alertBuilder = new android.app.AlertDialog.Builder(mContext);
        alertBuilder.setTitle("Konfirmasi");
        alertBuilder.setMessage("Anda yakin ingin menghapus ?");
        alertBuilder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Call<ResponseBody> response = mApiService.updateLogMgsIsValid(body);

                progressDialog = new ProgressDialog(mContext);
                progressDialog.setMessage("Menghapus Data");
                progressDialog.show();

                dialog.dismiss();
                response.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> rawResponse) {
                        if (rawResponse.isSuccessful()) {
                            progressDialog.dismiss();

                            Toast.makeText(mContext, "Berhasil Mengapus",
                                    Toast.LENGTH_LONG).show();

                            ((Activity) mContext).finish();
                            mContext.startActivity(((Activity) mContext).getIntent());
                            progressDialog.dismiss();

                        } else {
                            Toast.makeText(mContext, "Gagal",
                                    Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable throwable) {

                        Toast.makeText(mContext, throwable.getMessage(),
                                Toast.LENGTH_LONG).show();
                    }
                });

            }
        });
        alertBuilder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        android.app.AlertDialog alertDialog = alertBuilder.create();
        alertDialog.show();


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
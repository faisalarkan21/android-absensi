package com.example.naurahhidayah.absensigundar;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.naurah.adapter.CustomListAdapterLogMhs;
import com.naurah.model.Mahasiswa;
import com.naurah.service.APIService;
import com.naurah.utils.ApiUtils;
import com.naurah.utils.SessionManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class LogMahasiswa extends Activity {
    // Log tag
    private static final String TAG = MainActivity.class.getSimpleName();
    APIService mApiService;
    // Movies json url\
    //private static final String url = "https://api.androidhive.info/json/movies.json";
    ProgressDialog progressDialog;
    private List<Mahasiswa> mhsList = new ArrayList<Mahasiswa>();
    private ListView listView;
    private CustomListAdapterLogMhs adapter;
    SessionManager session;
    boolean isHistoryMhs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mhs_log);
        Intent intent = getIntent();
        isHistoryMhs = intent.getBooleanExtra("isHistoryMhs", false);
        adapter = new CustomListAdapterLogMhs(this, mhsList, isHistoryMhs);

        RecyclerView rv = (RecyclerView) findViewById(R.id.recycler_view);

        rv.setHasFixedSize(true);




        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());

        rv.setLayoutManager(mLayoutManager);

        rv.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        rv.setItemAnimator(new DefaultItemAnimator());
        rv.setAdapter(adapter);

        rv.setVisibility(View.VISIBLE);


        session = new SessionManager(getApplicationContext());

        String idKelas = intent.getStringExtra("kelas");

        progressDialog = new ProgressDialog(LogMahasiswa.this);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setMessage("Sedang Menyiapkan Data");
        progressDialog.show();
        // Showing progress dialog before making http request



        mApiService = ApiUtils.getAPIService();
        Call<JsonObject> response = mApiService.getAllMahasiswa(idKelas, session.getIdJadwal());
        // changing action bar color
//        getActionBar().setBackgroundDrawable(
//                new ColorDrawable(Color.parseColor("#1b1b1b")));

        // Creating volley request obj


        response.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, retrofit2.Response<JsonObject> rawResponse) {

                if (rawResponse.isSuccessful()) {
                    try {
                        JsonArray jsonArray = rawResponse.body().get("data").getAsJsonArray();

                        if (jsonArray.size() == 0) {
                            Toast.makeText(LogMahasiswa.this, "Tidak ada data.",
                                    Toast.LENGTH_LONG).show();
                        }
                        Log.d("TEST", jsonArray.toString());
                        for (int i = 0; i < jsonArray.size(); i++) {

                            Mahasiswa mhs = new Mahasiswa();
                            JsonObject Data = jsonArray.get(i).getAsJsonObject();
                            Log.d("Data", jsonArray.toString());
                            mhs.setNama(Data.get("nama").getAsString());
                            mhs.setKelas(Data.get("kelas").getAsString());
                            mhs.setNpm(Data.get("npm").getAsString());
                            mhs.setTime(Data.get("date_on_sign") != null ? Data.get("date_on_sign").getAsString() : "Belum Input Lokasi");
                            mhsList.add(mhs);


                        }

                        adapter.notifyDataSetChanged();

                        progressDialog.dismiss();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(LogMahasiswa.this, rawResponse.toString(),
                            Toast.LENGTH_LONG).show();
                }


            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable throwable) {
                Toast.makeText(LogMahasiswa.this, throwable.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        });


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        // getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }



}
package com.example.naurahhidayah.absensigundar;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
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
import com.naurah.adapter.AdapterLogDosenInit;
import com.naurah.adapter.AdapterLogInDosenMhsInit;
import com.naurah.adapter.AdapterToSaveLoc;
import com.naurah.adapter.AdapterToSaveLocDosen;
import com.naurah.model.Schedule;
import com.naurah.service.APIService;
import com.naurah.utils.ApiUtils;
import com.naurah.utils.SessionManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class DosenSchedule extends Activity {
    // Log tag
    private static final String TAG = MainActivity2.class.getSimpleName();
    APIService mApiService;
    // Movies json url\
    //private static final String url = "https://api.androidhive.info/json/movies.json";
    ProgressDialog progressDialog;
    private List<Schedule> scheduleList = new ArrayList<Schedule>();
    private ListView listView;
    private AdapterLogInDosenMhsInit adapterLogMhsInit;
    private AdapterLogDosenInit adapterLogDosenInit;
    private AdapterToSaveLocDosen adapterLoc;
    SessionManager session;
    boolean isDosenLogMhs;
    boolean isLogDosen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dosen_schedule);
        Intent intent = getIntent();
        isDosenLogMhs = intent.getBooleanExtra("isDosenLogMhs", false);
        isLogDosen = intent.getBooleanExtra("isLogDosen", false);


        if (isDosenLogMhs) {
            adapterLogMhsInit = new AdapterLogInDosenMhsInit(this, scheduleList, isDosenLogMhs);
            RecyclerView rv = (RecyclerView) findViewById(R.id.recycler_view);
            rv.setHasFixedSize(true);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
            rv.setLayoutManager(mLayoutManager);
            rv.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
            rv.setItemAnimator(new DefaultItemAnimator());
            rv.setAdapter(adapterLogMhsInit);
            rv.setVisibility(View.VISIBLE);
            session = new SessionManager(getApplicationContext());

            progressDialog = new ProgressDialog(DosenSchedule.this);
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.setMessage("Sedang Menyiapkan Data");
            progressDialog.show();
            // Showing progress dialog before making http request


            mApiService = ApiUtils.getAPIService();
            Call<JsonObject> response = mApiService.getAllJadwalDosen(session.getIdDosen(), session.getNipDosen());

            response.enqueue(new Callback<JsonObject>() {
                @Override
                public void onResponse(Call<JsonObject> call, retrofit2.Response<JsonObject> rawResponse) {

                    if (rawResponse.isSuccessful()) {
                        try {
                            JsonArray jsonArray = rawResponse.body().get("data").getAsJsonArray();

                            if (jsonArray.size() == 0) {
                                Toast.makeText(DosenSchedule.this, "Tidak ada data.",
                                        Toast.LENGTH_LONG).show();
                            }
                            Log.d("TEST", jsonArray.toString());
                            for (int i = 0; i < jsonArray.size(); i++) {

                                Schedule schedule = new Schedule();
                                JsonObject Data = jsonArray.get(i).getAsJsonObject();
                                Log.d("Data", jsonArray.toString());
                                schedule.setIdJadwal(Data.get("id_jadwal").getAsString());
                                schedule.setNip(Data.get("nip").getAsString());
                                schedule.setTitle(Data.get("matkul").getAsString());
                                schedule.setDosen(Data.get("kelas").getAsString());
                                schedule.setYear(Data.get("hari").getAsString());
                                    schedule.setPlaceAndTime(Data.get("ruang").getAsString() + " Jam Ke - " + Data.get("waktu").getAsString());

                                scheduleList.add(schedule);


                            }

                            adapterLogMhsInit.notifyDataSetChanged();

                            progressDialog.dismiss();

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                        Toast.makeText(DosenSchedule.this, rawResponse.toString(),
                                Toast.LENGTH_LONG).show();
                    }


                }

                @Override
                public void onFailure(Call<JsonObject> call, Throwable throwable) {
                    Toast.makeText(DosenSchedule.this, throwable.getMessage(),
                            Toast.LENGTH_LONG).show();
                }
            });


        } else if (isLogDosen) {

            adapterLogDosenInit = new AdapterLogDosenInit(this, scheduleList, isLogDosen);
            RecyclerView rv = (RecyclerView) findViewById(R.id.recycler_view);
            rv.setHasFixedSize(true);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
            rv.setLayoutManager(mLayoutManager);
            rv.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
            rv.setItemAnimator(new DefaultItemAnimator());
            rv.setAdapter(adapterLogDosenInit);
            rv.setVisibility(View.VISIBLE);
            session = new SessionManager(getApplicationContext());

            progressDialog = new ProgressDialog(DosenSchedule.this);
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.setMessage("Sedang Menyiapkan Data");
            progressDialog.show();
            // Showing progress dialog before making http request


            mApiService = ApiUtils.getAPIService();
            Call<JsonObject> response = mApiService.getAllJadwalDosen(session.getIdDosen(), session.getNipDosen());
            response.enqueue(new Callback<JsonObject>() {
                @Override
                public void onResponse(Call<JsonObject> call, retrofit2.Response<JsonObject> rawResponse) {

                    if (rawResponse.isSuccessful()) {
                        try {
                            JsonArray jsonArray = rawResponse.body().get("data").getAsJsonArray();

                            if (jsonArray.size() == 0) {
                                Toast.makeText(DosenSchedule.this, "Tidak ada data.",
                                        Toast.LENGTH_LONG).show();
                            }
                            Log.d("TEST1", jsonArray.toString());
                            for (int i = 0; i < jsonArray.size(); i++) {

                                Schedule schedule = new Schedule();
                                JsonObject Data = jsonArray.get(i).getAsJsonObject();
                                Log.d("Data", jsonArray.toString());
                                schedule.setIdJadwal(Data.get("id_jadwal").getAsString());
                                schedule.setNip(Data.get("nip").getAsString());
                                schedule.setTitle(Data.get("matkul").getAsString());
                                schedule.setDosen(Data.get("kelas").getAsString());
                                schedule.setYear(Data.get("hari").getAsString());
                                schedule.setPlaceAndTime(Data.get("ruang").getAsString() + " Jam Ke - " + Data.get("waktu").getAsString());
                                scheduleList.add(schedule);
                            }

                            Log.d("TEST2", "Total" + scheduleList.size());

                            adapterLogDosenInit.notifyDataSetChanged();
                            progressDialog.dismiss();

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                        Toast.makeText(DosenSchedule.this, rawResponse.toString(),
                                Toast.LENGTH_LONG).show();
                    }


                }

                @Override
                public void onFailure(Call<JsonObject> call, Throwable throwable) {
                    Toast.makeText(DosenSchedule.this, throwable.getMessage(),
                            Toast.LENGTH_LONG).show();
                }
            });


        } else {

            adapterLoc = new AdapterToSaveLocDosen(this, scheduleList, isDosenLogMhs);

            RecyclerView rv = (RecyclerView) findViewById(R.id.recycler_view);

            rv.setHasFixedSize(true);


            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());

            rv.setLayoutManager(mLayoutManager);

            rv.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

            rv.setItemAnimator(new DefaultItemAnimator());
            rv.setAdapter(adapterLoc);

            rv.setVisibility(View.VISIBLE);


            session = new SessionManager(getApplicationContext());


            progressDialog = new ProgressDialog(DosenSchedule.this);
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.setMessage("Sedang Menyiapkan Data");
            progressDialog.show();
            // Showing progress dialog before making http request


            mApiService = ApiUtils.getAPIService();
            Call<JsonObject> response = mApiService.getAllJadwalDosen(session.getIdDosen(), session.getNipDosen());
            response.enqueue(new Callback<JsonObject>() {
                @Override
                public void onResponse(Call<JsonObject> call, retrofit2.Response<JsonObject> rawResponse) {

                    if (rawResponse.isSuccessful()) {
                        try {
                            JsonArray jsonArray = rawResponse.body().get("data").getAsJsonArray();

                            if (jsonArray.size() == 0) {
                                Toast.makeText(DosenSchedule.this, "Tidak ada data.",
                                        Toast.LENGTH_LONG).show();
                            }
                            Log.d("TEST", jsonArray.toString());
                            for (int i = 0; i < jsonArray.size(); i++) {

                                Schedule schedule = new Schedule();
                                JsonObject Data = jsonArray.get(i).getAsJsonObject();
                                Log.d("Data", jsonArray.toString());
                                schedule.setIdJadwal(Data.get("id_jadwal").getAsString());
                                schedule.setNip(Data.get("nip").getAsString());
                                schedule.setTitle(Data.get("matkul").getAsString());
                                schedule.setDosen(Data.get("nama_kelas").getAsString());
                                schedule.setYear(Data.get("hari").getAsString());
                                schedule.setPertemuanDosen(Data.get("pertemuanDosen").getAsString());
                                schedule.setPlaceAndTime(Data.get("ruang").getAsString() + " Jam Ke - " + Data.get("waktu").getAsString());

                                scheduleList.add(schedule);


                            }

                            adapterLoc.notifyDataSetChanged();

                            progressDialog.dismiss();

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                        Toast.makeText(DosenSchedule.this, rawResponse.toString(),
                                Toast.LENGTH_LONG).show();
                    }


                }

                @Override
                public void onFailure(Call<JsonObject> call, Throwable throwable) {
                    Toast.makeText(DosenSchedule.this, throwable.getMessage(),
                            Toast.LENGTH_LONG).show();
                }
            });
        }
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
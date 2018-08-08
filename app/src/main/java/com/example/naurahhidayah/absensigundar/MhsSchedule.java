package com.example.naurahhidayah.absensigundar;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.naurah.adapter.CustomListAdapter;
import com.naurah.model.Schedule;
import com.naurah.service.APIService;
import com.naurah.utils.ApiUtils;
import com.naurah.utils.SessionManager;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class MhsSchedule extends Activity {
    // Log tag
    private static final String TAG = MainActivity.class.getSimpleName();
    APIService mApiService;
    SessionManager session;
    // Movies json url\
    //private static final String url = "https://api.androidhive.info/json/movies.json";
    ProgressDialog progressDialog;
    private List<Schedule> scheduleList = new ArrayList<Schedule>();
    private ListView listView;
    private CustomListAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mhs_schedule);
        listView = (ListView) findViewById(R.id.list);
        adapter = new CustomListAdapter(this, scheduleList);
        adapter.setmContext(this);
        listView.setAdapter(adapter);


        session = new SessionManager(getApplicationContext());


        progressDialog = new ProgressDialog(MhsSchedule.this);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setMessage("Sedang Menyiapkan Data");
        progressDialog.show();
        // Showing progress dialog before making http request


        mApiService = ApiUtils.getAPIService();
        Call<JsonObject> response = mApiService.getAllJadwal();
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
                            Toast.makeText(MhsSchedule.this, "Tidak ada data.",
                                    Toast.LENGTH_LONG).show();
                        }
                        Log.d("TEST", jsonArray.toString());
                        for (int i = 0; i < jsonArray.size(); i++) {


                            Schedule schedule = new Schedule();
                            JsonObject Data = jsonArray.get(i).getAsJsonObject();
                            Log.d("Data", jsonArray.toString());

                            schedule.setTitle(Data.get("matkul").getAsString());


                            schedule.setDosen(Data.get("dosen").getAsString());
                            schedule.setYear(Data.get("hari").getAsString());
                            schedule.setPlaceAndTime(Data.get("ruang").getAsString() + Data.get("waktu").getAsString());

                            scheduleList.add(schedule);


                        }

                        adapter.notifyDataSetChanged();

                        progressDialog.dismiss();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(MhsSchedule.this, rawResponse.toString(),
                            Toast.LENGTH_LONG).show();
                }


            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable throwable) {
                Toast.makeText(MhsSchedule.this, throwable.getMessage(),
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
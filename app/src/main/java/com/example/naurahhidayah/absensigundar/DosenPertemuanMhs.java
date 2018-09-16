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
import com.naurah.adapter.AdapterListPetemuanMhs;
import com.naurah.adapter.AdapterLogDosen;
import com.naurah.model.Schedule;
import com.naurah.service.APIService;
import com.naurah.utils.ApiUtils;
import com.naurah.utils.SessionManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by faisal on 9/16/18.
 */

public class DosenPertemuanMhs extends Activity {

    private static final String TAG = MainActivity2.class.getSimpleName();
    APIService mApiService;
    // Movies json url\
    //private static final String url = "https://api.androidhive.info/json/movies.json";
    ProgressDialog progressDialog;
    private List<Schedule> dsnList = new ArrayList<Schedule>();
    private ListView listView;
    private AdapterListPetemuanMhs adapter;
    SessionManager session;
    boolean isLogMhs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mhs_log);
        Intent intent = getIntent();
        isLogMhs = intent.getBooleanExtra("isDosenLogMhs", false);
        adapter = new AdapterListPetemuanMhs(this, dsnList, isLogMhs);

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

        progressDialog = new ProgressDialog(DosenPertemuanMhs.this);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setMessage("Sedang Menyiapkan Data");
        progressDialog.show();

           for (int i = 0; i < 12; i++) {

            Schedule dsn = new Schedule();

            dsn.setPertemuan("Petemuan Ke : " + (i +1));

            dsnList.add(dsn);
        }

        adapter.notifyDataSetChanged();
        progressDialog.dismiss();


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

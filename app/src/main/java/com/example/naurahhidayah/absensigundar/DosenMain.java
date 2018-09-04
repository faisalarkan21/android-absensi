package com.example.naurahhidayah.absensigundar;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

public class DosenMain extends AppCompatActivity {


    private BottomNavigationView.OnNavigationItemSelectedListener botnav = new BottomNavigationView.OnNavigationItemSelectedListener(){
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()){
                case R.id.navigasi_jadwal:
                    Dosen_JadwalFragment dosen_jadwalFragment = new Dosen_JadwalFragment();
                    android.support.v4.app.FragmentTransaction fragmentdosenjadwalTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentdosenjadwalTransaction.replace(R.id.content, dosen_jadwalFragment);
                    fragmentdosenjadwalTransaction.commit();

                    return true;
                case R.id.navigasi_jadwal_cek_mhs:
                    Dosen_jadwalFragmentLogMhs dosen_jadwalFragmentLogMhs = new Dosen_jadwalFragmentLogMhs();
                    android.support.v4.app.FragmentTransaction fragmentdosenjadwalTransactionLog = getSupportFragmentManager().beginTransaction();
                    fragmentdosenjadwalTransactionLog.replace(R.id.content, dosen_jadwalFragmentLogMhs);
                    fragmentdosenjadwalTransactionLog.commit();

                    return true;
                case R.id.navigasi_logout:
                    PreferenceManager.getDefaultSharedPreferences(getBaseContext()).
                            edit().clear().apply();
                    Intent login = new Intent(DosenMain.this, Dosen_Login.class);
                    startActivity(login);

                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dosen_main);

        Dosen_JadwalFragment dosen_jadwalFragment = new Dosen_JadwalFragment();
        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content, dosen_jadwalFragment);
        fragmentTransaction.commit();


        BottomNavigationView BNV = (BottomNavigationView) findViewById(R.id.navigasi);
        BNV.setOnNavigationItemSelectedListener(botnav);
    }
}

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

public class MhsMain extends AppCompatActivity {


        private BottomNavigationView.OnNavigationItemSelectedListener botnav = new BottomNavigationView.OnNavigationItemSelectedListener(){
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()){
                case R.id.navigasi_jadwal:
                    Mhs_JadwalFragment mhs_jadwalFragment = new Mhs_JadwalFragment();
                    android.support.v4.app.FragmentTransaction fragmentmhsjadwalTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentmhsjadwalTransaction.replace(R.id.content, mhs_jadwalFragment);
                    fragmentmhsjadwalTransaction.commit();

                    return true;

                case R.id.navigasi_mhs_cek_log:
                    Mhs_JadwalFragmentLogMhs mhs_jadwalfragmentLogMhs = new Mhs_JadwalFragmentLogMhs();
                    android.support.v4.app.FragmentTransaction fragmentmhsjadwalTransactionLog = getSupportFragmentManager().beginTransaction();
                    fragmentmhsjadwalTransactionLog.replace(R.id.content, mhs_jadwalfragmentLogMhs);
                    fragmentmhsjadwalTransactionLog.commit();

                    return true;

                case R.id.navigasi_logout:
                    PreferenceManager.getDefaultSharedPreferences(getBaseContext()).
                            edit().clear().apply();
                    Intent login = new Intent(MhsMain.this, Mahasiswa_Login.class);
                    startActivity(login);


            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mhs_main);

        Mhs_JadwalFragment mhs_jadwalFragment = new Mhs_JadwalFragment();
        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content, mhs_jadwalFragment);
        fragmentTransaction.commit();


        BottomNavigationView BNV = (BottomNavigationView) findViewById(R.id.navigasi_mhs);
        BNV.setOnNavigationItemSelectedListener(botnav);
    }
}

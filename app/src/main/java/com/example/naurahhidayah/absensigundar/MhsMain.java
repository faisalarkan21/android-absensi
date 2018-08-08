package com.example.naurahhidayah.absensigundar;

import android.os.Bundle;
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
                case R.id.navigasi_informasi:
                    Mhs_InfoFragment mhs_infoFragment = new Mhs_InfoFragment();
                    android.support.v4.app.FragmentTransaction fragmentmhsinfoTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentmhsinfoTransaction.replace(R.id.content, mhs_infoFragment);
                    fragmentmhsinfoTransaction.commit();

                    return true;
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


        BottomNavigationView BNV = (BottomNavigationView) findViewById(R.id.navigasi);
        BNV.setOnNavigationItemSelectedListener(botnav);
    }
}

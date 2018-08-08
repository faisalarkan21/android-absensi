package com.example.naurahhidayah.absensigundar;


import android.os.Bundle;
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
                case R.id.navigasi_informasi:
                    Dosen_InfoFragment dosen_infoFragment = new Dosen_InfoFragment();
                    android.support.v4.app.FragmentTransaction fragmentdoseninfoTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentdoseninfoTransaction.replace(R.id.content, dosen_infoFragment);
                    fragmentdoseninfoTransaction.commit();

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

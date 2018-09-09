package com.example.naurahhidayah.absensigundar;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Mhs_LocationFragment extends AppCompatActivity implements View.OnClickListener{

    String idJadwal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_mhs__location_fragment);

        Button btn_mhsloc = (Button) findViewById(R.id.btn_mhsloc);
        btn_mhsloc.setOnClickListener(this);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            idJadwal = extras.getString("id_jadwal");
        }



    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_mhsloc:
                Intent mhsloc = new Intent(this, SaveLocation.class);
                mhsloc.putExtra("id_jadwal", idJadwal);
                startActivity(mhsloc);
                break;

        }

    }
}
package com.example.naurahhidayah.absensigundar;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class Mhs_JadwalFragmentLogMhs extends Fragment implements View.OnClickListener {


    View v;
    Button btn_mhsnotice;

    public Mhs_JadwalFragmentLogMhs() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.activity_mhs__jadwal_fragment_log_mhs, container, false);
        btn_mhsnotice = (Button)v.findViewById(R.id.btn_mhsjadwal);
        btn_mhsnotice.setOnClickListener(this);
        // Inflate the layout for this fragment
        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_mhsjadwal:
                Intent mhsjadwal = new Intent(getActivity(), MhsSchedule.class);
                mhsjadwal.putExtra("isHistoryMhs", true);
                startActivity(mhsjadwal);
                break;
        }
    }


}

package com.example.naurahhidayah.absensigundar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class Mhs_JadwalFragment extends Fragment implements View.OnClickListener {


    View v;
    Button btn_mhsjadwal;

    public Mhs_JadwalFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_mhs_jadwal, container, false);
        btn_mhsjadwal = (Button)v.findViewById(R.id.btn_mhsjadwal);
        btn_mhsjadwal.setOnClickListener(this);
        // Inflate the layout for this fragment
        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_mhsjadwal:
                Intent mhsjadwal = new Intent(getActivity(), MhsSchedule.class);
                startActivity(mhsjadwal);
                break;
        }
    }
}

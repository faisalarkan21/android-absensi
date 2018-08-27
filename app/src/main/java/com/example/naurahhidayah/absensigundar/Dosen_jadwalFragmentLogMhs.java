package com.example.naurahhidayah.absensigundar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by faisal on 8/27/18.
 */

public class Dosen_jadwalFragmentLogMhs extends Fragment implements View.OnClickListener {


    View v;
    Button btn_dosennotice;

    public Dosen_jadwalFragmentLogMhs() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_dosen__jadwal_log_mhs, container, false);
        btn_dosennotice = (Button)v.findViewById(R.id.btn_dosenjadwal);
        btn_dosennotice.setOnClickListener(this);
        // Inflate the layout for this fragment
        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_dosenjadwal:
                Intent dosenjadwal = new Intent(getActivity(), DosenSchedule.class);
                dosenjadwal.putExtra("isLogMhs", true);
                startActivity(dosenjadwal);
                break;
        }
    }


}

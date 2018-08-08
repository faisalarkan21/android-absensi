package com.example.naurahhidayah.absensigundar;


import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main2);

        Button btn_dosenlogin = (Button) findViewById(R.id.btn_dosenlogin);
        btn_dosenlogin.setOnClickListener(this);

        Button btn_back = (Button) findViewById(R.id.btn_back);
        btn_back.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_dosenlogin:
                Intent dosenlogin = new Intent(this, Dosen_Login.class);
                startActivity(dosenlogin);
                break;

            case R.id.btn_back:
                Intent back = new Intent(this, MainActivity.class);
                startActivity(back);
                break;

        }

    }
}
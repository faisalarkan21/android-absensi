package com.example.naurahhidayah.absensigundar;


import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);

        Button btn_mhslogin = (Button) findViewById(R.id.btn_mhslogin);
        btn_mhslogin.setOnClickListener(this);

        Button btn_next = (Button) findViewById(R.id.btn_next);
        btn_next.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_mhslogin:
                Intent mhslogin = new Intent(this, Mahasiswa_Login.class);
                startActivity(mhslogin);
                break;

            case R.id.btn_next:
                Intent next = new Intent(this, MainActivity2.class);
                startActivity(next);
                break;

        }

    }
}
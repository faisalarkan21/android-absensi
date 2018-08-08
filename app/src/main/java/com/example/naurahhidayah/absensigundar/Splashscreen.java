package com.example.naurahhidayah.absensigundar;


import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class Splashscreen extends AppCompatActivity {
    private static int progress = 0;
    private int status = 0;
    ProgressBar progressBar;
    Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_splashscreen);

        progressBar = (ProgressBar)findViewById(R.id.progressbar1);

        new Thread(new Runnable() {
            @Override
            public void run() {
                while(status < 100) {
                    status = loading();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setProgress(status);
                        }
                    });

                }
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Intent pindah = new Intent(Splashscreen.this,
                                WelcomeActivity.class);
                        startActivity(pindah);
                        finish();
                    }
                });
            }
            public int loading() {
                try {
                    Thread.sleep(50);
                }
                catch (InterruptedException ie) {
                    ie.printStackTrace();
                }
                return ++progress;
            }
        }).start();
    }
}

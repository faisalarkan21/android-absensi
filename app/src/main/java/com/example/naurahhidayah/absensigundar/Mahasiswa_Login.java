package com.example.naurahhidayah.absensigundar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.ArrayMap;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.naurah.service.APIService;
import com.naurah.utils.ApiUtils;
import com.naurah.utils.SessionManager;

import org.json.JSONObject;

import java.util.Map;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;

public class Mahasiswa_Login extends AppCompatActivity {
    APIService mApiService;
    SessionManager session;
    EditText username, password;

    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mahasiswa__login);

        //pada saat tombol home ditekan
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PrefManager prefManager = new PrefManager(getApplicationContext());
                prefManager.setFirstTimeLaunch(true);
                startActivity(new Intent(Mahasiswa_Login.this, WelcomeActivity.class));
                finish();
            }
        });

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        session = new SessionManager(getApplicationContext());
        progressDialog = new ProgressDialog(Mahasiswa_Login.this);




        findViewById(R.id.btnLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Toast.makeText(Mahasiswa_Login.this, password.getText().toString(),Toast.LENGTH_SHORT).show();

                sendPost(username.getText().toString(), password.getText().toString());

            }
        });





    }

    public void sendPost(String email, String password) {

        mApiService = ApiUtils.getAPIService();

        Map<String, Object> jsonParams = new ArrayMap<>();
        jsonParams.put("email", email);
        jsonParams.put("password", password);


        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), (new JSONObject(jsonParams)).toString());


        Call<ResponseBody> response = mApiService.loginPostMhs( body);

        response.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> rawResponse) {
                if (rawResponse.isSuccessful()) {
                    try {

                        JSONObject jsonObject = new JSONObject(rawResponse.body().string());
                        final String dataStatus = jsonObject.getString("isValid");
                        Log.d("coba", dataStatus);


                        if (!Boolean.parseBoolean(dataStatus)){
                            Toast.makeText(Mahasiswa_Login.this, "Password / Username Salah",
                                    Toast.LENGTH_LONG).show();
                            return;
                        }



//                        String dataToken = jsonObject.getString("token");
                        session.setIdNpm(jsonObject.getString("npm"));
                            session.setIdKelas(jsonObject.getString("kelas"));

                        new CountDownTimer(1000, 1000) {

                            public void onTick(long millisUntilFinished) {
                                // You don't need anything here
                            }
                            public void onFinish() {
                                progressDialog.dismiss();
                                Intent dashboard = new Intent(Mahasiswa_Login.this, MhsMain.class);
                                startActivity(dashboard);
                                finish();

                            }
                        }.start();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(Mahasiswa_Login.this, rawResponse.message(),
                            Toast.LENGTH_LONG).show();
                    progressDialog.dismiss();
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable throwable) {

                Toast.makeText(Mahasiswa_Login.this, throwable.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        });


    }

}

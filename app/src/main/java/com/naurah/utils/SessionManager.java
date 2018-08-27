package com.naurah.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SessionManager {

    private SharedPreferences prefs;
    private static final String IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch";


    public SessionManager(Context cntx) {
        // TODO Auto-generated constructor stub
        prefs = PreferenceManager.getDefaultSharedPreferences(cntx);
    }

    public void setSession(String token) {
        prefs.edit().putString("token", token).apply();
    }

    public void setIdJadwal(String id) {
        prefs.edit().putString("idJadwal", id).apply();
    }

    public void setIdNpm(String npm) {
        prefs.edit().putString("npm", npm).apply();
    }

    public void setIdKelas(String kelas) {
        prefs.edit().putString("kelas", kelas).apply();
    }

    public void setNipDosen(String nipDosen) {
        prefs.edit().putString("nipDosen", nipDosen).apply();
    }

    public void setIdDosen(String idDosen) {
        prefs.edit().putString("idDosen", idDosen).apply();
    }

    public String getToken() {
        String token = prefs.getString("token","");
        return token;
    }


    public String getNipDosen() {
        String token = prefs.getString("nipDosen","");
        return token;
    }

    public String getIdJadwal() {
        String token = prefs.getString("idJadwal","");
        return token;
    }

    public String getIdDosen() {
        String token = prefs.getString("idDosen","");
        return token;
    }

    public String getIdNpm() {
        String token = prefs.getString("npm","");
        return token;
    }

    public String getIdKelas(){
        String token = prefs.getString("kelas","");
        return token;

    }

    public void setFirstTimeLaunch(boolean isFirstTime) {
        prefs.edit().putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTime).commit();
    }

    public void removeToken(){
        prefs.edit().remove("token").apply();
    }

}

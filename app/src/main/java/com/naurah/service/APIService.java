package com.naurah.service;

import com.google.gson.JsonObject;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIService {

    @POST("/api/login-mhs")
    Call<ResponseBody> loginPostMhs(@Body RequestBody params);

    @POST("/api/login-dosen")
    Call<ResponseBody> loginPostDosen(@Body RequestBody params);

    @POST("/api/main-login")
    Call<ResponseBody> mainloginPost(@Body RequestBody params);

    @POST("/api/save-location-mhs")
    Call<ResponseBody> saveMapMhs(@Body RequestBody params);

    @POST("/api/save-location-dosen")
    Call<ResponseBody> saveMapDosen(@Body RequestBody params);

    @POST("https://maps.googleapis.com/maps/api/geocode/json")
    Call<JsonObject> reverseGeocode(@Query("latlng") String langlat,
                                    @Query("key") String key);

    @GET("/api/jadwal-mhs")
    Call<JsonObject> getAllJadwalMhs(@Query("kelas") String params);

    @GET("/api/get-all-mhs")
    Call<JsonObject> getAllMahasiswa(@Query("id_kelas") String params, @Query("id_jadwal") String idJadwalKelas);

    @GET("/api/jadwal-dosen")
    Call<JsonObject> getAllJadwalDosen(@Query("id") String idDosen);

    @GET("/api/get-all-log-mhs")
    Call<JsonObject> getAllLogMhs(@Query("id_jadwal") String idJadwalKelas);



}

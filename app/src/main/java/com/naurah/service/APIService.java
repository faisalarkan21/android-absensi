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

    @POST("/api/login")
    Call<ResponseBody> loginPost(@Body RequestBody params);

    @POST("/api/main-login")
    Call<ResponseBody> mainloginPost(@Body RequestBody params);

    @POST("/api/save-location-mhs")
    Call<ResponseBody> saveMapMhs(@Body RequestBody params);

    @POST("https://maps.googleapis.com/maps/api/geocode/json")
    Call<JsonObject> reverseGeocode(@Query("latlng") String langlat,
                                    @Query("key") String key);

    @GET("/api/jadwal-mhs")
    Call<JsonObject> getAllJadwal(@Query("kelas") String params);

    @GET("/api/jadwal-dosen")
    Call<JsonObject> getMainAllJadwal();

}

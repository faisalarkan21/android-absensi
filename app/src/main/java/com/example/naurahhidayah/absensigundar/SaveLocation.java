package com.example.naurahhidayah.absensigundar;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.IntentSender;
import android.location.Location;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStates;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.naurah.model.Schedule;
import com.naurah.service.APIService;
import com.naurah.utils.ApiUtils;
import com.naurah.utils.SessionManager;

import org.json.JSONObject;

import java.util.List;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SaveLocation extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMapLongClickListener, EasyPermissions.PermissionCallbacks, View.OnClickListener {

    private static final int LOCATION_REQUEST = 201;
    private final static int LOCATION_SETTING_RC = 122;
    private APIService mApiSevice;
    private FusedLocationProviderClient mFusedLocationClient;
    private GoogleMap mMap;
    private Marker mAssetMarker;
    private MarkerOptions mAssetMarkerOptions;
    private LatLng assetLocation = new LatLng(-6.4024844, 106.7402258);
    private String[] locationPerm = new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};
    private String mAddress = "";
    EditText mEtAssetAddress;
    Button btnSubmitLokasi;
    ProgressDialog progressDialog;
    SessionManager session;
    String idJadwal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mhs_location);

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        mEtAssetAddress = (EditText) findViewById(R.id.editTextLocation);

        btnSubmitLokasi = (Button) findViewById(R.id.btn_submit_location);

        progressDialog = new ProgressDialog(SaveLocation.this);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setMessage("Sedang Menyiapkan Data");
        progressDialog.show();
//        mBtnSubmitLocation.setOnClickListener(this);
        btnSubmitLokasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitMapData();
            }
        });
        session = new SessionManager(getApplicationContext());
        Log.d("cekdosen", session.getNipDosen());

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.asset_map);
        mapFragment.getMapAsync(this);
        mApiSevice = ApiUtils.getAPIService();
        mEtAssetAddress.setEnabled(false);
        if (EasyPermissions.hasPermissions(this, locationPerm)) {
            askUserToTurnOnGPS();
            getCurrentLocationWithPermission();
        } else {
            EasyPermissions.requestPermissions(this, "Izinkan aplikasi untuk mengakses lokasi anda", LOCATION_REQUEST, locationPerm);
        }
        updateMapUI();

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Do something after 5s = 5000ms
                progressDialog.hide();
            }
        }, 6500);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            idJadwal = extras.getString("id_jadwal");
        }


    }


    private void askUserToTurnOnGPS() {
        try {
            GoogleApiClient
                    googleApiClient = new GoogleApiClient.Builder(this)
                    .addApi(LocationServices.API)
                    .build();

            googleApiClient.connect();

            LocationRequest request = new LocationRequest();
            request.setInterval(1000000)
                    .setFastestInterval(500000)
                    .setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);

            LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder();
            builder.addLocationRequest(request);

            PendingResult<LocationSettingsResult> result =
                    LocationServices.SettingsApi.checkLocationSettings(googleApiClient, builder.build());
            result.setResultCallback(new ResultCallback<LocationSettingsResult>() {
                @Override
                public void onResult(LocationSettingsResult result) {
                    final Status status = result.getStatus();
                    final LocationSettingsStates state = result.getLocationSettingsStates();
                    switch (status.getStatusCode()) {
                        case LocationSettingsStatusCodes.SUCCESS:
                            // All location settings are satisfied. The client can initialize location
                            // requests here.
                            getCurrentLocationWithPermission();
                            if (state.isGpsUsable() && state.isLocationUsable() && state.isNetworkLocationUsable()) {
                                return;
                            }
                            break;
                        case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                            // Location settings are not satisfied. But could be fixed by showing the user
                            // a dialog.
                            try {
                                // Show the dialog by calling startResolutionForResult(),
                                // and check the result in onActivityResult().
                                status.startResolutionForResult(
                                        SaveLocation.this, LOCATION_SETTING_RC);
                            } catch (IntentSender.SendIntentException e) {
                                e.printStackTrace();
                            }
                            break;
                        case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                            // Location settings are not satisfied. However, we have no way to fix the
                            // settings so we won't show the dialog.
                            break;
                    }
                }
            });
        } catch (Exception exception) {
            // Log exception
        }
    }

    @AfterPermissionGranted(12)
    private void getCurrentLocationWithPermission() {

        String[] locPerms = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};
        if (EasyPermissions.hasPermissions(this, locPerms)) {
            getCurrentLocation();


        } else {
            EasyPermissions.requestPermissions(this, "Izinkan aplikasi untuk mengakses lokasi anda", 12, locPerms);
        }
    }

    private void getCurrentLocation() {

        try {
            Task<Location> result = mFusedLocationClient.getLastLocation();
            result.addOnSuccessListener(this, new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    if (location != null) {
                        Log.d("currlocation", "berhasil :" + location.getLongitude());
                        assetLocation = new LatLng(location.getLatitude(), location.getLongitude());
                        getAddressByLocation(location.getLatitude(), location.getLongitude());
                    }
                }
            });
        } catch (SecurityException e) {

            e.printStackTrace();
        }
    }

    private void getAddressByLocation(double lat, double lng) {
        String formattedLangLat = lat + "," + lng;
        Call<JsonObject> geoRequest = mApiSevice.reverseGeocode(formattedLangLat, getString(R.string.geocode_key));
        geoRequest.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                JsonArray results = response.body().getAsJsonArray("results");
                JsonObject firstAddress = results.get(0).getAsJsonObject();
                mAddress = firstAddress.get("formatted_address").getAsString();
                updateMapUI();
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        });
    }


    private void submitMapData() {

        progressDialog = new ProgressDialog(SaveLocation.this);
        progressDialog.setMessage("Menyimpan Data");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();


        try {

            JSONObject request = new JSONObject();
            Schedule schedule = new Schedule();


            request.put("id_jadwal_kelas", session.getIdJadwal());


            request.put("longitude", assetLocation.longitude);
            request.put("latitude", assetLocation.latitude);
            request.put("kelas", session.getIdKelas());

            if (!session.getNipDosen().isEmpty()) {
                request.put("nip", session.getNipDosen());
            } else {
                request.put("npm", session.getIdNpm());
            }


            request.put("address", mEtAssetAddress.getText().toString());

            Call<ResponseBody> res = null;
            RequestBody requestBody = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), request.toString());

            if (!session.getNipDosen().isEmpty()) {
                res = mApiSevice.saveMapDosen(requestBody);
                Log.d("save", "save dosen");
                Log.d("savedosen", session.getNipDosen());
            } else {
                res = mApiSevice.saveMapMhs(requestBody);
                Log.d("save", "save mhs");
            }


            res.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    progressDialog.dismiss();
                    if (response.isSuccessful()) {
                        Toast.makeText(SaveLocation.this, "Penyimpanan Lokasi Berhasil!", Toast.LENGTH_LONG).show();
                        if (!session.getNipDosen().isEmpty()) {
                            startActivity(new Intent(SaveLocation.this, DosenMain.class));

                        } else {
                            startActivity(new Intent(SaveLocation.this, MhsMain.class));

                        }
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    progressDialog.dismiss();
                    Toast.makeText(SaveLocation.this, "Penyimpanan Lokasi Gagal!", Toast.LENGTH_SHORT).show();
                }
            });


        } catch (Exception e) {

        }


    }


    private void updateMapUI() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mMap != null && mAssetMarker != null) {
                    // Do something after 5s = 5000ms
                    if (mAddress.equals("")) {
                        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(assetLocation, 0.01f));
                    } else {
                        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(assetLocation, 10.0f));
                        mAssetMarker.setPosition(assetLocation);
                        mEtAssetAddress.setText(mAddress);
                    }
                }
            }
        }, 3800);
    }


    @Override
    public void onClick(View view) {

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;
        mMap.setIndoorEnabled(true);
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(assetLocation, 1.0f));

        if (EasyPermissions.hasPermissions(this, locationPerm)) {
            mMap.setMyLocationEnabled(true);
        } else {
            EasyPermissions.requestPermissions(this, "Izinkan aplikasi untuk mengakses lokasi anda", LOCATION_REQUEST, locationPerm);
        }

        mMap.setOnMapLongClickListener(this);
        mAssetMarkerOptions = new MarkerOptions();
        mAssetMarkerOptions.position(assetLocation);
        mAssetMarkerOptions.draggable(false);
        mAssetMarkerOptions.title("Lokasi Anda");
        mAssetMarker = mMap.addMarker(mAssetMarkerOptions);
        mAssetMarker.showInfoWindow();

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {


                mMap.getUiSettings().setMapToolbarEnabled(true);
                // return true will prevent any further map action from happening
                return false;

            }
        });

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {

            @Override
            public void onMapClick(LatLng point) {
                assetLocation = point;
                getAddressByLocation(assetLocation.latitude, assetLocation.longitude);
//                Toast.makeText(ActivityMapAsset.this, assetLocation.toString(), Toast.LENGTH_LONG).show();
            }
        });


    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {

    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {

    }


    @Override
    public void onMapLongClick(LatLng latLng) {
        Toast.makeText(SaveLocation.this, "Anda tidak bisa merubah lokasi..", Toast.LENGTH_LONG).show();
    }
}

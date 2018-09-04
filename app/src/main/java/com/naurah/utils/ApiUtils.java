package com.naurah.utils;

import com.naurah.service.APIService;

public class ApiUtils {

    public static final String BASE_URL = "http://192.168.43.229:3001/";

    public static APIService getAPIService(){
        return  RetrofitClient.getClientLogin(BASE_URL).create(APIService.class);
    }

}

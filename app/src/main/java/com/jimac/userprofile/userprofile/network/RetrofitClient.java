package com.jimac.userprofile.userprofile.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

class RetrofitClient {

    public static ApiWebClient getApiClient(String baseUrl) {
        return new
                Retrofit.Builder().baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(ApiWebClient.class);
    }

}

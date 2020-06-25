package com.jimac.userprofile.userprofile.network;

import com.jimac.userprofile.userprofile.network.model.UserProfile;
import com.jimac.userprofile.userprofile.network.model.UserProfileResponse;
import com.jimac.userprofile.userprofile.utils.INetworkListener;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WebServiceRepository {

    private ApiWebClient webClient;
    public WebServiceRepository(){
        webClient = RetrofitClient.getApiClient("https://run.mocky.io/v3/");
    }

    public void registerUserProfile(UserProfile userProfile, INetworkListener iNetworkListener){
        webClient.registerUserProfile(userProfile).enqueue(new Callback<UserProfileResponse>() {
            @Override
            public void onResponse(Call<UserProfileResponse> call, Response<UserProfileResponse> response) {
                if(response.isSuccessful()){
                    UserProfileResponse userProfileResponse = (UserProfileResponse) response.body();
                    iNetworkListener.showNetworkResponse(userProfileResponse.getMessage());
                }else{
                    iNetworkListener.showNetworkResponse(response.message());

                }
            }

            @Override
            public void onFailure(Call<UserProfileResponse> call, Throwable t) {
                iNetworkListener.showNetworkResponse(t.getMessage());
            }
        });
    }
}

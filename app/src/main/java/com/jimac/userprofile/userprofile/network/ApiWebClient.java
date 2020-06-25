package com.jimac.userprofile.userprofile.network;

import com.jimac.userprofile.userprofile.network.model.UserProfile;
import com.jimac.userprofile.userprofile.network.model.UserProfileResponse;

import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.Call;
interface ApiWebClient {

    @POST("42cc79d3-2079-4199-a6fa-02c56822f5a6")
    Call<UserProfileResponse> registerUserProfile(@Body UserProfile userProfile);
}

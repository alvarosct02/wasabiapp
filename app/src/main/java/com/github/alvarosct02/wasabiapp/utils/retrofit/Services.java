package com.github.alvarosct02.wasabiapp.utils.retrofit;

import com.github.alvarosct02.wasabiapp.models.Session;

import retrofit2.Call;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Services {

    @GET(Urls.GET_USER_SESSION)
    Call<Session> getUserSession(@Path(Urls.USER_ID) String userId);

}

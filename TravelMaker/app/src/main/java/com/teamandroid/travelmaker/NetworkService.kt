package com.teamandroid.travelmaker

import com.teamandroid.travelmaker.get.GetNaverLoginResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface NetworkService {

    @GET("v1/nid/me")
    fun getUserProfile(@Header("Authorization") header : String) : Call<GetNaverLoginResponse>
}
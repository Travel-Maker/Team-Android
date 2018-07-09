package com.teamandroid.travelmaker

import com.teamandroid.travelmaker.get.GetNaverLoginResponse
import com.teamandroid.travelmaker.post.PostSignIn
import com.teamandroid.travelmaker.post.PostSignup
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface NetworkService {

    @GET("v1/nid/me")
    fun getUserProfile(@Header("Authorization") header : String) : Call<GetNaverLoginResponse>

    @POST("/user/signin")
    fun postUserSignIn(user_id : String) : Call<PostSignIn>

    @POST("/user/signup")
    fun postUserSignup(naverUserInfo: NaverUserInfo) : Call<PostSignup>
}
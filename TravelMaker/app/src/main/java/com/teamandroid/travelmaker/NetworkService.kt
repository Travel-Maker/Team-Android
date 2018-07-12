package com.teamandroid.travelmaker

import com.teamandroid.travelmaker.get.GetApplicationDetail
import com.teamandroid.travelmaker.get.GetCountryDetail
import com.teamandroid.travelmaker.get.GetNaverLoginResponse
import com.teamandroid.travelmaker.post.*
import retrofit2.Call
import retrofit2.http.*

interface NetworkService {

    @GET("v1/nid/me")
    fun getUserProfile(@Header("Authorization") header : String) : Call<GetNaverLoginResponse>

    @FormUrlEncoded
    @POST("user/signin")
    fun postUserSignIn(@Field("user_id") user_id : String) : Call<PostSignIn>

    @FormUrlEncoded
    @POST("user/signup")
    fun postUserSignup(@Field("user_id") user_id : String, @Field("user_name") user_name : String,
                       @Field("user_age") user_age : String, @Field("user_gender") user_gender : String,
                       @Field("user_nick") user_nick : String , @Field("user_email") user_email : String,
                       @Field("user_style") user_style : String, @Field("user_img") user_img : String
    ) : Call<PostSignup>

    @GET("country/{country_idx}")
    fun getCountryDetail(@Path("country_idx") country_idx : Int) : Call<GetCountryDetail>

    @POST("board")
    fun postWriteApplication(@Header("token")token : String, @Body writeApplication: WriteApplication) : Call<PostWirteApplication>

    @GET("board/detail/{board_idx}")
    fun getdetailApplication(@Path("board_idx") board_idx : Int) : Call<GetApplicationDetail>

    @POST("plan/send")
    fun getSendApplication(@Header("token")token : String) : Call<PostSendApplication>

    @POST("plan/receive")
    fun getReceiveApplication(@Header("token")token : String) : Call<PostReceiveApplication>
}
package com.teamandroid.travelmaker

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.nhn.android.naverlogin.OAuthLogin
import com.nhn.android.naverlogin.OAuthLoginHandler
import com.teamandroid.travelmaker.get.GetNaverLoginResponse
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    companion object {
        val OAUTH_CLIENT_ID = "tAUdbi7BhPBDG0t6n1an"
        val OAUTH_CLIENT_SECRET = "QNKg4YJ5aZ"
        val OAUTH_CLIENT_NAME = "travelmaker"
    }
    lateinit var accessToken : String
    lateinit var mOAuthClientModule : OAuthLogin
    var flag = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mOAuthClientModule = OAuthLogin.getInstance()
        mOAuthClientModule.init(this, OAUTH_CLIENT_ID, OAUTH_CLIENT_SECRET, OAUTH_CLIENT_NAME)
        btn_naverLogin.setOAuthLoginHandler(mOAuthLoginHandler)
    }

    val mOAuthLoginHandler = object : OAuthLoginHandler(){
        override fun run(success: Boolean) {
            if(success){
                Log.d("UserProfile",mOAuthClientModule.getAccessToken(applicationContext))
                requireUserProfile()
            }

            else{
                val errorCode = mOAuthClientModule.getLastErrorCode(applicationContext).code
                val errorDesc = mOAuthClientModule.getLastErrorDesc(applicationContext)
                Log.e("NaverLoginError", "errorCode:" + errorCode  + ", errorDesc:" + errorDesc)
            }
        }
    }

    fun requireUserProfile(){
        (applicationContext as TravelMakerApplication).makeNetworkService("https://openapi.naver.com/")
        val getUserProfile =  (applicationContext as TravelMakerApplication).getApplicationNetworkService().getUserProfile("Bearer "+mOAuthClientModule.getAccessToken(applicationContext))
        getUserProfile.enqueue(object : Callback<GetNaverLoginResponse> {
            override fun onFailure(call: Call<GetNaverLoginResponse>?, t: Throwable?) {
                Log.d("UserProfile","Error")
            }

            override fun onResponse(call: Call<GetNaverLoginResponse>?, response: Response<GetNaverLoginResponse>?) {
                if(response!!.isSuccessful){
                    val userProfile = response.body()!!.response
                    Log.d("UserProfile","OK")
                }
                Log.d("UserProfile",response.headers().toString())
                Log.d("UserProfile",response.code().toString())
            }
        })
    }
}

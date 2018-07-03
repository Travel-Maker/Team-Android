package com.teamandroid.travelmaker.login

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nhn.android.naverlogin.OAuthLogin
import com.nhn.android.naverlogin.OAuthLoginHandler
import com.teamandroid.travelmaker.R
import com.teamandroid.travelmaker.TravelMakerApplication
import com.teamandroid.travelmaker.get.GetNaverLoginResponse
import kotlinx.android.synthetic.main.fragment_login.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginFragment :Fragment() {

    lateinit var mContext : Context
    lateinit var activity: LoginActivity
    lateinit var mOAuthClientModule : OAuthLogin

    companion object {
        val OAUTH_CLIENT_ID = "tAUdbi7BhPBDG0t6n1an"
        val OAUTH_CLIENT_SECRET = "QNKg4YJ5aZ"
        val OAUTH_CLIENT_NAME = "travelmaker"
        val NAVER_LOGIN_URL = "https://openapi.naver.com/"
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        activity = getActivity() as LoginActivity
        mContext = activity.applicationContext

        val view = inflater.inflate(R.layout.fragment_login, container, false)

        mOAuthClientModule = OAuthLogin.getInstance()
        mOAuthClientModule.init(mContext, OAUTH_CLIENT_ID, OAUTH_CLIENT_SECRET,OAUTH_CLIENT_NAME)


        val mOAuthLoginHandler = object : OAuthLoginHandler(){
            override fun run(success: Boolean) {
                if(success){
                    Log.d("UserProfile",mOAuthClientModule.getAccessToken(mContext))
                    requireUserProfile()
                }

                else{
                    val errorCode = mOAuthClientModule.getLastErrorCode(mContext).code
                    val errorDesc = mOAuthClientModule.getLastErrorDesc(mContext)
                    Log.e("NaverLoginError", "errorCode:" + errorCode  + ", errorDesc:" + errorDesc)
                }
            }
        }

        view.btn_naverLogin.setOAuthLoginHandler(mOAuthLoginHandler)

        return view
    }


    private fun requireUserProfile(){
        (mContext as TravelMakerApplication).makeNetworkService(NAVER_LOGIN_URL)
        val getUserProfile =  (mContext as TravelMakerApplication).getApplicationNetworkService().getUserProfile("Bearer "+mOAuthClientModule.getAccessToken(mContext))
        getUserProfile.enqueue(object : Callback<GetNaverLoginResponse> {
            override fun onFailure(call: Call<GetNaverLoginResponse>?, t: Throwable?) {
                Log.d("UserProfile","Error")
            }

            override fun onResponse(call: Call<GetNaverLoginResponse>?, response: Response<GetNaverLoginResponse>?) {
                if(response!!.isSuccessful){
                    val userProfile = response.body()!!.response
                    Log.d("UserProfile","OK")
                    //여기서 서버로 정보를 보낸다.
                    //가입이 된 사람이면, MainActivity로
                    //가입이 안된 사람이면, 닉네임 설정으로
                }
            }
        })
    }
}
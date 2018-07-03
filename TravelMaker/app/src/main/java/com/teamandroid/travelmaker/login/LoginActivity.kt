package com.teamandroid.travelmaker.login

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import com.teamandroid.travelmaker.R

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        changeFragment(LoginFragment())
    }

    fun changeFragment(fragment : Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.login_container,fragment).commit()
    }

}

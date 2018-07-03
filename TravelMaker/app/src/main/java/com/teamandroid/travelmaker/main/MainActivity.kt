package com.teamandroid.travelmaker.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import com.teamandroid.travelmaker.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        changeFragment(HomeFragment())
    }


    fun changeFragment(fragment : Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.main_container,fragment).commit()
    }
}

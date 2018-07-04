package com.teamandroid.travelmaker.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import android.widget.ImageView
import com.teamandroid.travelmaker.R
import com.teamandroid.travelmaker.main.favorite.FavoriteFragment
import com.teamandroid.travelmaker.main.home.HomeFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var currentSelected : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigationBar_home.setOnClickListener(this)
        navigationBar_favorite.setOnClickListener(this)
        navigationBar_receive.setOnClickListener(this)
        navigationBar_send.setOnClickListener(this)
        navigationBar_mypage.setOnClickListener(this)

        currentSelected = navigationBar_home
        currentSelected.isSelected = true
        changeFragment(HomeFragment())
    }


    fun changeFragment(fragment : Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.main_container,fragment).commit()
    }

    fun registCurrentSelected(candiate : ImageView){
        if(currentSelected != candiate){
            currentSelected.isSelected = false
            currentSelected = candiate
            currentSelected.isSelected = true
        }
    }

    override fun onClick(v: View?) {

        if(v == navigationBar_home){
            registCurrentSelected(navigationBar_home)
            changeFragment(HomeFragment())
        }
        else if(v == navigationBar_favorite){
            registCurrentSelected(navigationBar_favorite)
            changeFragment(FavoriteFragment())
        }
    }
}

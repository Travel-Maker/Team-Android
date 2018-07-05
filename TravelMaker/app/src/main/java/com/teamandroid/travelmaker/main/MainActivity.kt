package com.teamandroid.travelmaker.main

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat.startActivityForResult
import android.support.v4.app.Fragment
import android.support.v7.app.ActionBar
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import com.teamandroid.travelmaker.R
import com.teamandroid.travelmaker.R.id.*
import com.teamandroid.travelmaker.search.SearchActivity
import com.teamandroid.travelmaker.main.favorite.FavoriteFragment
import com.teamandroid.travelmaker.main.home.HomeFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var currentSelected : ImageView
    lateinit var actionBar: ActionBar
    lateinit var beforeFragment: Fragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigationBar_home.setOnClickListener(this)
        navigationBar_favorite.setOnClickListener(this)
        navigationBar_receive.setOnClickListener(this)
        navigationBar_send.setOnClickListener(this)
        navigationBar_mypage.setOnClickListener(this)

        btn_searchActivity.setOnClickListener(this)

        setSupportActionBar(main_toolBar)
        actionBar = supportActionBar!!

        currentSelected = navigationBar_home
        currentSelected.isSelected = true

        registCurrentSelected(currentSelected)
        supportFragmentManager.beginTransaction().replace(R.id.main_container,HomeFragment()).commit()

    }

    fun setDisplayHomeAsUpEnabled(flag: Boolean) {

        actionBar.setDisplayHomeAsUpEnabled(flag)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        if(item!!.itemId == android.R.id.home){
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        val manager = supportFragmentManager
        Log.d("num",manager.backStackEntryCount.toString())
        if (manager.backStackEntryCount > 0) {
            if(beforeFragment is HomeFragment){
                registCurrentSelected(navigationBar_home)
            }
            else if(beforeFragment is FavoriteFragment){
                registCurrentSelected(navigationBar_favorite)
            }
            else{
                setDisplayHomeAsUpEnabled(true)
            }
            manager.popBackStack()
            beforeFragment = supportFragmentManager.findFragmentById(R.id.main_container)
        } else {
            super.onBackPressed()
        }
    }

    fun changeMainFragment(fragment : Fragment, candidate : ImageView){
        registCurrentSelected(candidate)
        changeFragment(fragment)
    }


    fun changeEtcFragment(fragment : Fragment){
        changeFragment(fragment)
        setDisplayHomeAsUpEnabled(true)
    }

    fun changeFragment(fragment: Fragment){
        beforeFragment = supportFragmentManager.findFragmentById(R.id.main_container)

        val transaction = supportFragmentManager.beginTransaction()
        transaction.addToBackStack(null)
        transaction.replace(R.id.main_container,fragment).commit()
    }

    fun registCurrentSelected(candiate : ImageView){
        if(currentSelected != candiate){
            currentSelected.isSelected = false
            currentSelected = candiate
            currentSelected.isSelected = true
        }
        setDisplayHomeAsUpEnabled(false)
    }



    override fun onClick(v: View?) {

        if(v == navigationBar_home){
            changeMainFragment(HomeFragment(),navigationBar_home)
        }
        else if(v == navigationBar_favorite){
            changeMainFragment(FavoriteFragment(),navigationBar_favorite)
        }

        else if(v == btn_searchActivity){
            val intent = Intent(this, SearchActivity::class.java)
            startActivityForResult(intent, 100)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }
}

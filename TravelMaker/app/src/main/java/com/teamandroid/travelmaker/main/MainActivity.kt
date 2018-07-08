package com.teamandroid.travelmaker.main

import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat.startActivityForResult
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.app.ActionBar
import android.util.Log
import android.view.View
import android.widget.ImageView
import com.teamandroid.travelmaker.MainPage
import com.teamandroid.travelmaker.R
import com.teamandroid.travelmaker.search.SearchActivity
import com.teamandroid.travelmaker.main.favorite.FavoriteFragment
import com.teamandroid.travelmaker.main.home.HomeFragment
import com.teamandroid.travelmaker.main.receive.ReceiveFragment
import kotlinx.android.synthetic.main.activity_main.*
import android.view.WindowManager
import com.teamandroid.travelmaker.R.id.*
import com.teamandroid.travelmaker.detail.DetailActivity


class MainActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var currentSelected : ImageView
    lateinit var actionBar: ActionBar

    lateinit var categories : ArrayList<Category>
    lateinit var countryData : ArrayList<CountryData>

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

        initActivityDesign()

        currentSelected = navigationBar_home
        currentSelected.isSelected = true

        makeCountryData()

        val fragment = HomeFragment.newInstance(categories)
        supportFragmentManager.beginTransaction().replace(R.id.main_container,fragment,"navigationBar_home").commit()

    }


    override fun onBackPressed() {
        val manager = supportFragmentManager
        if (manager.backStackEntryCount > 0) {
            setNavigationSelected(manager.findFragmentById(R.id.main_container).tag as String)
            manager.popBackStack()
        } else {
            super.onBackPressed()
        }
    }


    fun changeFragment(fragment: Fragment){
        val manager = supportFragmentManager
        var myNavigation = ""
        val currentFragment = manager.findFragmentById(R.id.main_container)

        if(fragment is HomeFragment)
            myNavigation = "navigationBar_home"

        else if(fragment is FavoriteFragment){
            myNavigation = "navigationBar_favorite"
        }

        else if(fragment is ReceiveFragment){
            myNavigation = "navigationBar_receive"
        }

        manager.beginTransaction().addToBackStack(null).replace(R.id.main_container, fragment,currentFragment.tag as String).commit()

        setNavigationSelected(myNavigation)
    }


    fun setNavigationSelected(tag : String){
        currentSelected.isSelected = false
        when(tag){
            "navigationBar_home" -> currentSelected = navigationBar_home
            "navigationBar_favorite" -> currentSelected = navigationBar_favorite
            "navigationBar_receive" -> currentSelected = navigationBar_receive
            "navigationBar_send" -> currentSelected = navigationBar_send
            "navigationBar_mypage" -> currentSelected = navigationBar_mypage
        }
        currentSelected.isSelected = true
    }


    override fun onClick(v: View?) {

        if(v == navigationBar_home){
            if(currentSelected != v) {
                val fragment = HomeFragment.newInstance(categories)
                changeFragment(fragment)
            }
        }
        else if(v == navigationBar_favorite){
            if(currentSelected !=v) {
                val fragment = FavoriteFragment.newInstance(categories)
                changeFragment(fragment)
            }
        }
        else if(v == navigationBar_receive){
            if(currentSelected !=v){
                changeFragment(ReceiveFragment())
            }
        }
        else if(v == btn_searchActivity){
            val intent = Intent(this, SearchActivity::class.java)

            intent.putParcelableArrayListExtra("countryData",countryData)
            startActivityForResult(intent, 100)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode == 100 && resultCode == 100){
            val index = data!!.getIntExtra("index",-1)

            for(i in 0..(categories.size - 1)){
                for(j in 0..(categories[i].country.size - 1)){
                    if(categories[i].country[j].countryData.index == index){
                        val intent = Intent(applicationContext, DetailActivity::class.java)
                        intent.putExtra("country",categories[i].country[j].countryData)
                        startActivity(intent)
                    }
                }
            }
        }
    }

    private fun makeCountryData(){

        countryData = ArrayList()

        countryData.add(CountryData(0, "대한민국", true))
        countryData.add(CountryData(1, "중국", true))
        countryData.add(CountryData(2, "일본", true))

        countryData.add(CountryData(3, "영국", false))
        countryData.add(CountryData(4, "프랑스", true))
        countryData.add(CountryData(5, "스페인", true))

        countryData.add(CountryData(6, "캐나다", false))
        countryData.add(CountryData(7, "미국", true))
        countryData.add(CountryData(8, "멕시코", false))

        categories = ArrayList()
        var countries =  ArrayList<Country>()

        countries.add(Country(countryData[0],BitmapFactory.decodeResource(resources,R.drawable.korea_img_big),
                BitmapFactory.decodeResource(resources, R.drawable.korea_detail_img)))
        countries.add(Country(countryData[1],BitmapFactory.decodeResource(resources,R.drawable.china_img_big)
                ,BitmapFactory.decodeResource(resources, R.drawable.china_detail_img)))
        countries.add(Country(countryData[2],BitmapFactory.decodeResource(resources,R.drawable.japan_img_big)
                ,BitmapFactory.decodeResource(resources, R.drawable.japan_detail_img)))

        categories.add(Category("아시아",countries))

        countries = ArrayList()

        countries.add(Country(countryData[3],BitmapFactory.decodeResource(resources,R.drawable.uk_img_big)
                ,BitmapFactory.decodeResource(resources, R.drawable.uk_detail_img)))
        countries.add(Country(countryData[4],BitmapFactory.decodeResource(resources,R.drawable.france_img_big)
                ,BitmapFactory.decodeResource(resources, R.drawable.france_detail_img)))
        countries.add(Country(countryData[5],BitmapFactory.decodeResource(resources,R.drawable.spain_img_big)
                ,BitmapFactory.decodeResource(resources, R.drawable.spain_detail_img)))

        categories.add(Category("유럽",countries))

        countries = ArrayList()

        countries.add(Country(countryData[6],BitmapFactory.decodeResource(resources,R.drawable.canada_img_big)
                ,BitmapFactory.decodeResource(resources, R.drawable.canada_detail_img)))
        countries.add(Country(countryData[7],BitmapFactory.decodeResource(resources,R.drawable.usa_img_big)
                ,BitmapFactory.decodeResource(resources, R.drawable.usa_detail_img)))
        countries.add(Country(countryData[8],BitmapFactory.decodeResource(resources,R.drawable.mexico_img_big)
                ,BitmapFactory.decodeResource(resources, R.drawable.mexico_detail_img)))

        categories.add(Category("북아메리카",countries))

    }

    fun initActivityDesign(){

        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){

            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            window.statusBarColor = resources.getColor(R.color.statusbarWhite, null)
        }
    }
}

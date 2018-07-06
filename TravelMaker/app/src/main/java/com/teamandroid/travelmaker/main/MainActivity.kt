package com.teamandroid.travelmaker.main

import android.content.Intent
import android.graphics.BitmapFactory
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.ActionBar
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import com.teamandroid.travelmaker.R
import com.teamandroid.travelmaker.main.etc.CountryDetailFragment
import com.teamandroid.travelmaker.search.SearchActivity
import com.teamandroid.travelmaker.main.favorite.FavoriteFragment
import com.teamandroid.travelmaker.main.home.HomeFragment
import com.teamandroid.travelmaker.main.receive.ReceiveFragment
import com.teamandroid.travelmaker.search.SearchData
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var currentSelected : ImageView
    lateinit var actionBar: ActionBar

    lateinit var categories : ArrayList<Category>
    lateinit var countryThumbnails: ArrayList<CountryThumbnail>
    lateinit var searchData : ArrayList<SearchData>

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
        currentSelected.tag = "navigationBar_home"

        makeHomeData()
        makeSearchData()

        val fragment = HomeFragment.newInstance(categories)
        supportFragmentManager.beginTransaction().replace(R.id.main_container,fragment,"HomeFragment").commit()

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

        if (manager.backStackEntryCount > 0) {
            val name = manager.getBackStackEntryAt(manager.backStackEntryCount - 1).name
            if(name.compareTo("HomeFragment") == 0){
                registCurrentSelected(navigationBar_home)
            }
            else if(name.compareTo("FavoriteFragment") == 0){
                registCurrentSelected(navigationBar_favorite)
            }
            else if(name.compareTo("ReceiveFragment") == 0){
                registCurrentSelected(navigationBar_receive)
            }
            else{
                currentSelected.isSelected = false
                when(currentSelected.tag.toString()){
                    "navigationBar_home" -> currentSelected = navigationBar_home
                    "navigationBar_favorite" -> currentSelected = navigationBar_favorite
                    "navigationBar_receive" -> currentSelected = navigationBar_receive
                    "navigationBar_send" -> currentSelected = navigationBar_send
                    "navigationBar_mypage" -> currentSelected = navigationBar_mypage
                }
                currentSelected.isSelected = true
                setDisplayHomeAsUpEnabled(true)
            }
            manager.popBackStack()
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
        val manager = supportFragmentManager
        var name = manager.findFragmentById(R.id.main_container).tag

        val transaction = supportFragmentManager.beginTransaction()

        if(name!!.compareTo("EtcFragment") == 0) {
            transaction.addToBackStack(currentSelected.tag as String)
        }
        else{
            transaction.addToBackStack(name)
        }

        if(fragment is HomeFragment){
            name = "HomeFragment"
        }
        else if(fragment is FavoriteFragment){
            name = "FavoriteFragment"
        }
        else if(fragment is ReceiveFragment){
            name = "ReceiveFragment"
        }
        else{
            name = "EtcFragment"
        }
        transaction.replace(R.id.main_container,fragment,name).commitAllowingStateLoss()
    }

    fun registCurrentSelected(candiate : ImageView){

        if(currentSelected != candiate){
            currentSelected.isSelected = false
            when(currentSelected){
                navigationBar_home -> candiate.tag = "navigationBar_home"
                navigationBar_favorite -> candiate.tag = "navigationBar_favorite"
                navigationBar_receive -> candiate.tag = "navigationBar_receive"
                navigationBar_send -> candiate.tag = "navigationBar_send"
                navigationBar_mypage -> candiate.tag = "navigationBar_mypage"
            }
            currentSelected = candiate
            currentSelected.isSelected = true
        }
        setDisplayHomeAsUpEnabled(false)
    }



    override fun onClick(v: View?) {

        if(v == navigationBar_home){
            val fragment = HomeFragment.newInstance(categories)
            changeMainFragment(fragment,navigationBar_home)
        }
        else if(v == navigationBar_favorite){
            changeMainFragment(FavoriteFragment(),navigationBar_favorite)
        }
        else if(v == navigationBar_receive){
            changeMainFragment(ReceiveFragment(), navigationBar_receive)
        }
        else if(v == btn_searchActivity){
            val intent = Intent(this, SearchActivity::class.java)

            intent.putParcelableArrayListExtra("searchData",searchData)
            startActivityForResult(intent, 100)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        //super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 100 && resultCode == 100){
            val index = data!!.getIntExtra("index",-1)
            Log.d("index",index.toString())
            changeEtcFragment(CountryDetailFragment())
        }
    }
    private fun makeSearchData(){
        searchData = ArrayList()
        for(i in 0..(categories.size - 1)){
            for(j in 0..(categories[i].country.size - 1 )){
                searchData.add(SearchData(categories[i].country[j].index, categories[i].country[j].name))
            }
        }
    }

    private fun makeHomeData(){
        categories = ArrayList()
        countryThumbnails = ArrayList()

        countryThumbnails.add(CountryThumbnail(0, "대한민국",BitmapFactory.decodeResource(resources,R.drawable.korea_img_big)))
        countryThumbnails.add(CountryThumbnail(1, "중국",BitmapFactory.decodeResource(resources,R.drawable.china_img_big)))
        countryThumbnails.add(CountryThumbnail(2, "일본",BitmapFactory.decodeResource(resources,R.drawable.japan_img_big)))

        categories.add(Category("아시아",countryThumbnails))

        countryThumbnails = ArrayList()

        countryThumbnails.add(CountryThumbnail(3, "영국",BitmapFactory.decodeResource(resources,R.drawable.uk_img_big)))
        countryThumbnails.add(CountryThumbnail(4, "프랑스",BitmapFactory.decodeResource(resources,R.drawable.france_img_big)))
        countryThumbnails.add(CountryThumbnail(5, "스페인",BitmapFactory.decodeResource(resources,R.drawable.spain_img_big)))

        categories.add(Category("유럽",countryThumbnails))

        countryThumbnails = ArrayList()

        countryThumbnails.add(CountryThumbnail(6, "캐나다",BitmapFactory.decodeResource(resources,R.drawable.canada_img_big)))
        countryThumbnails.add(CountryThumbnail(7, "미국",BitmapFactory.decodeResource(resources,R.drawable.usa_img_big)))
        countryThumbnails.add(CountryThumbnail(8, "멕시코",BitmapFactory.decodeResource(resources,R.drawable.mexico_img_big)))

        categories.add(Category("북아메리카",countryThumbnails))

    }
}

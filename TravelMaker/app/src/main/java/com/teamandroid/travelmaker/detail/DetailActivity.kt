package com.teamandroid.travelmaker.detail

import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.view.View
import android.view.WindowManager
import com.teamandroid.travelmaker.R
import com.teamandroid.travelmaker.main.Country
import com.teamandroid.travelmaker.main.CountryData
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity(), View.OnClickListener{

    lateinit var country : Country
    lateinit var stackFragment : ArrayList<Fragment>
    var isBackPress = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val countryData = intent.getParcelableExtra<CountryData>("country")
        var img = 0
        when(countryData.index){
            0 -> img = R.drawable.korea_detail_img
            1 -> img = R.drawable.china_detail_img
            2 -> img = R.drawable.japan_detail_img
            3 -> img = R.drawable.uk_detail_img
            4 -> img = R.drawable.france_detail_img
            5 -> img = R.drawable.spain_detail_img
            6 -> img = R.drawable.canada_detail_img
            7 -> img = R.drawable.usa_detail_img
            8 -> img = R.drawable.mexico_detail_img
        }
        country = Country(countryData,null,BitmapFactory.decodeResource(resources, img) )

        btn_close.setOnClickListener(this)
        countryName.text = country.countryData.name

        stackFragment = ArrayList()

        val fragment = CountryDetailFragment.newInstance(country)
        supportFragmentManager.beginTransaction().replace(R.id.other_container,fragment).commit()
    }

    fun changeFragment(fragment: Fragment){
        val manager = supportFragmentManager
        var container : Int

        if(main_container.visibility == View.VISIBLE){
            container = R.id.main_container
        }
        else{
            container = R.id.other_container
        }

        val currentFragment = manager.findFragmentById(container)

        if(fragment is CountryDetailFragment){
            countryDetailDesign()
            container = R.id.other_container
            main_container.visibility = View.INVISIBLE
            other_container.visibility = View.VISIBLE
        }

        else{
            otherDesign()
            container = R.id.main_container
            main_container.visibility = View.VISIBLE
            other_container.visibility = View.INVISIBLE
        }

        if(!isBackPress) {
            stackFragment.add(currentFragment)
            manager.beginTransaction().addToBackStack(null).replace(container, fragment).commit()
        }
        else{
            stackFragment.removeAt(stackFragment.size - 1)
        }

        isBackPress = false
    }

    override fun onClick(v: View?) {
        if(v == btn_close){
            finish()
        }
    }

    override fun onBackPressed(){

        if (stackFragment.size > 0) {
            isBackPress = true
            changeFragment(stackFragment[stackFragment.size - 1])
            supportFragmentManager.popBackStack()

        } else {
            super.onBackPressed()
        }
    }

    fun countryDetailDesign(){
        detail_toolBar.background = null
        detail_toolBar.setBackgroundColor(Color.parseColor("#20000000"))
        countryName.setTextColor(Color.parseColor("#F1F6FD"))
        detail_toolBar.setPadding(0,getStatusBarHeight(),0,0)
        btn_close.setImageResource(R.drawable.x_icon_white)
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
    }

    fun otherDesign(){
        detail_toolBar.background = ContextCompat.getDrawable(this,R.drawable.toolbar_background_normal)
        detail_toolBar.setBackgroundColor(Color.TRANSPARENT)
        countryName.setTextColor(Color.parseColor("#049DED"))
        detail_toolBar.setPadding(0,0,0,0)
        btn_close.setImageResource(R.drawable.x_icon_blue)

        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){

            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            window.statusBarColor = resources.getColor(R.color.statusbarWhite, null)
        }
    }

    private fun getStatusBarHeight(): Int {
        var result = 0
        val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId > 0)
            result = resources.getDimensionPixelSize(resourceId)

        return result
    }
}

package com.teamandroid.travelmaker.main.home

import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.teamandroid.travelmaker.R
import com.teamandroid.travelmaker.main.CountryThumbnail
import kotlinx.android.synthetic.main.home_viewpager_item.view.*

class CountryThumbnailAdapter(var countryThumbnails : ArrayList<CountryThumbnail>) : PagerAdapter() {

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun getCount(): Int  = countryThumbnails.size

    override fun getItemPosition(`object`: Any): Int {
        return super.getItemPosition(`object`)
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = LayoutInflater.from(container.context).inflate(R.layout.home_viewpager_item, container,false)
                //TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 120, re.)

        //view.rc_vp_countryImage.setImageBitmap(countryThumbnails[position].bitmp)
        view.rc_vp_countryName.text = countryThumbnails[position].name

        container.addView(view)
        return view
    }

}
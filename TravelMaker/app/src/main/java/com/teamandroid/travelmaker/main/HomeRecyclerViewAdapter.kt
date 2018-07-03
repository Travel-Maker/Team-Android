package com.teamandroid.travelmaker.main

import android.support.v4.view.ViewPager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.teamandroid.travelmaker.R
import android.R.attr.x
import android.content.Context
import android.graphics.Point
import com.teamandroid.travelmaker.InfinitePagerAdapter


class HomeRecyclerViewAdapter(var categories: ArrayList<Category>, var activity: MainActivity) : RecyclerView.Adapter<HomeRecyclerViewHolder>(){
    lateinit var mContext : Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeRecyclerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.home_recyclerview_item, parent, false)
        mContext = parent.context
        return HomeRecyclerViewHolder(view)
    }

    override fun getItemCount(): Int = categories.size

    override fun onBindViewHolder(holder: HomeRecyclerViewHolder, position: Int) {
        holder.viewPager.adapter = InfinitePagerAdapter(CountryThumbnailAdapter(categories[position].countryThumbnails))
        holder.viewPager.setPadding(350, 0, 350, 0)

        val screen = Point()
        activity.windowManager.defaultDisplay.getSize(screen)
        val startOffset = 350.0f / (screen.x - 2 * 350.0f)
        holder.viewPager.setPageTransformer(false, CardPagerTransformerShift(holder.viewPager.elevation,holder.viewPager.elevation * 0.5f,
                0.6f, startOffset))
        holder.countryName.text = categories[position].categoryTitle
    }


}
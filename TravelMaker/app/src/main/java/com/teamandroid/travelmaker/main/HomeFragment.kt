package com.teamandroid.travelmaker.main

import android.graphics.BitmapFactory
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.teamandroid.travelmaker.R
import kotlinx.android.synthetic.main.fragment_home.view.*

class HomeFragment : Fragment() {
    lateinit var categories : ArrayList<Category>
    lateinit var countryThumbnails: ArrayList<CountryThumbnail>
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_home,container,false)

        categories = ArrayList()
        countryThumbnails = ArrayList()

        countryThumbnails.add(CountryThumbnail(0,"중국"))
        countryThumbnails.add(CountryThumbnail(1,"한국"))
        countryThumbnails.add(CountryThumbnail(2,"일본"))

        categories.add(Category("아시아",countryThumbnails))

        view.home_recyclerView.adapter = HomeRecyclerViewAdapter(categories,activity as MainActivity)
        view.home_recyclerView.layoutManager = LinearLayoutManager(container!!.context)
        return view
    }
}
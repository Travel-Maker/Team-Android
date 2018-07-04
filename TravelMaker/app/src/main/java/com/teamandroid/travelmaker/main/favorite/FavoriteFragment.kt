package com.teamandroid.travelmaker.main.favorite

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.teamandroid.travelmaker.Expert
import com.teamandroid.travelmaker.R
import com.teamandroid.travelmaker.main.CountryThumbnail
import com.teamandroid.travelmaker.main.home.HomeRecyclerViewAdapter
import kotlinx.android.synthetic.main.fragment_favorite.view.*
import kotlinx.android.synthetic.main.fragment_home.view.*

class FavoriteFragment : Fragment() {
    lateinit var countryThumbnails: ArrayList<CountryThumbnail>
    lateinit var experts : ArrayList<Expert>
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_favorite,container,false)

        countryThumbnails = ArrayList()

        countryThumbnails.add(CountryThumbnail(10, "프랑스"))
        countryThumbnails.add(CountryThumbnail(11, "미국"))
        countryThumbnails.add(CountryThumbnail(12, "영국"))
        countryThumbnails.add(CountryThumbnail(0, "한국"))
        countryThumbnails.add(CountryThumbnail(1, "일본"))

        experts = ArrayList()
        experts.add(Expert("ABCDEFG"))
        experts.add(Expert("ABCDEFG"))
        experts.add(Expert("ABCDEFG"))

        view.recycler_country.adapter = CountryRecyclerViewAdapter(countryThumbnails)
        view.recycler_country.layoutManager = LinearLayoutManager(container!!.context,LinearLayoutManager.HORIZONTAL,false)

        view.recycler_expert.adapter = PersonRecyclerViewAdapter(experts)
        view.recycler_expert.layoutManager = LinearLayoutManager(container.context)

        return view
    }
}
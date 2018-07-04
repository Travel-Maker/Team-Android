package com.teamandroid.travelmaker.main.favorite

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.teamandroid.travelmaker.R
import com.teamandroid.travelmaker.main.CountryThumbnail

class CountryRecyclerViewAdapter(var countryThumbnails : ArrayList<CountryThumbnail>) : RecyclerView.Adapter<CountryRecyclerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryRecyclerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_favorite_country_item, parent, false)
        return CountryRecyclerViewHolder(view)
    }

    override fun getItemCount(): Int = countryThumbnails.size

    override fun onBindViewHolder(holder: CountryRecyclerViewHolder, position: Int) {
        //holder.countryImage.setImageBitmap(countryThumbnails[position].)
        holder.countryName.text = countryThumbnails[position].name
    }
}
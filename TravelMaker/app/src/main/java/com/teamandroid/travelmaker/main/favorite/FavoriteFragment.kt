package com.teamandroid.travelmaker.main.favorite

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.teamandroid.travelmaker.Expert
import com.teamandroid.travelmaker.MainPage
import com.teamandroid.travelmaker.R
import com.teamandroid.travelmaker.RecyclerItemClickListener
import com.teamandroid.travelmaker.main.Category
import com.teamandroid.travelmaker.main.Country
import com.teamandroid.travelmaker.main.MainActivity
import com.teamandroid.travelmaker.detail.CountryDetailFragment
import kotlinx.android.synthetic.main.fragment_favorite.view.*

class FavoriteFragment : Fragment(), MainPage {

    lateinit var country : ArrayList<Country>
    lateinit var experts : ArrayList<Expert>

    companion object {
        fun newInstance(categories : ArrayList<Category>): FavoriteFragment {
            val fragment = FavoriteFragment()
            val temp  = ArrayList<Country>()

            for(i in 0..(categories.size - 1 )){
                for(j in 0..(categories[i].country.size -1 )){
                    if(categories[i].country[j].countryData.favorite)
                        temp.add(categories[i].country[j])
                }
            }
            fragment.country = temp
            return fragment
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_favorite,container,false)

        (activity as MainActivity).initActivityDesign()

        experts = ArrayList()
        experts.add(Expert("ABCDEFG"))
        experts.add(Expert("ABCDEFG"))
        experts.add(Expert("ABCDEFG"))


        view.recycler_country.adapter = CountryRecyclerViewAdapter(country)
        view.recycler_country.layoutManager = LinearLayoutManager(container!!.context,LinearLayoutManager.HORIZONTAL,false)
        view.recycler_country.addOnItemTouchListener(RecyclerItemClickListener(activity!!.applicationContext, view.recycler_country,
                object : RecyclerItemClickListener.OnItemClickListener{
                    override fun onItemClick(view: View, position: Int) {
                        val fragment = CountryDetailFragment.newInstance(country[position])
                        (activity as MainActivity).changeFragment(fragment)
                    }

                    override fun onLongItemClick(view: View, position: Int) {}
                }))

        view.recycler_expert.adapter = PersonRecyclerViewAdapter(experts)
        view.recycler_expert.layoutManager = LinearLayoutManager(container.context)
        view.recycler_expert.addOnItemTouchListener(RecyclerItemClickListener(activity!!.applicationContext, view.recycler_expert,
                object : RecyclerItemClickListener.OnItemClickListener{
                    override fun onItemClick(view: View, position: Int) {
                    }

                    override fun onLongItemClick(view: View, position: Int) {

                    }
                }))
        return view
    }
}
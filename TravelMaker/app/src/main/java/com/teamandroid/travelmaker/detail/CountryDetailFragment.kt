package com.teamandroid.travelmaker.detail

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.*
import com.teamandroid.travelmaker.R
import com.teamandroid.travelmaker.main.Country
import com.teamandroid.travelmaker.main.MainActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_countrydetail.view.*

class CountryDetailFragment : Fragment(), View.OnClickListener {
    lateinit var mview : View
    lateinit var country : Country

    companion object {
        fun newInstance(country : Country): CountryDetailFragment {
            val fragment = CountryDetailFragment()
            fragment.country = country
            return fragment
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mview = inflater.inflate(R.layout.fragment_countrydetail,container,false)
        (activity as DetailActivity).countryDetailDesign()
        mview.btn_moreApplication.setOnClickListener(this)
        mview.btn_moreExpert.setOnClickListener(this)
        mview.countryDetail_image.setImageBitmap(country.detailBitmap)


        return mview
    }

    override fun onClick(v: View?) {
        if(v == mview.btn_moreApplication){
            (activity as DetailActivity).changeFragment(MoreApplicationsFragment())
        }
        else if(v == mview.btn_moreExpert){
            (activity as DetailActivity).changeFragment(MoreExpertsFragment())
        }
    }
}
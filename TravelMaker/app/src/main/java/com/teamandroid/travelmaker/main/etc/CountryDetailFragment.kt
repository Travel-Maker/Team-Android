package com.teamandroid.travelmaker.main.etc

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.teamandroid.travelmaker.R
import com.teamandroid.travelmaker.main.MainActivity
import kotlinx.android.synthetic.main.fragment_countrydetail.view.*

class CountryDetailFragment : Fragment(), View.OnClickListener {
    lateinit var mview : View
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mview = inflater.inflate(R.layout.fragment_countrydetail,container,false)

        mview.btn_moreApplication.setOnClickListener(this)
        mview.btn_moreExpert.setOnClickListener(this)

        return mview
    }

    override fun onClick(v: View?) {
        if(v == mview.btn_moreApplication){
            (activity as MainActivity).changeEtcFragment(MoreApplicationsFragment())
        }
        else if(v == mview.btn_moreExpert){
            (activity as MainActivity).changeEtcFragment(MoreExpertsFragment())
        }
    }
}
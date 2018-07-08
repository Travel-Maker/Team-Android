package com.teamandroid.travelmaker.detail

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.teamandroid.travelmaker.R
import com.teamandroid.travelmaker.etc.FilterDialogFragment
import kotlinx.android.synthetic.main.fragment_apply.view.*

class MoreApplicationsFragment : Fragment() , View.OnClickListener{

    override fun onClick(v: View?) {
        if(mView.btn_filter == v){
            val filterDialogFragment = FilterDialogFragment()
            filterDialogFragment.show(activity!!.supportFragmentManager,null)
        }
    }

    lateinit var mView : View
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mView = inflater.inflate(R.layout.fragment_apply,container,false)
        (activity as DetailActivity).otherDesign()
        mView.btn_filter.setOnClickListener(this)
        return mView
    }
}
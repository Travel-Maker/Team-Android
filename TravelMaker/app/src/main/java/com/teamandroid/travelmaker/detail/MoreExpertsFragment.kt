package com.teamandroid.travelmaker.detail

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.teamandroid.travelmaker.Application
import com.teamandroid.travelmaker.Expert
import com.teamandroid.travelmaker.R
import com.teamandroid.travelmaker.etc.FilterDialogFragment
import com.teamandroid.travelmaker.main.Country
import kotlinx.android.synthetic.main.expert_person_item.view.*
import kotlinx.android.synthetic.main.fragment_apply.view.*
import kotlinx.android.synthetic.main.fragment_expert.view.*
import java.util.ArrayList

class MoreExpertsFragment : Fragment(),View.OnClickListener {
    lateinit var mView : View
    lateinit var experts : ArrayList<Expert>

    companion object {
        fun newInstance(experts: ArrayList<Expert>): MoreExpertsFragment {
            val fragment = MoreExpertsFragment()
            fragment.experts = experts
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mView = inflater.inflate(R.layout.fragment_expert,container,false)
        (activity as DetailActivity).otherDesign()
        mView.expert_filter.setOnClickListener(this)
        mView.expert_recycler.adapter = MoreExpertsRecyclerAdapter(experts)
        mView.expert_recycler.layoutManager = LinearLayoutManager(activity!!.applicationContext)
        return mView
    }

    override fun onClick(v: View?) {
        if(mView.expert_filter == v){
            val filterDialogFragment = FilterDialogFragment()
            filterDialogFragment.show(activity!!.supportFragmentManager,null)
        }
    }
}
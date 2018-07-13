package com.teamandroid.travelmaker.travelplanread
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.teamandroid.travelmaker.R
import kotlinx.android.synthetic.main.schedule_tab.view.*

class GetScheduleTapFragment: Fragment() {
    private val mRecyclerView: RecyclerView? = null
    private var mAdapter: RecyclerView.Adapter<*>? = null
    private var mLayoutManager: RecyclerView.LayoutManager? = null
    lateinit var scheduleItems : ArrayList<GetScheduleItem>
    lateinit var activity_context : Context

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        activity_context = container!!.context
        val view = inflater.inflate(R.layout.get_schedule_tab, container, false)
        scheduleItems = ArrayList()
        scheduleItems.add(GetScheduleItem(1, "내용보기"))
        scheduleItems.add(GetScheduleItem(2, "내용보기"))
        scheduleItems.add(GetScheduleItem(3, "내용보기"))
        scheduleItems.add(GetScheduleItem(4, "내용보기"))
        scheduleItems.add(GetScheduleItem(5, "내용보기"))

        // Set the adapter

        if (view.rv_schedule is RecyclerView) {
            val mRecyclerView = view.rv_schedule as RecyclerView
            mRecyclerView.setHasFixedSize(true)

            // use a linear layout manager

            mLayoutManager = LinearLayoutManager(context)
            mRecyclerView.layoutManager = mLayoutManager

            // specify an adapter (see also next example)

            mAdapter = GetScheduleAdapter(scheduleItems, activity_context)
            mRecyclerView.adapter = mAdapter
        }
        return view
    }
}
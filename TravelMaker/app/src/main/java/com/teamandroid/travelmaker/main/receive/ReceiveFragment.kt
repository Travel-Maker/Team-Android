package com.teamandroid.travelmaker.main.receive

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.teamandroid.travelmaker.MainPage
import com.teamandroid.travelmaker.R
import com.teamandroid.travelmaker.RecyclerItemClickListener
import com.teamandroid.travelmaker.etc.DeleteDialogFragment
import com.teamandroid.travelmaker.main.MainActivity

import kotlinx.android.synthetic.main.fragment_receive.view.*

class ReceiveFragment: Fragment(),MainPage {
    lateinit var mView : View
    lateinit var items : ArrayList<ReceiveData>
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        mView = inflater.inflate(R.layout.fragment_receive,container,false)
        (activity as MainActivity).initActivityDesign()
        items = ArrayList<ReceiveData>()

        items.add(ReceiveData("A","장소 / 날짜 / 성향"))
        items.add(ReceiveData("B","장소 / 날짜 / 성향"))
        items.add(ReceiveData("C","장소 / 날짜 / 성향"))

        mView.receive_recycler.adapter = ReceiveRecyclerViewAdapter(items)
        mView.receive_recycler.layoutManager = LinearLayoutManager(container!!.context)
        mView.receive_recycler.addOnItemTouchListener(RecyclerItemClickListener(container.context, mView.receive_recycler,
                object : RecyclerItemClickListener.OnItemClickListener{
                    override fun onItemClick(view: View, position: Int) {}

                    override fun onLongItemClick(view: View, position: Int) {
                        val deleteDialog = DeleteDialogFragment()
                        deleteDialog.setOkButton(object : View.OnClickListener{
                            override fun onClick(v: View?) {
                                items.removeAt(position)
                                (mView.receive_recycler.adapter as ReceiveRecyclerViewAdapter).addItem(items)
                            }
                        })

                        deleteDialog.setcancelButton(object : View.OnClickListener{ override fun onClick(v: View?) {} })

                        deleteDialog.show(activity!!.supportFragmentManager,null)
                    }
                }))
        return mView
    }
}
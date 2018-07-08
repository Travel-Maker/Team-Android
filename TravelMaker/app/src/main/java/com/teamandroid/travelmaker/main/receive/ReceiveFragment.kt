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
import com.teamandroid.travelmaker.main.send.SendData
import com.teamandroid.travelmaker.main.send.SendFragment

import kotlinx.android.synthetic.main.fragment_receive.view.*

class ReceiveFragment: Fragment(){
    lateinit var mView : View
    lateinit var items : ArrayList<ReceiveData>

    companion object {
        fun newInstance(items : ArrayList<ReceiveData>): ReceiveFragment {
            val fragment = ReceiveFragment()
            fragment.items = items
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        mView = inflater.inflate(R.layout.fragment_receive,container,false)
        (activity as MainActivity).initActivityDesign()


        mView.receive_recycler.adapter = ReceiveRecyclerViewAdapter(items)
        mView.receive_recycler.layoutManager = LinearLayoutManager(container!!.context)
        mView.receive_recycler.addOnItemTouchListener(RecyclerItemClickListener(container.context, mView.receive_recycler,
                object : RecyclerItemClickListener.OnItemClickListener{
                    override fun onItemClick(view: View, position: Int) {}

                    override fun onLongItemClick(view: View, position: Int) {
                        val deleteDialog = DeleteDialogFragment()
                        deleteDialog.setOkButton(object : View.OnClickListener{
                            override fun onClick(v: View?) {
                                items = (activity as MainActivity).deleteReceiveData(position)
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
package com.teamandroid.travelmaker.main.send

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.teamandroid.travelmaker.R
import com.teamandroid.travelmaker.RecyclerItemClickListener
import com.teamandroid.travelmaker.etc.DeleteDialogFragment
import com.teamandroid.travelmaker.main.MainActivity
import kotlinx.android.synthetic.main.fragment_receive.view.*
import kotlinx.android.synthetic.main.fragment_send.view.*

class SendFragment : Fragment() {

    lateinit var mView : View
    lateinit var items : ArrayList<SendData>

    companion object {
        fun newInstance(items : ArrayList<SendData>): SendFragment {
            val fragment = SendFragment()
            fragment.items = items
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        mView = inflater.inflate(R.layout.fragment_send,container,false)
        (activity as MainActivity).initActivityDesign()

        mView.send_recycler.adapter = SendRecyclerViewAdapter(items)
        mView.send_recycler.layoutManager = LinearLayoutManager(container!!.context)
        mView.send_recycler.addOnItemTouchListener(RecyclerItemClickListener(container.context, mView.send_recycler,
                object : RecyclerItemClickListener.OnItemClickListener{
                    override fun onItemClick(view: View, position: Int) {}

                    override fun onLongItemClick(view: View, position: Int) {
                        val deleteDialog = DeleteDialogFragment()
                        deleteDialog.setOkButton(object : View.OnClickListener{
                            override fun onClick(v: View?) {
                                items = (activity as MainActivity).deleteSendData(position)
                                (mView.send_recycler.adapter as SendRecyclerViewAdapter).addItem(items)
                            }
                        })

                        deleteDialog.setcancelButton(object : View.OnClickListener{ override fun onClick(v: View?) {} })

                        deleteDialog.show(activity!!.supportFragmentManager,null)
                    }
                }))
        return mView
    }
}
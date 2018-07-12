package com.teamandroid.travelmaker.main.receive

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.teamandroid.travelmaker.accept.ApplyAccept
import com.teamandroid.travelmaker.R
import com.teamandroid.travelmaker.RecyclerItemClickListener
import com.teamandroid.travelmaker.TravelMakerApplication
import com.teamandroid.travelmaker.etc.DeleteDialogFragment
import com.teamandroid.travelmaker.main.MainActivity
import com.teamandroid.travelmaker.post.PostReceiveApplication

import kotlinx.android.synthetic.main.fragment_receive.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ReceiveFragment: Fragment(){
    lateinit var mView : View
    lateinit var items : ArrayList<ReceiveBoard>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        mView = inflater.inflate(R.layout.fragment_receive,container,false)
        (activity as MainActivity).initActivityDesign()
        requestReceiveApplication()

        return mView
    }

    fun requestReceiveApplication(){
        val getReceiveApplication = (activity!!.applicationContext as TravelMakerApplication).getApplicationNetworkService().getReceiveApplication(
                (activity!!.applicationContext as TravelMakerApplication).userToken
        )

        getReceiveApplication.enqueue(object : Callback<PostReceiveApplication>{
            override fun onFailure(call: Call<PostReceiveApplication>?, t: Throwable?) {

            }

            override fun onResponse(call: Call<PostReceiveApplication>?, response: Response<PostReceiveApplication>?) {
                if(response!!.isSuccessful){
                    Log.d("ok","ok")
                    items = response.body()!!.receive_board

                    mView.receive_recycler.adapter = ReceiveRecyclerViewAdapter(items)
                    mView.receive_recycler.layoutManager = LinearLayoutManager(activity!!.applicationContext)
                    mView.receive_recycler.addOnItemTouchListener(RecyclerItemClickListener(activity!!.applicationContext, mView.receive_recycler,
                            object : RecyclerItemClickListener.OnItemClickListener{
                                override fun onItemClick(view: View, position: Int) {
                                    val intent = Intent(activity!!.applicationContext, ApplyAccept::class.java)
                                    intent.putExtra("board_idx",items[position].board_data.board_idx)
                                    startActivityForResult(intent,200)
                                }

                                override fun onLongItemClick(view: View, position: Int) {
                                    val deleteDialog = DeleteDialogFragment()
                                    deleteDialog.setOkButton(object : View.OnClickListener{
                                        override fun onClick(v: View?) {

                                        }
                                    })

                                    deleteDialog.setcancelButton(object : View.OnClickListener{ override fun onClick(v: View?) {} })

                                    deleteDialog.show(activity!!.supportFragmentManager,null)
                                }
                            }))
                }
            }

        })
    }
}
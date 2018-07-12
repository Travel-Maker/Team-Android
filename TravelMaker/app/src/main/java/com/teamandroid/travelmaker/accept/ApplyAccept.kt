package com.teamandroid.travelmaker.accept

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.teamandroid.travelmaker.R
import com.teamandroid.travelmaker.TravelMakerApplication
import com.teamandroid.travelmaker.get.GetApplicationDetail
import com.teamandroid.travelmaker.review.CommentRecyclerAdapter
import com.teamandroid.travelmaker.review.PlanRecyclerAdapter
import kotlinx.android.synthetic.main.activity_apply_review.*
import kotlinx.android.synthetic.main.apply_item_inside.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApplyAccept : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_apply_accept)
        val board_idx = intent.getIntExtra("board_idx",-1)
        requestApplyData(board_idx)

    }


    fun requestApplyData(board_idx : Int){
        val getApplicationDetail = (applicationContext as TravelMakerApplication).getApplicationNetworkService().getdetailApplication(board_idx)

        getApplicationDetail.enqueue(object : Callback<GetApplicationDetail> {
            override fun onFailure(call: Call<GetApplicationDetail>?, t: Throwable?) {

            }

            override fun onResponse(call: Call<GetApplicationDetail>?, response: Response<GetApplicationDetail>?) {
                if(response!!.isSuccessful){
                    val board = response.body()!!.board

                    apply_title.setText("["+board[0].board_city +"] "+board[0].board_title)
                    apply_id.text = response.body()!!.sender[0].user_nick
                    apply_time.text = board[0].board_writetime

                    apply_during.text = board[0].board_arr_time + " ~ " + board[0].board_dep_time
                    apply_day.text = board[0].board_days.toString() + "일"
                    apply_coin.text = board[0].board_coin.toString() + "코인"
                    apply_etc.text = board[0].board_content

                    apply_item_recycler.adapter = PlanRecyclerAdapter(response.body()!!.plan)
                    apply_item_recycler.layoutManager = LinearLayoutManager(applicationContext)

                }
            }

        })
    }
}

package com.teamandroid.travelmaker.login

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.teamandroid.travelmaker.R
import kotlinx.android.synthetic.main.fragment_nickname.*

class MakeNickNameFragment : Fragment(), View.OnClickListener {

    var currentTendency : TextView? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_nickname,container, false)

        button1.tag = "먹고죽자형"
        button2.tag = "콜럼버스형"
        button3.tag = "인생샷형"
        button4.tag = "느긋한 휴양자형"
        button5.tag = "액티비티형"
        button6.tag = "자린고비형"
        button7.tag = "쇼핑형"

        return view
    }

    override fun onClick(v: View?) {
        if(v == signup){

        }else if(v == nickname_check){

        }else{
            if(currentTendency != null){
                currentTendency!!.isSelected = false
            }

            when(v){
                button1 -> currentTendency = button1
                button2 -> currentTendency = button2
                button3 -> currentTendency = button3
                button4 -> currentTendency = button4
                button5 -> currentTendency = button5
                button6 -> currentTendency = button6
                button7 -> currentTendency = button7
            }

            currentTendency!!.isSelected = true
        }
    }
}
package com.teamandroid.travelmaker.detail

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.*
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import com.teamandroid.travelmaker.Application
import com.teamandroid.travelmaker.Expert
import com.teamandroid.travelmaker.R
import com.teamandroid.travelmaker.RecyclerItemClickListener
import com.teamandroid.travelmaker.main.Country
import com.teamandroid.travelmaker.review.ApplyReview
import kotlinx.android.synthetic.main.apply_layout.view.*
import kotlinx.android.synthetic.main.fragment_countrydetail.*
import kotlinx.android.synthetic.main.fragment_countrydetail.view.*
import java.util.*

class CountryDetailFragment : Fragment(), View.OnClickListener {
    lateinit var mview : View
    lateinit var country : Country
    lateinit var applications : ArrayList<Application>
    lateinit var experts : ArrayList<Expert>
    var expertCount = 0

    companion object {
        fun newInstance(country : Country, experts: ArrayList<Expert>, applications : ArrayList<Application>): CountryDetailFragment {
            val fragment = CountryDetailFragment()
            fragment.country = country

            fragment.experts = experts
            fragment.applications = applications
            return fragment
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mview = inflater.inflate(R.layout.fragment_countrydetail,container,false)
        (activity as DetailActivity).countryDetailDesign()
        mview.btn_moreApplication.setOnClickListener(this)
        mview.btn_moreExpert.setOnClickListener(this)
        mview.country_favorite.setOnClickListener(this)
        mview.countryDetail_image.setImageBitmap(country.detailBitmap)


        if(country.countryData.country_idx != 8){
            mview.china_box.visibility = View.INVISIBLE
            mview.country_box.visibility = View.VISIBLE

            var img = 0
            when(country.countryData.country_idx){
                10 -> img = R.drawable.korea
                12 -> img = R.drawable.japan
                13 -> img = R.drawable.uk
                14 -> img = R.drawable.france
                15 -> img = R.drawable.spain
                16 -> img = R.drawable.canada
                17 -> img = R.drawable.america
                18 -> img = R.drawable.mexico
            }
            mview.country_box.setBackgroundResource(img)
        }
        else {
            mview.china_box.visibility = View.VISIBLE
            mview.country_box.visibility = View.INVISIBLE

            mview.weather.text = country.countryData.country_climate.toString() + "℃"
            mview.money.text = "약"+country.countryData.country_exchange.toString()+"위안"
            mview.time.text = "서울-"+country.countryData.country_time_difference+"시간"
            mview.language.text = country.countryData.country_language

            settingExpert(mview.expert_image1, mview.expert_grade1, mview.expert_nickName1, mview.expert_tendency1, mview.expert_city1
                    , mview.expert_ratingBar1, mview.expert_ratingValue1)

            settingExpert(mview.expert_image2, mview.expert_grade2, mview.expert_nickName2, mview.expert_tendency2, mview.expert_city2
                    , mview.expert_ratingBar2, mview.expert_ratingValue2)

            settingExpert(mview.expert_image3, mview.expert_grade3, mview.expert_nickName3, mview.expert_tendency3, mview.expert_city3
                    , mview.expert_ratingBar3, mview.expert_ratingValue3)

            val _applications = ArrayList<Application>()

            if (applications.size < 5) {
                for (i in 0..(applications.size - 1)) {
                    _applications.add(applications[i])
                }
            } else {
                for (i in 0..4) {
                    _applications.add(applications[i])
                }
            }

            mview.application_recycler.adapter = MoreApplicationsRecyclerAdapter(_applications)
            mview.application_recycler.layoutManager = LinearLayoutManager(activity!!.applicationContext)

            mview.application_recycler.addOnItemTouchListener(RecyclerItemClickListener(activity!!.applicationContext, mview.application_recycler,
                    object : RecyclerItemClickListener.OnItemClickListener{
                        override fun onItemClick(view: View, position: Int) {
                            val intent = Intent(activity!!.applicationContext, ApplyReview::class.java)
                            intent.putExtra("board_idx",applications[position].board_idx)
                            startActivity(intent)
                        }

                        override fun onLongItemClick(view: View, position: Int) {
                        }
                    }))

        }
        return mview
    }

    override fun onClick(v: View?) {
        if(v == mview.btn_moreApplication){
            (activity as DetailActivity).changeFragment(MoreApplicationsFragment.newInstance(applications))
        }
        else if(v == mview.btn_moreExpert){
            (activity as DetailActivity).changeFragment(MoreExpertsFragment.newInstance(experts))
        }
        else if(v == mview.country_favorite){
            mview.country_favorite.isSelected = !mview.country_favorite.isSelected
        }
    }

    private fun settingExpert(expert_image : ImageView, expert_grade : ImageView, expert_nickName : TextView, expert_tendency : TextView, expert_city : TextView,
                              expert_ratingBar : RatingBar, expert_ratingValue : TextView){

        when(expertCount){
            0 -> expert_image.setImageResource(R.drawable.china_expert_img)
            1 -> expert_image.setImageResource(R.drawable.expert_img_1)
            2 -> expert_image.setImageResource(R.drawable.expert_img_2)
        }


        var city = ""

        if(experts[expertCount].expert_city1 != null){
            val token = StringTokenizer(experts[expertCount].expert_city1, " ")
            var string = token.nextToken()
            Log.d("string",string)
            if(string.compareTo(country.countryData.country_name) == 0){
                if(token.hasMoreTokens()){
                    string = token.nextToken()
                    city = "" + string + " "
                }
            }
        }

        if(experts[expertCount].expert_city2 != null){
            val token = StringTokenizer(experts[expertCount].expert_city2, " ")
            var string = token.nextToken()

            if(string.compareTo(country.countryData.country_name) == 0){
                if(token.hasMoreTokens()){
                    string = token.nextToken()
                    city = "" + string + " "
                }
            }
        }

        if(experts[expertCount].expert_city3 != null){
            val token = StringTokenizer(experts[expertCount].expert_city3, " ")
            var string = token.nextToken()

            if(string.compareTo(country.countryData.country_name) == 0){
                if(token.hasMoreTokens()){
                    string = token.nextToken()
                    city = "" + string + " "
                }
            }
        }

        expert_city.text = city
        var img = 0
        when(experts[expertCount].expert_grade){
            0 -> img = R.drawable.blue_crown_big
            1 -> img = R.drawable.emerald_crown
            2 -> img = R.drawable.gold_crown_big
        }

        expert_grade.setImageResource(img)

        expert_nickName.text = experts[expertCount].user_nick

        var style = ""
        when(experts[expertCount].user_style){
            0 -> style = "콜럼버스형"
            1 -> style = "인생샷형"
            2 -> style = "액티비티형"
            3 -> style = "먹고죽자형"
            4 -> style = "느긋한 휴양자형"
            5 -> style = "자린고비형"
            6 -> style = "쇼핑형"
        }

        expert_tendency.text = style

            expert_ratingBar.rating = (experts[expertCount].expert_rate)!!.toFloat()
            expert_ratingValue.text = "("+experts[expertCount].expert_rate.toString()+")"
        expertCount++
    }
}
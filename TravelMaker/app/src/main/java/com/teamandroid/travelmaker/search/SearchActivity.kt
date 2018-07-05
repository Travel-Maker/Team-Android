package com.teamandroid.travelmaker.search

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.ActionBar
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.view.MenuItem
import com.teamandroid.travelmaker.R
import kotlinx.android.synthetic.main.activity_search.*
import kotlin.collections.ArrayList

class SearchActivity : AppCompatActivity(){


    lateinit var actionBar : ActionBar
    val countryWords = arrayOf("중국", "일본", "한국", "미국","캐나다","멕시코","영국","프랑스","스페인")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        setSupportActionBar(search_toolbar)


        actionBar = supportActionBar!!
        actionBar.title = null

        actionBar.setDisplayHomeAsUpEnabled(true)

        search_list.adapter = SearchRecyclerViewAdapter()
        search_list.layoutManager = LinearLayoutManager(this)

        search_editText.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                val filteredList = ArrayList<String>()

                for(item in countryWords){
                    if(item.toLowerCase().contains(s.toString().toLowerCase())){
                        filteredList.add(item)
                    }
                }

                (search_list.adapter as SearchRecyclerViewAdapter).addItem(filteredList)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        if(item!!.itemId == android.R.id.home){
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

}

package com.teamandroid.travelmaker.search

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.ActionBar
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import com.teamandroid.travelmaker.R
import com.teamandroid.travelmaker.RecyclerItemClickListener
import com.teamandroid.travelmaker.etc.DeleteDialogFragment
import com.teamandroid.travelmaker.main.CountryThumbnail
import com.teamandroid.travelmaker.main.receive.ReceiveRecyclerViewAdapter
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.fragment_receive.view.*
import kotlin.collections.ArrayList

class SearchActivity : AppCompatActivity(){


    lateinit var actionBar : ActionBar
    lateinit var searchData : ArrayList<SearchData>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        setSupportActionBar(search_toolbar)

        searchData = intent.getParcelableArrayListExtra("searchData")

        actionBar = supportActionBar!!
        actionBar.title = null

        search_btn_back.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                onBackPressed()
            }
        })

        search_list.adapter = SearchRecyclerViewAdapter()
        search_list.layoutManager = LinearLayoutManager(this)

        search_list.addOnItemTouchListener(RecyclerItemClickListener(applicationContext, search_list,
                object : RecyclerItemClickListener.OnItemClickListener{
                    override fun onItemClick(view: View, position: Int) {
                        changeCountryDetailFragment(view.tag as Int)
                        finish()
                    }

                    override fun onLongItemClick(view: View, position: Int) {
                        }}))

        search_editText.setOnEditorActionListener(object : TextView.OnEditorActionListener{
            override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
                if(actionId == EditorInfo.IME_ACTION_SEARCH){
                    val countryName = search_editText.text.toString()
                    for(i in 0..searchData.size){
                        if(countryName.compareTo(searchData[i].name) == 0) {
                            changeCountryDetailFragment(searchData[i].index)
                            return true
                        }
                    }
                }
                return false
            }

        })
        search_editText.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                val filteredList = ArrayList<SearchData>()
                if(s != null && s.isNotEmpty()){
                    for(item in searchData){
                        if(item.name.toLowerCase().contains(s.toString().toLowerCase())){
                            filteredList.add(item)
                        }
                    }
                }
                (search_list.adapter as SearchRecyclerViewAdapter).addItem(filteredList)
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

        })
    }


    fun changeCountryDetailFragment(index : Int){
        val intent = Intent()
        intent.putExtra("index",index)
        setResult(100,intent)
        finish()
    }

}

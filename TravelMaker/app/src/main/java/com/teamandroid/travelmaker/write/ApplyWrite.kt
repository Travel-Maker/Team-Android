package com.teamandroid.travelmaker.write

import android.app.DatePickerDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.teamandroid.travelmaker.R
import kotlinx.android.synthetic.main.activity_apply_write.*
import java.util.*
import kotlin.collections.ArrayList

class ApplyWrite : AppCompatActivity(), View.OnClickListener {

    lateinit var items : ArrayList<Empty>
    lateinit var adapter : ApplyWriteRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_apply_write)

        items = ArrayList()
        items.add(Empty())

        adapter = ApplyWriteRecyclerViewAdapter(items)

        apply_rv.adapter = adapter
    }


    override fun onClick(v: View?) {

        if(v == btnDateFragment){
            val calendar = Calendar.getInstance()

            DatePickerDialog(this, {
                view1, year, month, dayOfMonth -> btnDateFragment.setText(
                    String.format("%d", year) + "-" + String.format("%02d", month + 1) + "-" + String.format("%02d", dayOfMonth))
            }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE)).show()
        }
        else if(v == plus_btn){
            items.add(Empty())
            adapter.addItem(items)
        }
    }
}

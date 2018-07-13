package com.teamandroid.travelmaker.travelplanread

data class GetTrans (
        var trans_idx : Int,
        var trans_name : Int,
        var trans_budget : Int,
        var trans_day : Int,
        var trans_dep_time : String,
        var trans_arr_time : String,
        var trans_content : String?,
        var board_idx : Int
)
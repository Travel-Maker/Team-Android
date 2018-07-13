package com.teamandroid.travelmaker.travelplanread

data class GetPlace (
        var place_idx : Int,
        var place_day : Int,
        var place_count : Int,
        var place_name : String,
        var place_latitude : Double,
        var place_longitude : Double,
        var place_budget : Int,
        var place_budget_comment : String,
        var place_img : String?,
        var board_idx : Int
)
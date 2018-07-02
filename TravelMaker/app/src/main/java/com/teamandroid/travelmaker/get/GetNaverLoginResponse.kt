package com.teamandroid.travelmaker.get

import com.teamandroid.travelmaker.UserProfile

data class GetNaverLoginResponse (
        var resultcode : String,
        var message : String,
        var response : UserProfile
)
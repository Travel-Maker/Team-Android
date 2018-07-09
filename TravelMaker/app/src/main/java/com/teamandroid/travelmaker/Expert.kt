package com.teamandroid.travelmaker

import android.graphics.Bitmap
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView

data class Expert (
        val name : String,
        val tendency : String,
        val city : String,
        val ratingValue : Float,
        val profile : Bitmap,
        val crown : Bitmap
)
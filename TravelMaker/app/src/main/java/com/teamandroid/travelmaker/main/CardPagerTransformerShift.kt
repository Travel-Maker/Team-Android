package com.teamandroid.travelmaker.main

import android.support.v4.view.ViewPager
import android.view.View
import android.widget.RelativeLayout
import kotlin.math.abs

class CardPagerTransformerShift(var baseElevation : Float, var raisingElevation : Float, var smallerScale : Float, var startOffset : Float) : ViewPager.PageTransformer {

    override fun transformPage(page: View, position: Float) {
        var absPosition = Math.abs(position - startOffset)

        if(absPosition >= 1){
            page.elevation = baseElevation
            page.scaleY = smallerScale
            page.scaleX = smallerScale
        }
        else{
            page.elevation = (1-absPosition) * raisingElevation + baseElevation
            page.scaleY = (smallerScale - 1) * absPosition + 1
            page.scaleX = (smallerScale - 1) * absPosition + 1
        }
    }

}
package com.teamandroid.travelmaker.main

import android.graphics.Bitmap
import android.os.Parcel
import android.os.Parcelable

class Country(var countryData : CountryData, var thumbnailbitmap : Bitmap?, var detailBitmap : Bitmap?) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readParcelable(CountryData::class.java.classLoader),
            parcel.readParcelable(Bitmap::class.java.classLoader),
            parcel.readParcelable(Bitmap::class.java.classLoader)) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(countryData, flags)
        parcel.writeParcelable(thumbnailbitmap, flags)
        parcel.writeParcelable(detailBitmap, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Country> {
        override fun createFromParcel(parcel: Parcel): Country {
            return Country(parcel)
        }

        override fun newArray(size: Int): Array<Country?> {
            return arrayOfNulls(size)
        }
    }
}
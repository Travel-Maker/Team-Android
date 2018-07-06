package com.teamandroid.travelmaker.main

import android.graphics.Bitmap
import android.os.Parcel
import android.os.Parcelable

class CountryThumbnail(var index : Int, var name : String, var bitmap : Bitmap) : Parcelable{
    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString(),
            parcel.readParcelable(Bitmap::class.java.classLoader)) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(index)
        parcel.writeString(name)
        parcel.writeParcelable(bitmap, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CountryThumbnail> {
        override fun createFromParcel(parcel: Parcel): CountryThumbnail {
            return CountryThumbnail(parcel)
        }

        override fun newArray(size: Int): Array<CountryThumbnail?> {
            return arrayOfNulls(size)
        }
    }
}
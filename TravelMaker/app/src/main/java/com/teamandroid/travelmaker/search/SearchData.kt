package com.teamandroid.travelmaker.search

import android.os.Parcel
import android.os.Parcelable

class SearchData(var index : Int, var name : String) : Parcelable{
    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(index)
        parcel.writeString(name)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SearchData> {
        override fun createFromParcel(parcel: Parcel): SearchData {
            return SearchData(parcel)
        }

        override fun newArray(size: Int): Array<SearchData?> {
            return arrayOfNulls(size)
        }
    }
}
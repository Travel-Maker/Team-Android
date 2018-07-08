package com.teamandroid.travelmaker.main

import android.os.Parcel
import android.os.Parcelable

class CountryData (var index : Int, var name : String, var favorite : Boolean) : Parcelable{
    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString(),
            parcel.readByte() != 0.toByte()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(index)
        parcel.writeString(name)
        parcel.writeByte(if (favorite) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CountryData> {
        override fun createFromParcel(parcel: Parcel): CountryData {
            return CountryData(parcel)
        }

        override fun newArray(size: Int): Array<CountryData?> {
            return arrayOfNulls(size)
        }
    }
}
package com.teamandroid.travelmaker.main.receive

import android.os.Parcel
import android.os.Parcelable

class ReceiveData(var from : String, var contents : String ) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(from)
        parcel.writeString(contents)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ReceiveData> {
        override fun createFromParcel(parcel: Parcel): ReceiveData {
            return ReceiveData(parcel)
        }

        override fun newArray(size: Int): Array<ReceiveData?> {
            return arrayOfNulls(size)
        }
    }
}
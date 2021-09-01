package com.hardik.ekakshya.entity.course

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Enroll(
    val _id : String? = null,
    val course : Course? = null,
    val studentID : String? = null
) : Parcelable {
    @PrimaryKey(autoGenerate = true)
    val enrollmentID : Int = 0

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readParcelable(Course::class.java.classLoader),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(_id)
        parcel.writeParcelable(course, flags)
        parcel.writeString(studentID)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Enroll> {
        override fun createFromParcel(parcel: Parcel): Enroll {
            return Enroll(parcel)
        }

        override fun newArray(size: Int): Array<Enroll?> {
            return arrayOfNulls(size)
        }
    }


}
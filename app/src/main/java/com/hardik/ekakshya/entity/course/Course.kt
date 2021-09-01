package com.hardik.ekakshya.entity.course

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Course (
    val _id : String? = null,
    var courseTitle: String? = null,
    var courseDesc: String? = null,
    var courseThumbnail: String? = null,
    var courseVideo:String? = null,
    var coursePrice:Float? = null,
    var teacherID:String? = null
) : Parcelable {

    @PrimaryKey(autoGenerate = true)
    var courseID : Int = 0

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Float::class.java.classLoader) as? Float,
        parcel.readString()
    ) {
        courseID = parcel.readInt()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(_id)
        parcel.writeString(courseTitle)
        parcel.writeString(courseDesc)
        parcel.writeString(courseThumbnail)
        parcel.writeString(courseVideo)
        parcel.writeValue(coursePrice)
        parcel.writeString(teacherID)
        parcel.writeInt(courseID)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Course> {
        override fun createFromParcel(parcel: Parcel): Course {
            return Course(parcel)
        }

        override fun newArray(size: Int): Array<Course?> {
            return arrayOfNulls(size)
        }
    }


}


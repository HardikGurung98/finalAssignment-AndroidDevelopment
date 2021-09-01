package com.hardik.ekakshya.entity.auth.student

import androidx.room.Entity

@Entity
data class Student (
        var _id : String? = null,
        var username: String? = null,
        var email: String? = null,
        var password: String? = null,
        var firstName:String? = null,
        var lastName:String? = null,
        var bioGraph:String? = null,
        var profilePicture: String?=null

)
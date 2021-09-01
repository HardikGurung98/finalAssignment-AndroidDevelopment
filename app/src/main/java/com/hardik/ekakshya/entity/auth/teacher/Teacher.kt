package com.hardik.ekakshya.entity.auth.teacher

import androidx.room.Entity

@Entity
data class Teacher (
    var username: String? = null,
    var email: String? = null,
    var password: String? = null,
    var firstName:String? = null,
    var lastName:String? = null,
    var bioGraph:String? = null,
    var website:String? = null,
    var linkIn:String? = null,
    var youtube:String? = null,
    var profilePicture:String? = null

)
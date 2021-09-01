package com.hardik.ekakshya.response

import com.hardik.ekakshya.entity.auth.student.Student

class LoginResponse (
    val success: Boolean? = null,
    val token: String? = null,
    val message:String?=null,
    val studentData : MutableList<Student>? = null
)
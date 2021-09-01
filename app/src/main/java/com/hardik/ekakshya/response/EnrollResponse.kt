package com.hardik.ekakshya.response

import com.hardik.ekakshya.entity.course.Enroll

data class EnrollResponse (
    val token : String? = null,
    val success : Boolean? =null,
    val message : String? = null,
    val data : MutableList<Enroll>? = null
)
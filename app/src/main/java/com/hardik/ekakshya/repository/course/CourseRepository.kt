package com.hardik.ekakshya.repository.course

import com.hardik.ekakshya.api.MyAPIRequest1
import com.hardik.ekakshya.api.ServiceBuilder
import com.hardik.ekakshya.api.course.student.CourseAPI
import com.hardik.ekakshya.response.CourseResponse


class CourseRepository : MyAPIRequest1() {
    val myAPI = ServiceBuilder.buildService(CourseAPI::class.java)

    suspend fun getAllCourse():CourseResponse
    {
        return apiRequest {
            myAPI.getAllCourse()
        }
    }
}

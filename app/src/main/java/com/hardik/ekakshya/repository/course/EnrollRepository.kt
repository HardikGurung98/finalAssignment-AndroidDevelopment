package com.hardik.ekakshya.repository.course

import com.hardik.ekakshya.api.MyAPIRequest1
import com.hardik.ekakshya.api.ServiceBuilder
import com.hardik.ekakshya.api.course.student.CourseAPI
import com.hardik.ekakshya.entity.course.Enroll
import com.hardik.ekakshya.response.EnrollResponse

class EnrollRepository : MyAPIRequest1() {
    val myApi = ServiceBuilder.buildService(CourseAPI::class.java)

    suspend fun enrollCourse(id : String, enroll: Enroll) : EnrollResponse{
        return apiRequest {
            myApi.enrollCourse(ServiceBuilder.token!!, id, enroll)
        }
    }

    suspend fun getMyEnrolls() : EnrollResponse{
        return apiRequest {
            myApi.getMyAccounts(ServiceBuilder.token!!)
        }
    }

    suspend fun deleteEnroll(id : String) : EnrollResponse{
        return apiRequest {
            myApi.deleteMyEnroll(ServiceBuilder.token!!, id)
        }
    }
}
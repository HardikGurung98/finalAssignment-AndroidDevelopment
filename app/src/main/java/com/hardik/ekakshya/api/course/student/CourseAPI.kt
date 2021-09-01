package com.hardik.ekakshya.api.course.student

import com.hardik.ekakshya.entity.course.Enroll
import com.hardik.ekakshya.response.CourseResponse
import com.hardik.ekakshya.response.EnrollResponse
import retrofit2.Response
import retrofit2.http.*

interface CourseAPI {

    @GET("all-courses")
    suspend fun getAllCourse(): Response<CourseResponse>

    @POST("enroll/course/{id}")
    suspend fun enrollCourse(
        @Header("Authorization") id : String,
        @Path("id") courseID : String,
        @Body enroll: Enroll
    ) : Response<EnrollResponse>


    @GET("get/enrolled/courses")
    suspend fun getMyAccounts(
        @Header("Authorization") id : String
    ) : Response<EnrollResponse>

    @DELETE("delete/my-enroll/{id}")
    suspend fun deleteMyEnroll(
        @Header("Authorization") token : String,
        @Path("id") id : String
    ) : Response<EnrollResponse>
}
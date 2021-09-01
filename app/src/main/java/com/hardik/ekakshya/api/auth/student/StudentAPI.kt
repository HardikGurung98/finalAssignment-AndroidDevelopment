package com.hardik.ekakshya.api.auth.student

import com.hardik.ekakshya.entity.auth.student.Student
import com.hardik.ekakshya.response.LoginResponse

import retrofit2.Response
import retrofit2.http.*

interface StudentAPI {
    @POST("account/register/student")
    suspend fun registerStudent(
            @Body student : Student
    ): Response<LoginResponse>


    @FormUrlEncoded
    @POST("account/login/student")
    suspend fun checkStudent(
            @Field("username") username: String,
            @Field("password") password: String
    ):Response<LoginResponse>






}
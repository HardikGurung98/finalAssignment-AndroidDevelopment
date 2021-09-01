package com.hardik.ekakshya.api.auth.teacher

import com.hardik.ekakshya.entity.auth.teacher.Teacher
import com.hardik.ekakshya.response.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface TeacherAPI {

    @POST("account/register/teacher")
    suspend fun registerTeacher(
        @Body teacher : Teacher
    ):Response<LoginResponse>


    @FormUrlEncoded
    @POST("account/login/teacher")
    suspend fun checkTeacher(
        @Field("username") username: String,
        @Field("password") password: String
    ):Response<LoginResponse>
}
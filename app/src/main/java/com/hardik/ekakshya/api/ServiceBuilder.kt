package com.hardik.ekakshya.api

import com.hardik.ekakshya.entity.auth.student.Student
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceBuilder {

//    private const val BASE_URL = "http://localhost:1337/"
    private const val BASE_URL = "http://10.0.2.2:1337/"
    var token: String? = null
    var data : MutableList<Student> ? = null

    private val okHttp = OkHttpClient.Builder()
    private val retrofitBuilder = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(
        GsonConverterFactory.create()).client(okHttp.build())

    //Create retrofit instance
    private val retrofit = retrofitBuilder.build()

    //Generic function
    fun <T> buildService(serviceType: Class<T>): T {
        return retrofit.create(serviceType)
    }

    fun loadImage() : String{
        return BASE_URL
    }
}
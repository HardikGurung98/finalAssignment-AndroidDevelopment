package com.hardik.ekakshya.repository.auth.student


import com.hardik.ekakshya.api.MyAPIRequest1
import com.hardik.ekakshya.api.ServiceBuilder
import com.hardik.ekakshya.api.auth.student.StudentAPI
import com.hardik.ekakshya.entity.auth.student.Student
import com.hardik.ekakshya.response.LoginResponse


class StudentRepository : MyAPIRequest1() {
    val myAPI = ServiceBuilder.buildService(StudentAPI::class.java)

    suspend fun registerStudent(student : Student):LoginResponse
    {
        return apiRequest {
            myAPI.registerStudent(student)
        }
    }

    suspend fun checkStudent(username:String, password:String):LoginResponse{
        return apiRequest {
            myAPI.checkStudent(username, password)
        }
    }



}
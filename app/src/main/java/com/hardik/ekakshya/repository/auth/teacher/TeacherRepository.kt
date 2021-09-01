package com.hardik.ekakshya.repository.auth.teacher


import com.hardik.ekakshya.api.MyAPIRequest1
import com.hardik.ekakshya.api.ServiceBuilder
import com.hardik.ekakshya.api.auth.teacher.TeacherAPI
import com.hardik.ekakshya.entity.auth.teacher.Teacher
import com.hardik.ekakshya.response.LoginResponse

class TeacherRepository : MyAPIRequest1() {
    val myAPI = ServiceBuilder.buildService(TeacherAPI::class.java)

    suspend fun registerTeacher(teacher : Teacher): LoginResponse{
        return apiRequest {
            myAPI.registerTeacher(teacher)
        }
    }



    suspend fun checkTeacher(username: String, password: String): LoginResponse {
        return apiRequest {
            myAPI.checkTeacher(username, password)
        }
    }
}
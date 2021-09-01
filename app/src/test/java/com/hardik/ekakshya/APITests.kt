package com.hardik.ekakshya

import com.hardik.ekakshya.api.ServiceBuilder
import com.hardik.ekakshya.entity.auth.student.Student
import com.hardik.ekakshya.entity.course.Enroll
import com.hardik.ekakshya.repository.auth.student.StudentRepository
import com.hardik.ekakshya.repository.course.EnrollRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class APITests {

    @Test
    fun checkLogin() = runBlocking {
        val repository = StudentRepository()

        val response = repository.checkStudent("ajay", "1530ajay")

        val expectedResult = true

        val actualResult = response.success

        Assert.assertEquals(expectedResult, actualResult)
    }

    @Test
    fun checkRegister() = runBlocking {
        val repository = StudentRepository()

        val student = Student(username = "ajay123", email = "ajay12313213214@gmail.com",
        password = "password123", firstName = "Ajay", lastName = "Pudasaini")
        val response = repository.registerStudent(student)

        val expectedResult = true
        val actualResult = response.success

        Assert.assertEquals(expectedResult, actualResult)
    }

    @Test
    fun getMyEnrolls() = runBlocking {
        val repository = StudentRepository()
        val enrollRepository = EnrollRepository()

        ServiceBuilder.token = repository.checkStudent("ajay", "1530ajay").token

        ServiceBuilder.data?.get(0)?._id = "6077c48055d2ff28acf80f3e"

        val response = enrollRepository.getMyEnrolls()

        val expectedResult = true

        val actualResult = response.success

        Assert.assertEquals(expectedResult, actualResult)
    }

    @Test
    fun addEnrolls() = runBlocking {
        val repository = StudentRepository()
        val enrollRepository = EnrollRepository()

        ServiceBuilder.token = repository.checkStudent("ajay", "1530ajay").token

        val enroll = Enroll("6066e1cbd4e89e48aaff7f0c", )
    }


}
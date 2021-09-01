package com.hardik.ekakshya.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.hardik.ekakshya.entity.course.Course

@Dao
interface CourseDao {

    @Insert
    suspend fun uploadCourse(course : ArrayList<Course>)

    @Query("select * from Course")
    suspend fun getAllCourses() : MutableList<Course>

    @Query("Delete from Course")
    suspend fun deleteCourse()
}


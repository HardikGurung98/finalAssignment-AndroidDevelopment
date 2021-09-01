package com.hardik.ekakshya.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.hardik.ekakshya.dao.CourseDao
import com.hardik.ekakshya.entity.course.Course

@Database(
    entities = [(Course::class)],
    version = 1,
    exportSchema = false
)
abstract class CourseDB : RoomDatabase() {
    abstract  fun getCourseDao() : CourseDao

    companion object{
        private var instances : CourseDB? = null

        fun getInstance(context: Context) : CourseDB
        {
            if (instances == null){
                synchronized(Course::class){
                    instances = buildDatabase(context)
                }
            }
            return instances!!
        }

        private fun  buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext, CourseDB::class.java, "CourseList"
        ).addMigrations().build()
    }
}
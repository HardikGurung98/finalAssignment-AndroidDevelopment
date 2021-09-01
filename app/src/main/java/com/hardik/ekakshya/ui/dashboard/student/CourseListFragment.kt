package com.hardik.ekakshya.ui.dashboard.student

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hardik.ekakshya.R
import com.hardik.ekakshya.adapter.CourseAdapter
import com.hardik.ekakshya.database.CourseDB
import com.hardik.ekakshya.entity.course.Course
import com.hardik.ekakshya.repository.course.CourseRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class CourseListFragment : Fragment() {

    private lateinit var courseListView : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_course_list, container, false)

        courseListView = view.findViewById(R.id.courseListView)

        courseList()

        return view
    }


    private fun courseList(){
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val courseRepository = CourseRepository()
                val response = courseRepository.getAllCourse()

                if (response.success == true) {

                    val courselist: ArrayList<Course> = response.data!!


                    context?.let { CourseDB.getInstance(it).getCourseDao().deleteCourse() }

                    context?.let {
                        CourseDB.getInstance(it).getCourseDao().uploadCourse(courselist)
                    }

                    val courses =
                        context?.let { CourseDB.getInstance(it).getCourseDao().getAllCourses() }

                    withContext(Main) {
                        courseListView.adapter = context?.let { CourseAdapter(courses!!, it) }
                        val gridLayoutManager =
                            GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)

                        courseListView.layoutManager = gridLayoutManager
                    }

                }
            }
            catch (ex:Exception)
            {
                withContext(Main){
                    Toast.makeText(context, ex.localizedMessage, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


}
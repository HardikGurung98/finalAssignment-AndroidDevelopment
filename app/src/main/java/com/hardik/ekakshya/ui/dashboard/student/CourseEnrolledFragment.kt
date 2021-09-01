package com.hardik.ekakshya.ui.dashboard.student

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hardik.ekakshya.R
import com.hardik.ekakshya.adapter.EnrollAdapter
import com.hardik.ekakshya.entity.course.Enroll
import com.hardik.ekakshya.repository.course.EnrollRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception


class CourseEnrolledFragment : Fragment() {

    private lateinit var rvEnrolls : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_course_enrolled, container, false)

        rvEnrolls = view.findViewById(R.id.rvEnrolls)

        loadMyEnrolls()

        return view
    }

    private fun loadMyEnrolls(){
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val enrollRepository = EnrollRepository()


                val response = enrollRepository.getMyEnrolls()



                if(response.success == true){
                    var listEnrolls : MutableList<Enroll> = response.data!!

                    withContext(Main){
                        val adapter = EnrollAdapter(listEnrolls, context!!)

                        rvEnrolls.layoutManager = LinearLayoutManager(context)

                        rvEnrolls.adapter = adapter
                    }
                }
            }
            catch (ex : Exception){
                withContext(Main){
                    Toast.makeText(context, ex.toString(), Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

}
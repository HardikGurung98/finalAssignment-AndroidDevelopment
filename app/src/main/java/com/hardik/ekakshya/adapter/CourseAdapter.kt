package com.hardik.ekakshya.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hardik.ekakshya.R
import com.hardik.ekakshya.api.ServiceBuilder
import com.hardik.ekakshya.entity.course.Course
import com.hardik.ekakshya.ui.dashboard.student.CourseDetailsActivity
import com.bumptech.glide.Glide

class CourseAdapter(
    val courseList : MutableList<Course>,
    val context: Context
): RecyclerView.Adapter<CourseAdapter.CourseView>() {

    class CourseView(view: View): RecyclerView.ViewHolder(view)
    {
        val courseThumbnail : ImageView
        val tvCourseTitle : TextView
        val tvCoursePrice : TextView
        val btnDetails : Button

        init {
            courseThumbnail = view.findViewById(R.id.courseThumbnail)
            tvCourseTitle = view.findViewById(R.id.tvCourseTitle)
            tvCoursePrice = view.findViewById(R.id.tvCoursePrice)
            btnDetails = view.findViewById(R.id.btnDetails)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseView {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.courses, parent, false)
        return CourseView(view)

    }

    @SuppressLint("SetTextI8n")
    override fun onBindViewHolder(holder: CourseView, position: Int) {
        val courses = courseList[position]

        holder.tvCourseTitle.text = courses.courseTitle
        holder.tvCoursePrice.text = "Price ${courses.coursePrice.toString()}/-"

        val courseThumbnailPath = ServiceBuilder.loadImage() + courses.courseThumbnail

        Glide.with(context).load(courseThumbnailPath).into(holder.courseThumbnail)

        holder.btnDetails.setOnClickListener {
            val intent = Intent(context, CourseDetailsActivity::class.java)

            intent.putExtra("courses", courses)

            context.startActivity(intent)
        }


    }

    override fun getItemCount(): Int {
        return courseList.size
    }


}
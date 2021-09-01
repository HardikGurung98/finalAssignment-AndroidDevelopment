package com.hardik.ekakshya.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.hardik.ekakshya.R
import com.hardik.ekakshya.api.ServiceBuilder
import com.hardik.ekakshya.entity.course.Enroll
import com.hardik.ekakshya.repository.course.EnrollRepository
import com.bumptech.glide.Glide
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class EnrollAdapter (val enrolls : MutableList<Enroll>,
                     val context: Context) : RecyclerView.Adapter<EnrollAdapter.EnrollView>()
{
    class EnrollView(view : View) : RecyclerView.ViewHolder(view){
        val enrollImg : ImageView
        val courseTitle : TextView
        val coursePrice : TextView
        val btnDelete : Button

        init {
            enrollImg = view.findViewById(R.id.imgEnroll)
            courseTitle = view.findViewById(R.id.cTitle)
            coursePrice = view.findViewById(R.id.cPrice)
            btnDelete = view.findViewById(R.id.btnDelete)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EnrollView {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.enrolls, parent, false)

        return EnrollView(view)
    }

    override fun onBindViewHolder(holder: EnrollView, position: Int) {
        val enroll = enrolls[position]



        holder.courseTitle.text = enroll.course?.courseTitle
        holder.coursePrice.text = enroll.course?.coursePrice.toString()

        Glide.with(context).load(ServiceBuilder.loadImage()+enroll.course?.courseThumbnail)
            .into(holder.enrollImg)

        holder.btnDelete.setOnClickListener{
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val enrollRepository = EnrollRepository()

                    val response = enrollRepository.deleteEnroll(enroll._id!!)

                    if(response.success == true){
                        withContext(Main){
                            Toast.makeText(context, "Your enrollment has been deleted", Toast.LENGTH_SHORT).show()


                        }

                        withContext(Main){
                            enrolls.remove(enroll)
                            notifyDataSetChanged()
                        }
                    }
                }
                catch (ex:Exception){
                    withContext(Main){
                        Toast.makeText(context, ex.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return enrolls.size
    }
}
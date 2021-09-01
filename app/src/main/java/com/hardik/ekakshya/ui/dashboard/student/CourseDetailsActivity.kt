package com.hardik.ekakshya.ui.dashboard.student

import android.content.Intent
import android.graphics.Color
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.hardik.ekakshya.R
import com.hardik.ekakshya.api.ServiceBuilder
import com.hardik.ekakshya.entity.course.Course
import com.hardik.ekakshya.entity.course.Enroll
import com.hardik.ekakshya.entity.course.NotificationChannel
import com.hardik.ekakshya.repository.course.EnrollRepository
import com.bumptech.glide.Glide
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class CourseDetailsActivity : AppCompatActivity(), SensorEventListener {
    private lateinit var imgCourseThumbnail : ImageView
    private lateinit var tvDetailsCourseTitle : TextView
    private lateinit var tvDetailsCoursePrice : TextView
    private lateinit var tvDetailsCourseDesc : TextView
    private lateinit var btnEnrollNow : Button
    private lateinit var btnBack : Button
    private var sensor : Sensor? = null
    private lateinit var sensorManager: SensorManager

    var id : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_course_details)

        imgCourseThumbnail = findViewById(R.id.imgCourseThumbnail)
        tvDetailsCourseTitle = findViewById(R.id.tvDetailsCourseTitle)
        tvDetailsCoursePrice = findViewById(R.id.tvDetailsCoursePrice)
        tvDetailsCourseDesc = findViewById(R.id.tvDetailsCourseDesc)
        btnEnrollNow = findViewById(R.id.btnEnrollNow)
        btnBack = findViewById(R.id.btnBack)


        val intent = intent.getParcelableExtra<Course>("courses")


        id = intent!!._id.toString()
        var courseName = intent!!.courseTitle
        var courseThumbnail = intent!!.courseThumbnail
        var price = intent!!.coursePrice
        var description = intent!!.courseDesc

        tvDetailsCourseTitle.text = courseName

        tvDetailsCoursePrice.text = price.toString()
        tvDetailsCourseDesc.text = description.toString()

        Glide.with(this).load(ServiceBuilder.loadImage() + courseThumbnail).into(imgCourseThumbnail)

        Toast.makeText(this@CourseDetailsActivity, id, Toast.LENGTH_SHORT).show()


        btnBack.setOnClickListener {
            startActivity(Intent(this@CourseDetailsActivity, StudentDashboardActivity::class.java))
        }

        btnEnrollNow.setOnClickListener {
            enrollMyCourse()
        }

        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager


        if(!checkSensor()){
            return
        }

        else{
            sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)

            sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL)
        }

    }

    private fun enrollMyCourse(){

        var userID = ServiceBuilder.data!![0]._id
        var enroll = Enroll(userID)

        CoroutineScope(Dispatchers.IO).launch {
            try{
                var enrollRepository = EnrollRepository()

                val response = enrollRepository.enrollCourse(id, enroll)



                if(response.success == true){
                    withContext(Main){
                        createNotification()
                        Toast.makeText(this@CourseDetailsActivity, "Course enrolled", Toast.LENGTH_SHORT).show()

                    }
                }
            }

            catch (ex : Exception){
                withContext(Main)
                {
                    Toast.makeText(
                        this@CourseDetailsActivity,
                        ex.localizedMessage,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

    }

    override fun onSensorChanged(event: SensorEvent?) {
        val values = event!!.values[1]

        if(values < -3){
            enrollMyCourse()
            startActivity(Intent(this, StudentDashboardActivity::class.java))
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }

    private fun checkSensor() : Boolean{
        var flag = true
        if(sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY) == null){
            flag = false
        }

        return flag
    }

    private fun createNotification(){
        val notificationManager = NotificationManagerCompat.from(this)

        val notificationChannel = NotificationChannel(this)

        notificationChannel.createNotification()

        val notification = NotificationCompat.Builder(this, notificationChannel.CHANNEL1)
                .setSmallIcon(R.drawable.common_google_signin_btn_icon_dark)
                .setContentTitle("Enrollment")
                .setContentText("You have successfully enrolled the course")
                .setColor(Color.BLUE).build()

        notificationManager.notify(1, notification)
    }

}
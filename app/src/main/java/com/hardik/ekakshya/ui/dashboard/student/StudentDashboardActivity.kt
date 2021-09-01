package com.hardik.ekakshya.ui.dashboard.student

import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.hardik.ekakshya.R
import com.hardik.ekakshya.ui.auth.student.StudentLoginActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class StudentDashboardActivity : AppCompatActivity(), SensorEventListener {
    private lateinit var bottomNav : BottomNavigationView
    private var sensor : Sensor? = null
    private lateinit var sensorManager : SensorManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_home)

        bottomNav = findViewById(R.id.bottomNav)

        val homeFragment = CourseListFragment()
        val enrollFragment = CourseEnrolledFragment()
        val userFragment = UserProfileFragment()
        val aboutFragment = AboutElearnFragment()


        makeCurrentFragment(homeFragment)

        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager




        bottomNav.setOnNavigationItemSelectedListener {
            when(it.itemId)
            {
                R.id.miHome->makeCurrentFragment(homeFragment)
                R.id.miCart->makeCurrentFragment(enrollFragment)
                R.id.miUser->makeCurrentFragment(userFragment)
                R.id.miAbout->makeCurrentFragment(aboutFragment)

            }
            true
        }

        if(!checkSensor()){
            return
        }

        else{
            sensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY)

            sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL)
        }


    }

    private fun makeCurrentFragment(fragment:Fragment)=
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.linearContainer, fragment)
                commit()
            }

    private fun checkSensor() : Boolean{
        var flag = true
        if(sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY) == null){
            flag = false
        }

        return flag
    }

    override fun onSensorChanged(event: SensorEvent?) {
        val values = event!!.values[0]

        if(values < 3){
            logout()
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }

    private fun logout(){
        startActivity(Intent(this, StudentLoginActivity::class.java))

        val sharedPreferences =  getSharedPreferences("UsernamePasswordSave", Context.MODE_PRIVATE)

        val editor = sharedPreferences.edit()

        editor.clear()
        editor.apply()

    }
}
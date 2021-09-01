package com.hardik.ekakshya.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.hardik.ekakshya.R
import com.hardik.ekakshya.api.ServiceBuilder
import com.hardik.ekakshya.repository.auth.student.StudentRepository
import com.hardik.ekakshya.ui.auth.student.StudentLoginActivity
import com.hardik.ekakshya.ui.dashboard.student.StudentDashboardActivity
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.Main

class SplashActivity : AppCompatActivity() {
    private var username : String? = null
    private var password : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        CoroutineScope(Main).launch {
            delay(1000)
//            startActivity(
//                    Intent(this@SplashActivity, SelectUserActivity::class.java)
//            )
           getPreferences()
        //    verifyUser()

            startActivity(Intent(this@SplashActivity, StudentLoginActivity::class.java))
            finish()

        }
    }

    private fun verifyUser(){
            CoroutineScope(Dispatchers.IO).launch {
                if(username == null && password == null){
                    withContext(Main){
                        startActivity(Intent(this@SplashActivity, StudentLoginActivity::class.java))

                    }
                }
                else {
                    try {

                        val repository = StudentRepository()

                        val response = username?.let { password?.let { it1 -> repository.checkStudent(it, it1) } }

                        if (response != null) {
                            if (response.success == true) {
                                ServiceBuilder.token = "${response.token}"
                                ServiceBuilder.data = response.studentData!!

                                withContext(Main){
                                    startActivity(Intent(this@SplashActivity, StudentDashboardActivity::class.java))
                                }

                            }
                        }

                        else{
                            withContext(Main){
                                startActivity(Intent(this@SplashActivity, StudentLoginActivity::class.java))
                            }
                        }

                    }
                    catch (n : NullPointerException){
                        withContext(Main){
                            Toast.makeText(this@SplashActivity, n.toString(), Toast.LENGTH_SHORT).show()
                        }
                    }

                }
            }
        }


    private fun getPreferences(){
            val sharedPref = getSharedPreferences("UsernamePasswordSave", Context.MODE_PRIVATE)

            username = sharedPref!!.getString("username", "").toString()
            password = sharedPref!!.getString("password", "").toString()
        }


}
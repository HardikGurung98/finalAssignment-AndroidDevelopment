package com.hardik.ekakshya.ui.auth.student

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.hardik.ekakshya.R
import com.hardik.ekakshya.api.ServiceBuilder
import com.hardik.ekakshya.repository.auth.student.StudentRepository
import com.hardik.ekakshya.ui.dashboard.student.StudentDashboardActivity
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException

class StudentLoginActivity : AppCompatActivity() {



    private lateinit var etStudentUsername : TextInputEditText
    private lateinit var etStudentPassword : TextInputEditText
    private lateinit var btnStudentLogin : Button
    private lateinit var txtStudentCreateOne : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_login)


        etStudentUsername = findViewById(R.id.etStudentUsername)
        etStudentPassword = findViewById(R.id.etStudentPassword)
        btnStudentLogin = findViewById(R.id.btnStudentLogin)
        txtStudentCreateOne = findViewById(R.id.txtStudentCreateOne)



        txtStudentCreateOne.setOnClickListener {

            startActivity(Intent(this@StudentLoginActivity, StudentRegisterActivity::class.java))
        }

        btnStudentLogin.setOnClickListener {
            studentLogin()
        }


    }




    private fun studentLogin()
    {
        val username = etStudentUsername.text.toString()
        val password = etStudentPassword.text.toString()

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val studentRepository = StudentRepository()
                val response = studentRepository.checkStudent(username, password)
                if (response.success==true)
                {
                    saveStudentLoginData()
                    ServiceBuilder.token = "Bearer ${response.token}"
                    ServiceBuilder.data = response.studentData

                    startActivity(Intent(this@StudentLoginActivity, StudentDashboardActivity::class.java))


                    withContext(Dispatchers.Main){
                        Toast.makeText(this@StudentLoginActivity, "${response.studentData}", Toast.LENGTH_SHORT).show()

                    }
                    finish()



                }
                else
                {
                    withContext(Dispatchers.Main){
                        Toast.makeText(this@StudentLoginActivity, "Invalid Login data", Toast.LENGTH_SHORT).show()
                    }
                }
            }catch (ex: IOException){
                withContext(Dispatchers.Main)
                {
                    Toast.makeText(this@StudentLoginActivity, ex.toString(), Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


    private fun saveStudentLoginData() {
        val username = etStudentUsername.text.toString()
        val password = etStudentPassword.text.toString()
        val sharedPref = getSharedPreferences("UsernamePasswordSave", MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putString("username", username)
        editor.putString("password", password)
        editor.apply()
    }
}
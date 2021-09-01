package com.hardik.ekakshya.ui.auth.student

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.hardik.ekakshya.R
import com.hardik.ekakshya.entity.auth.student.Student
import com.hardik.ekakshya.repository.auth.student.StudentRepository
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.Main

class StudentRegisterActivity : AppCompatActivity() {
    private lateinit var etStudentSetUsername : TextInputEditText
    private lateinit var etStudentSetEmail : TextInputEditText
    private lateinit var etStudentSetPassword : TextInputEditText
    private lateinit var etStudentConfirmSetPassword : TextInputEditText
    private lateinit var btnStudentRegister : Button
    private lateinit var txtStudentLogin : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_register)

        etStudentSetUsername = findViewById(R.id.etStudentSetUsername)
        etStudentSetEmail = findViewById(R.id.etStudentSetEmail)
        etStudentSetPassword = findViewById(R.id.etStudentSetPassword)
        etStudentConfirmSetPassword = findViewById(R.id.etStudentConfirmSetPassword)
        btnStudentRegister = findViewById(R.id.btnStudentRegister)
        txtStudentLogin = findViewById(R.id.txtStudentLogin)

        txtStudentLogin.setOnClickListener {
            startActivity(Intent(this, StudentLoginActivity::class.java))
        }

        btnStudentRegister.setOnClickListener {
            val username = etStudentSetUsername.text.toString()
            val email = etStudentSetEmail.text.toString()
            val password = etStudentSetPassword.text.toString()
            val confirmPassword = etStudentConfirmSetPassword.text.toString()

            if (password!=confirmPassword)
            {
                etStudentConfirmSetPassword.error = "Password does not match"
                etStudentConfirmSetPassword.requestFocus()
                return@setOnClickListener
            }
            else
            {
                val student = Student(username=username, email = email, password = password)

                CoroutineScope(Dispatchers.IO).launch {
                    try {
                        val studentRepository =
                                StudentRepository()
                        val response = studentRepository.registerStudent(student)
                        if (response.success==true)
                        {
                            withContext(Dispatchers.Main){
                                Toast.makeText(this@StudentRegisterActivity, "Student Register Successfully", Toast.LENGTH_LONG).show()
                            }

                            startActivity(Intent(this@StudentRegisterActivity, StudentLoginActivity::class.java))
                        }
                    }
                    catch (ex:Exception)
                    {
                        withContext(Main){
                            Toast.makeText(this@StudentRegisterActivity, ex.toString(), Toast.LENGTH_SHORT).show()
                        }
                    }
                }

            }
        }

    }
}
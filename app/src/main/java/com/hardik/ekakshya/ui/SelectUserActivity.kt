package com.hardik.ekakshya.ui

import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.app.ActivityCompat
import com.hardik.ekakshya.R
import com.hardik.ekakshya.ui.auth.student.StudentLoginActivity


class SelectUserActivity : AppCompatActivity() {
    private val permissions = arrayOf(
            android.Manifest.permission.CAMERA,
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
            android.Manifest.permission.ACCESS_FINE_LOCATION
    )

    private lateinit var btnStudent:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        btnStudent = findViewById(R.id.btnStudent)

        checkRunTimePermission()


        btnStudent.setOnClickListener{
            val intent = Intent(this, StudentLoginActivity::class.java)
            startActivity(intent)
        }
    }

    private fun checkRunTimePermission() {
        if (!hasPermission()) {
            requestPermission()
        }
    }

    private fun hasPermission(): Boolean {
        var hasPermission = true
        for (permission in permissions) {
            if (ActivityCompat.checkSelfPermission(
                            this,
                            permission
                    ) != PackageManager.PERMISSION_GRANTED
            ) {
                hasPermission = false
                break
            }
        }
        return hasPermission
    }

    private fun requestPermission() {
        ActivityCompat.requestPermissions(this@SelectUserActivity, permissions, 1)
    }

}
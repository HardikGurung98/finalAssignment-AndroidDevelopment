package com.hardik.ekakshya.ui.dashboard.student

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.hardik.ekakshya.R
import com.hardik.ekakshya.ui.auth.student.StudentLoginActivity

class UserProfileFragment : Fragment() {

    private lateinit var btnStLogout:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_user_profile, container, false)
        btnStLogout = view.findViewById(R.id.btnStLogout)

        btnStLogout.setOnClickListener {
            stLogout()
        }


        return view
    }



    private fun stLogout(){
        startActivity(Intent(context, StudentLoginActivity::class.java))

        val preferences = requireContext().getSharedPreferences("UsernamePasswordSave", Context.MODE_PRIVATE)
        val edit = preferences.edit()
        edit.clear()
        edit.apply()

        (context as Activity).finish()




    }

}
package com.hardik.ekakshya.ui.dashboard.student

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.hardik.ekakshya.R

class AboutElearnFragment : Fragment() {

    private lateinit var btnLocation:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_about_elearn, container, false)
        btnLocation = view.findViewById(R.id.btnLocation)

        btnLocation.setOnClickListener {
         //   startActivity(Intent(this@AboutElearnFragment, LocationFragment::class.java))
            startActivity(Intent(context, GoogleMapsActivity::class.java))
        }

        return view
    }
}
package com.example.lect2devtools

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        forceCrash()
    }

    fun forceCrash() {
        Handler(Looper.getMainLooper()).postDelayed({
            throw NullPointerException("This is a crash")
        }, 250)
    }
}

package com.androidstudio.app_intro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnnext = findViewById<ImageButton>(R.id.btnnext)

        btnnext.setOnClickListener{
            val intent = Intent(this,Login::class.java)
            startActivity(intent)
        }
    }
}
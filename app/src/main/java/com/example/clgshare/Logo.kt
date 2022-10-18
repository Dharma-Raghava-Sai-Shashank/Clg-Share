package com.example.clgshare

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

class Logo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logo)
        startActivity(Intent(this@Logo.getApplicationContext(), MainActivity::class.java))
    }
}
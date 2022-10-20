package com.example.clgshare

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.InternalCoroutinesApi

class Logo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logo)
        startActivity(Intent(this@Logo.getApplicationContext(), Registration::class.java))
    }
}
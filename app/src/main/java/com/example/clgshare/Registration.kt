package com.example.clgshare

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager

class Registration : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Setting layout :
        setContentView(R.layout.activity_registration)

        val tablayout=findViewById<TabLayout>(R.id.TabLayout)
        val viewPager=findViewById<ViewPager>(R.id.ViewPager)
    }
}
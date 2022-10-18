package com.example.clgshare

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter


class RegistrationAdapter(fm: FragmentManager?, registration: Registration, tabCount: Int) : FragmentPagerAdapter(fm) {

    private var context: Context? = registration
    var totalTabs = tabCount



    override fun getItem(position: Int): Fragment? {
        return when (position) {
            0 -> {
                SigninFragment()
            }
            1 -> {
                SignupFragment()
            }
            else -> null
        }
    }

    override fun getCount(): Int {
        return totalTabs
    }
}
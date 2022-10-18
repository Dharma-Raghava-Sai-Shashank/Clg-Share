package com.example.clgshare

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.ActivityCompat
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    var a = 0
    var ProfilePic: ImageView? = null
    var FullImage:ImageView? = null
    var Name: String? = null
    var PersonName :TextView?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        replacefragment(HomeFragment())


        // Profile :
        PersonName = findViewById(R.id.PersonName)

//        val Database: AppDatabase = Room.databaseBuilder(
//            applicationContext,
//            AppDatabase::class.java, "USER DATA"
//        ).allowMainThreadQueries().build()
//        val houseDataDao: HouseDataDao = Database.houseDataDao()
//        val profileDataList: List<MyProfileData> = houseDataDao.getAllProfileData()
//        if (profileDataList.size != 0) {
//            Name = profileDataList[profileDataList.size - 1].Name
//            PersonName.setText(Name)
//        }


        // Tool Bar (Menu) :
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        toolbar.setTitleTextColor(resources.getColor(R.color.white))


        // Navigation View:
        var bottomNavigationView = findViewById(R.id.bottomNavigationView) as BottomNavigationView
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.getItemId()) {
                R.id.Home -> {
                    PersonName?.setText(Name)
                    replacefragment(HomeFragment())
                    a = 1
                }
                R.id.Chat -> {
                    PersonName?.setText("                       QUERY")
                    replacefragment(QueryFragment())
                    a = 2
                }
                R.id.Add -> {
                    PersonName?.setText("                      ADD POST")
                    replacefragment(PostFragment())
                    a = 3
                }
                R.id.Favorite -> {
                    PersonName?.setText("                    FAVOURITES")
                    replacefragment(LikeFragment())
                    a = 4
                }
                R.id.Profile -> {
                    PersonName?.setText("                       PROFILE")
                    replacefragment(ProfileFragment())
                    a = 5
                }
            }
            true
        }

    }

    // Menu :
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.myhouse) {
            a = 6
            PersonName?.setText("                     MY POST")
            replacefragment(MyPostFragment())
        } else if (item.itemId == R.id.Chats) {
            a = 7
            val intent = Intent(this@MainActivity, Chats::class.java)
            startActivity(intent)
        } else {
            a = 9
//            FirebaseAuth.getInstance().signOut()
            startActivity(Intent(this@MainActivity.applicationContext, Registration::class.java))
        }
        return true
    }

    // Changing of Fragments by Selection :
    fun replacefragment(fragment: android.support.v4.app.Fragment) {
        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.framelayout, fragment)
        fragmentTransaction.commit()
    }

    // Profilepic Touch :
    fun profilepic(view: View?) {
        PersonName?.setText("                       PROFILE")
        replacefragment(ProfileFragment())
        a = 5
    }

    //Connect Touch :
    fun connect(view: View?) {
        PersonName?.setText("                   QUERRY")
        replacefragment(QueryFragment())
        a = 2
    }

    //     On Back Press --> End the App :
    override fun onBackPressed() {
        if (a != 1) {
            a = 1
            PersonName?.setText(Name)
            replacefragment(HomeFragment())
        } else {
            super.onBackPressed()
            ActivityCompat.finishAffinity(this@MainActivity)
        }
    }
}





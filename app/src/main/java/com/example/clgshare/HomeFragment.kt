package com.example.clgshare

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import java.util.*

class HomeFragment : Fragment() {

    var homeRecyclerView: RecyclerView? = null
    val myPostDataList = ArrayList<MyPostData>()
//    lateinit var homeRecyclerViewAdapter: HomeRecyclerViewAdapterD


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

//         Firebase Databse Data :

        // Firebase Databse Data :
//        val Data: FirebaseRecyclerOptions<Data> =
//            FirebaseRecyclerOptions.Builder<Data>().setQuery(
//                FirebaseDatabase.getInstance().getReference("POST"),
//                Data::class.java
//            ).build

                // Write a message to the database
//                val database = Firebase.database
//                val myRef = database.getReference("message")
//
////        myRef.setValue("Hello, World!")
//              myRef.addValueEventListener(object : ValueEventListener {
//
//            override fun onDataChange(snapshot: DataSnapshot) {
//                // This method is called once with the initial value and again
//                // whenever data at this location is updated.
//                val value = snapshot.getValue<String>()
//                Log.d("TAG", "Value is: " + value)
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                Log.w("TAG", "Failed to read value.", error.toException())
//            }
//
//        })

//        Log.d("123456", Data.snapshots.toString())
//        homeRecyclerViewAdapter = HomeRecyclerViewAdapterD(Data)
//        homeRecyclerView?.adapter=(homeRecyclerViewAdapter)
        return view
    }

    override fun onPause() {

        super.onPause()
    }

    override fun onResume() {
        super.onResume()
    }

//    override fun onStart() {
//        super.onStart()
//        homeRecyclerViewAdapter.startListening()
//    }
//
//    override fun onStop() {
//        super.onStop()
//        homeRecyclerViewAdapter.stopListening()
//        }

}

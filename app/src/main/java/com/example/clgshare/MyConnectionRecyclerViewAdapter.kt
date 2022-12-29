package com.example.clgshare

import android.app.AlertDialog
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import java.io.File
import java.io.IOException
import java.util.*


class MyConnectionRecyclerViewAdapter(data: ArrayList<MyConnectionData>) : RecyclerView.Adapter<MyConnectionRecyclerViewAdapter.ViewHolder>() {

    var Database: AppDatabase? = null
    var builder: AlertDialog.Builder? = null
    var Data: ArrayList<MyConnectionData>?=data


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.connection_layout, parent, false)
        Database = Room.databaseBuilder(view.context, AppDatabase::class.java as Class<AppDatabase>, "APP DATA").allowMainThreadQueries().build()
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        // Firebase :
        val firebaseAuth = FirebaseAuth.getInstance()
        val firebaseDatabase = FirebaseDatabase.getInstance()
        val users = firebaseDatabase.getReference("USERS")

        // Firebase Storage :
        val storage = FirebaseStorage.getInstance()
        val HouseImages = storage.reference.child("Profile Image").child(Data!![position].uid)

        // Setting Text and Image:
        var bitmap: Bitmap?=null
        users.child(Data!![position].uid).get().addOnSuccessListener {
            holder.name.text = it.getValue().toString() }
        try {
            val file = File.createTempFile("tempfile", ".jpg")
            HouseImages.getFile(file)
                .addOnSuccessListener {
                    bitmap = BitmapFactory.decodeFile(file.absolutePath)
                    holder.pic.setImageBitmap(bitmap)
                }
        } catch (e: IOException) {
            e.printStackTrace()
        }

        holder.itemView.setOnClickListener(View.OnClickListener {
            val fullimage = AlertDialog.Builder(it.rootView.context)
            val view: View =
                LayoutInflater.from(it.rootView.context).inflate(R.layout.fullimage_dialog, null)
            val FullHouseImage: ImageView = view.findViewById(R.id.FullImage)
            fullimage.setView(view)

            FullHouseImage.setImageBitmap(bitmap)

            val alertDialog = fullimage.create()
            alertDialog.setCanceledOnTouchOutside(true)
            alertDialog.show()
            true
        })

    }

    override fun getItemCount(): Int {
        return Data!!.size
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var pic: ImageView
        var name: TextView

        init {
            pic = itemView.findViewById(R.id.UserImage)
            name = itemView.findViewById(R.id.UserName)
        }
    }

}
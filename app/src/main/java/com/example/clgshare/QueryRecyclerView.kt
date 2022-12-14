package com.example.clgshare

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import java.util.*

class QueryRecyclerViewAdapter( MyPostDataList: ArrayList<Data>): RecyclerView.Adapter<QueryRecyclerViewAdapter.ViewHolder>(){

    private  val myPostDataList=MyPostDataList

    var Database:AppDatabase?=null
    lateinit var appDataDao: AppDataDao
    var firebaseAuth: FirebaseAuth? = null
    var firebaseDatabase: FirebaseDatabase = FirebaseDatabase.getInstance()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QueryRecyclerViewAdapter.ViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        val view: View = layoutInflater.inflate(R.layout.query_layout, parent, false)
        Database = Room.databaseBuilder(view.context, AppDatabase::class.java as Class<AppDatabase>, "APP DATA").allowMainThreadQueries().build()
        appDataDao= Database!! .appDataDao()
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val myPostData=myPostDataList[position]

        // Firebase Storage :
        val storage = FirebaseStorage.getInstance()
        val PostImages = storage.reference.child("Profile Image").child(myPostData?.uid?.subSequence(0,28).toString())
        PostImages.downloadUrl.addOnSuccessListener {
            Glide.with(holder.itemView).load(it).centerCrop().placeholder(R.drawable.profile)
                .into(holder.ProfilePic)
        }

        val users = firebaseDatabase.getReference("USERS")
        val uid: String = myPostData.uid?.subSequence(0, 28).toString()
        users.child(uid).get().addOnSuccessListener {
            holder.ProfileName.text = it.getValue().toString()
        }

        Glide.with(holder.itemView).load(myPostData.image).centerCrop()
            .placeholder(R.drawable.postimage).into(holder.PostImage)
        holder.Suject.text = myPostData.subject
        holder.Description.text = (myPostData.description)

        if (appDataDao.is_pexist(myPostData?.uid?.subSequence(0, 28).toString()) == true) {
            holder.BlueSend.setVisibility(View.VISIBLE)
        } else {
            holder.BlueSend.setVisibility(View.INVISIBLE)
        }

//         Full House Image View on Long Press :
        holder.PostImage.setOnClickListener(View.OnClickListener {
            val fullimage = AlertDialog.Builder(it.rootView.context)
            val view: View =
                LayoutInflater.from(it.rootView.context).inflate(R.layout.fullimage_dialog, null)
            val FullHouseImage: ImageView = view.findViewById(R.id.FullImage)
            fullimage.setView(view)

            Glide.with(holder.itemView).load(myPostData.image).centerCrop()
                .placeholder(R.drawable.postimage).into(FullHouseImage)

            val alertDialog = fullimage.create()
            alertDialog.setCanceledOnTouchOutside(true)
            alertDialog.show()
            true
        })
        holder.PostImage.setOnLongClickListener(View.OnLongClickListener { v -> // Dialog Box :
            val fullimage = AlertDialog.Builder(v.rootView.context)
            val view: View =
                LayoutInflater.from(v.rootView.context).inflate(R.layout.fullimage_dialog, null)
            val FullHouseImage: ImageView = view.findViewById(R.id.FullImage)
            fullimage.setView(view)

            Glide.with(holder.itemView).load(myPostData.image).centerCrop()
                .placeholder(R.drawable.postimage).into(FullHouseImage)

            val alertDialog = fullimage.create()
            alertDialog.setCanceledOnTouchOutside(true)
            alertDialog.show()
            true
        })

//         Connection :
        holder.Send.setOnClickListener(View.OnClickListener {
            if (holder.BlueSend.getVisibility() == View.INVISIBLE) {
                holder.BlueSend.setVisibility(View.VISIBLE)
                if (appDataDao.is_pexist(myPostData.uid?.subSequence(0, 28).toString()) == false) {
                    appDataDao.insertConnectionData(
                        MyConnectionData(myPostData.uid?.subSequence(0, 28).toString())
                    )
                }
            } else {
                holder.BlueSend.setVisibility(View.INVISIBLE)
            }
        })


    }

    override fun getItemCount(): Int {
        return myPostDataList.size
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        var PostImage: ImageView
        var Send: ImageView
        var BlueSend: ImageView
        var ProfileName: TextView
        var Suject: TextView
        var Description: TextView
        var ProfilePic: ImageView

        init {
            Send = itemView.findViewById(R.id.Send)
            BlueSend = itemView.findViewById(R.id.BlueSend)
            PostImage = itemView.findViewById(R.id.QPostImage)
            Suject = itemView.findViewById(R.id.RSubject)
            Description = itemView.findViewById(R.id.LDescription)
            ProfilePic = itemView.findViewById(R.id.LProfilePic)
            ProfileName = itemView.findViewById(R.id.LProfileName)
        }
    }

    // On Double Tap :
    abstract class DoubleClickListener : View.OnClickListener {
        var lastClickTime: Long = 0
        override fun onClick(v: View) {
            val clickTime = System.currentTimeMillis()
            if (clickTime - lastClickTime < DOUBLE_CLICK_TIME_DELTA) {
                onDoubleClick(v)
                lastClickTime = 0
            } else {
                onSingleClick(v)
            }
            lastClickTime = clickTime
        }

        abstract fun onSingleClick(v: View?)
        abstract fun onDoubleClick(v: View?)

        companion object {
            private const val DOUBLE_CLICK_TIME_DELTA: Long = 300 //milliseconds
        }
    }

}

package com.example.clgshare

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class RecyclerViewAdapter(val PostDataList:ArrayList<MyPostData>): RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewAdapter.ViewHolder {
        val inflater:LayoutInflater= LayoutInflater.from(parent.context)
        val view:View=inflater.inflate(R.layout.home_layout,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerViewAdapter.ViewHolder, position: Int) {

        holder.Suject.text=PostDataList.get(position).subject
        holder.Description.text=PostDataList.get(position).description

    }

    override fun getItemCount(): Int {
        return PostDataList.size
    }

    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

        var PostImage: ImageView
        var Like: ImageView
        var RedLike: ImageView
        var Send: ImageView
        var BlueSend: ImageView
        var ProfileName: TextView
        var Suject: TextView
        var Description: TextView
        var ProfilePic: ImageView

        init {
            RedLike = itemView.findViewById(R.id.RedLike)
            Like = itemView.findViewById(R.id.Like)
            Send = itemView.findViewById(R.id.Send)
            BlueSend = itemView.findViewById(R.id.BlueSend)
            PostImage = itemView.findViewById(R.id.LPostImage)
            Suject = itemView.findViewById(R.id.RSubject)
            Description = itemView.findViewById(R.id.LDescription)
            ProfilePic = itemView.findViewById(R.id.LProfilePic)
            ProfileName = itemView.findViewById(R.id.LProfileName)
        }
    }

}
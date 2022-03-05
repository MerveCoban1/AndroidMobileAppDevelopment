package com.example.kotlinders10firebasee

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class FeedRecyclerAdapter(private val userEmailArray:ArrayList<String>,private val userCommentArray:ArrayList<String>,private val userImageArray:ArrayList<String>) :
    RecyclerView.Adapter<FeedRecyclerAdapter.PostHolder>() {

    class PostHolder(view: View) : RecyclerView.ViewHolder(view){
        //viewlarımızı tanımladık
        var emailTextRecycler:TextView?=null
        var commentTextRecycler:TextView?=null
        var imageViewRecycler:ImageView?=null

        init {
            emailTextRecycler=view.findViewById(R.id.emailTextRecycler)
            commentTextRecycler=view.findViewById(R.id.commentTextRecycler)
            imageViewRecycler=view.findViewById(R.id.imageViewRecycler)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostHolder {
        val inflater=LayoutInflater.from(parent.context)
        val view=inflater.inflate(R.layout.recycler_view_row,parent,false)
        return PostHolder(view)
    }

    override fun getItemCount(): Int {
        //kaç tane recycler view row'u oluşturulacak onu yazıyoruz
        return userEmailArray.size
    }

    override fun onBindViewHolder(holder: PostHolder, position: Int) {
        //içeriklerini tanımlıyoruz
        holder.emailTextRecycler?.text=userEmailArray[position]
        holder.commentTextRecycler?.text=userCommentArray[position]

        //picasso verdiğimiz load'ı indiriyor
        Picasso.get().load(userImageArray[position]).into(holder.imageViewRecycler)

    }
}
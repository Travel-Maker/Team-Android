package com.teamandroid.travelmaker.review

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.teamandroid.travelmaker.R

class CommentRecyclerAdapter(var items : ArrayList<Comment>) : RecyclerView.Adapter<CommentRecyclerViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentRecyclerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.comment_item, parent, false)
        return CommentRecyclerViewHolder(view,parent.context)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: CommentRecyclerViewHolder, position: Int) {
        holder.nickName.text = items[position].user_nick
        holder.contents.text = items[position].comment_data.comment_content
    }
}
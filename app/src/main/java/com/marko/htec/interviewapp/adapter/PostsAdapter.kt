package com.marko.htec.interviewapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.marko.htec.interviewapp.databinding.RowPostBinding
import com.marko.htec.interviewapp.model.Post

/**
 * @author Created by Marko Mihajlovic on 28.8.2021.
 */
class PostsAdapter : RecyclerView.Adapter<PostsAdapter.RowPost>() {

    private var postList: List<Post> = mutableListOf()

    fun updateList(postList: List<Post>?) {
        this.postList = postList ?: mutableListOf()

        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowPost {
        val rowNewsBinding: RowPostBinding = RowPostBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return RowPost(rowNewsBinding)
    }

    override fun onBindViewHolder(rowPost: RowPost, position: Int) {
        val binding: RowPostBinding = rowPost.binding
        val post: Post = postList[position]

        binding.titleTxt.text = post.title
        binding.bodyTxt.text = post.body
        binding.root.setOnClickListener {  }
    }


    override fun getItemCount(): Int {
        return postList.size
    }

    class RowPost(val binding: RowPostBinding) : RecyclerView.ViewHolder(binding.root)

}
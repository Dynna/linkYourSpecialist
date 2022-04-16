package com.example.linkyourspecialistmobile.helpers

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.linkyourspecialistmobile.data.PostModelResponse
import com.example.linkyourspecialistmobile.databinding.PostItemBinding

class PostsRecyclerViewAdapter : RecyclerView.Adapter<PostsRecyclerViewAdapter.MyViewHolder>() {

    private var postsList: MutableList<PostModelResponse>? = mutableListOf()


    inner class MyViewHolder(binding: PostItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private val postName: TextView = binding.postName
        private val serviceCategory: TextView = binding.serviceCategory
        private val serviceDescription: TextView = binding.serviceDescription

        fun bind(item: PostModelResponse) {
            itemView.apply {
                postName.text = item.name
                serviceCategory.text = item.category
                serviceDescription.text = item.description
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            PostItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return postsList!!.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(postsList!![position])
    }

    fun setData(newList: MutableList<PostModelResponse>?) {
        postsList = newList?.toMutableList()
        notifyDataSetChanged()
    }
}

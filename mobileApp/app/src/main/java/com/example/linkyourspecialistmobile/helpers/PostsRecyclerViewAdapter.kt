package com.example.linkyourspecialistmobile.helpers

import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.linkyourspecialistmobile.data.HomeRepository
import com.example.linkyourspecialistmobile.data.PostModelResponse
import com.example.linkyourspecialistmobile.databinding.PostItemBinding

class PostsRecyclerViewAdapter : RecyclerView.Adapter<PostsRecyclerViewAdapter.MyViewHolder>() {

    private var postsList: MutableList<PostModelResponse>? = mutableListOf()
    val homeRepository = HomeRepository()
    private lateinit var userSharedPreferences: SharedPreferences
    private lateinit var activity: FragmentActivity

    inner class MyViewHolder(binding: PostItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private val postName: TextView = binding.postName
        private val serviceCategory: TextView = binding.serviceCategory
        private val serviceDescription: TextView = binding.serviceDescription
        private val location: TextView = binding.location
        private val deleteButton: ImageButton = binding.deletePost

        fun bind(item: PostModelResponse) {
            itemView.apply {
                postName.text = item.name
                serviceCategory.text = "Category: " + item.category
                serviceDescription.text = item.description
                location.text = "Location: " + item.location

                userSharedPreferences = activity?.getSharedPreferences("UserData", 0)!!
                val accessToken: String = "Bearer " +
                        userSharedPreferences.getString("access_token", "not logged in").toString()

                deleteButton.setOnClickListener {
                    homeRepository.deletePost(accessToken, item.postID)
                }
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

    fun setData(newList: MutableList<PostModelResponse>?, myActivity: FragmentActivity) {
        postsList = newList?.toMutableList()
        activity = myActivity
        notifyDataSetChanged()
    }
}

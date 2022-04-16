package com.example.linkyourspecialistmobile.ui.navigationfragments

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.linkyourspecialistmobile.R
import com.example.linkyourspecialistmobile.databinding.FragmentPostsBinding
import com.example.linkyourspecialistmobile.helpers.PostsRecyclerViewAdapter
import com.example.linkyourspecialistmobile.viewmodel.PostsViewModel

class PostsFragment : Fragment() {
    private lateinit var userSharedPreferences: SharedPreferences
    private lateinit var viewModel: PostsViewModel
    private lateinit var adapter: PostsRecyclerViewAdapter
    private lateinit var binding: FragmentPostsBinding
    private lateinit var addNewPostButton: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = PostsViewModel()
        adapter = PostsRecyclerViewAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPostsBinding.inflate(inflater, container, false)
        val layoutManager = LinearLayoutManager(context)
        binding.postsRecyclerView.layoutManager = layoutManager
        binding.postsRecyclerView.adapter = adapter

        addNewPostButton = binding.addNewPostButton
        addNewPostButton.setOnClickListener{
            val transaction = activity?.supportFragmentManager?.beginTransaction()
            transaction?.replace(R.id.postsFragment, NewPostFragment())
            transaction?.addToBackStack(null)
            transaction?.commit()
        }

        userSharedPreferences = activity?.getSharedPreferences("UserData", 0)!!
        val accessToken: String = "Bearer " +
                userSharedPreferences.getString("access_token", "not logged in").toString()
        val userid: String =
            userSharedPreferences.getString("id", "not found").toString()

        viewModel.getPosts(accessToken, userid)
        viewModel.posts?.observe(
            this, Observer {
                if (it != null) {
                    adapter.setData(it)
                } else {
                    Toast.makeText(context, "can't get posts", Toast.LENGTH_SHORT).show()
                }
            }
        )
        return binding.root
    }
    fun createNewPost(view: View){

    }
}

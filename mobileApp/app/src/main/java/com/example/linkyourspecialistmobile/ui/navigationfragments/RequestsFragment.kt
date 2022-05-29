package com.example.linkyourspecialistmobile.ui.navigationfragments

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.linkyourspecialistmobile.R
import com.example.linkyourspecialistmobile.databinding.FragmentAvailabilityBinding
import com.example.linkyourspecialistmobile.databinding.FragmentRequestsBinding
import com.example.linkyourspecialistmobile.helpers.BookRequestsRecyclerViewAdapter
import com.example.linkyourspecialistmobile.viewmodel.BookRequestViewModel

class RequestsFragment : Fragment() {

    private lateinit var userSharedPreferences: SharedPreferences
    private lateinit var viewModel: BookRequestViewModel
    private lateinit var adapter: BookRequestsRecyclerViewAdapter
    private lateinit var binding: FragmentRequestsBinding
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = BookRequestViewModel()
        adapter = BookRequestsRecyclerViewAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRequestsBinding.inflate(inflater, container, false)
        val layoutManager = LinearLayoutManager(context)
        binding.requestsRecyclerView.layoutManager = layoutManager
        binding.requestsRecyclerView.adapter = adapter

        userSharedPreferences = activity?.getSharedPreferences("UserData", 0)!!
        val accessToken: String = "Bearer " +
                userSharedPreferences.getString("access_token", "not logged in").toString()
        val userid: String =
            userSharedPreferences.getString("id", "not found").toString()

        getBookRequests(accessToken, userid)

        swipeRefreshLayout = binding.swiperefresh
        swipeRefreshLayout.setOnRefreshListener {
            getBookRequests(accessToken, userid)
            swipeRefreshLayout.isRefreshing = false
        }
        return binding.root
    }

    private fun getBookRequests(accessToken: String, userid: String) {
        viewModel.getBookRequests(accessToken, userid)
        viewModel.bookRequests?.observe(
            this, Observer {
                if (it != null) {
                    adapter.setData(it, activity!!)
                } else {
                    Toast.makeText(context, "can't get book requests", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        )
    }
}

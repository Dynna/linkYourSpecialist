package com.example.linkyourspecialistmobile.ui.navigationfragments.availability

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.linkyourspecialistmobile.R
import com.example.linkyourspecialistmobile.databinding.FragmentAvailabilityBinding
import com.example.linkyourspecialistmobile.helpers.AvailabilityRecyclerViewAdapter
import com.example.linkyourspecialistmobile.viewmodel.AvailabilityViewModel

class AvailabilityFragment : Fragment() {

    private lateinit var userSharedPreferences: SharedPreferences
    private lateinit var viewModel: AvailabilityViewModel
    private lateinit var adapter: AvailabilityRecyclerViewAdapter
    private lateinit var binding: FragmentAvailabilityBinding
    private lateinit var addItemButton: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = AvailabilityViewModel()
        adapter = AvailabilityRecyclerViewAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAvailabilityBinding.inflate(inflater, container, false)
        val layoutManager = LinearLayoutManager(context)
        binding.availabilityRecyclerView.layoutManager = layoutManager
        binding.availabilityRecyclerView.adapter = adapter

        userSharedPreferences = activity?.getSharedPreferences("UserData", 0)!!
        val accessToken: String = "Bearer " +
                userSharedPreferences.getString("access_token", "not logged in").toString()
        val userid: String =
            userSharedPreferences.getString("id", "not found").toString()

        viewModel.getAvailabilityItems(accessToken, userid)
        viewModel.availabilityItems?.observe(
            this, Observer {
                if (it != null) {
                    adapter.setData(it)
                } else {
                    Toast.makeText(context, "can't get availability items", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        )

        addItemButton = binding.addNewAvailabilityButton
        addItemButton.setOnClickListener {
            findNavController().navigate(R.id.newAvailabilityItemFragment)
        }

        return binding.root
    }
}

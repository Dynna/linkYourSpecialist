package com.example.linkyourspecialistmobile.ui.navigationfragments.user

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.linkyourspecialistmobile.R
import com.example.linkyourspecialistmobile.databinding.FragmentProfileBinding
import com.example.linkyourspecialistmobile.ui.navigationfragments.posts.NewPostFragment
import com.example.linkyourspecialistmobile.viewmodel.ProfileViewModel

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var userSharedPreferences: SharedPreferences
    private lateinit var updateButton: Button
    private lateinit var viewModel: ProfileViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userSharedPreferences = activity?.getSharedPreferences("UserData", 0)!!
        viewModel = ProfileViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        viewModel.setData(binding, userSharedPreferences)
        updateButton = binding.editButton
        updateButton.setOnClickListener {
            findNavController().navigate(R.id.updateProfileFragment)
        }
        return binding.root
    }
}

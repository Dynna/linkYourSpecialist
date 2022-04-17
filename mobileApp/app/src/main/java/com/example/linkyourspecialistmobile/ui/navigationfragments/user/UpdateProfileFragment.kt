package com.example.linkyourspecialistmobile.ui.navigationfragments.user

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.linkyourspecialistmobile.databinding.FragmentUpdateProfileBinding
import com.example.linkyourspecialistmobile.viewmodel.UpdateProfileViewModel

class UpdateProfileFragment : Fragment() {

    private lateinit var binding: FragmentUpdateProfileBinding
    private lateinit var userSharedPreferences: SharedPreferences
    private lateinit var viewModel: UpdateProfileViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userSharedPreferences = activity?.getSharedPreferences("UserData", 0)!!
        viewModel = UpdateProfileViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUpdateProfileBinding.inflate(inflater, container, false)
        viewModel.setDefaultData(binding, userSharedPreferences)
        return binding.root
    }
}

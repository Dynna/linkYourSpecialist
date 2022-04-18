package com.example.linkyourspecialistmobile.ui.navigationfragments.user

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.example.linkyourspecialistmobile.R
import com.example.linkyourspecialistmobile.data.UpdateProfileRequestModel
import com.example.linkyourspecialistmobile.databinding.FragmentUpdateProfileBinding
import com.example.linkyourspecialistmobile.viewmodel.UpdateProfileViewModel

class UpdateProfileFragment : Fragment() {

    private lateinit var binding: FragmentUpdateProfileBinding
    private lateinit var userSharedPreferences: SharedPreferences
    private lateinit var viewModel: UpdateProfileViewModel
    private lateinit var sharedPreferencesEditor: SharedPreferences.Editor
    private lateinit var updateProfileRequestModel: UpdateProfileRequestModel
    private lateinit var updateButton: Button
    private lateinit var backButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userSharedPreferences = activity?.getSharedPreferences("UserData", 0)!!
        sharedPreferencesEditor = userSharedPreferences.edit()
        viewModel = UpdateProfileViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUpdateProfileBinding.inflate(inflater, container, false)
        viewModel.setDefaultData(binding, userSharedPreferences)

        val userid: String =
            userSharedPreferences.getString("id", "not found").toString()
        val accessToken: String = "Bearer " +
                userSharedPreferences.getString("access_token", "not logged in").toString()


        updateButton = binding.updateProfileButton
        updateButton.setOnClickListener {

            updateProfileRequestModel = UpdateProfileRequestModel(
                binding.username.text.toString(),
                binding.email.text.toString(),
                binding.name.text.toString(),
                binding.surname.text.toString(),
                binding.birthday.text.toString(),
                binding.experience.text.toString(),
                binding.phone.text.toString(),
                binding.location.text.toString()
            )

            viewModel.updateProfile(
                accessToken,
                userid,
                updateProfileRequestModel,
                sharedPreferencesEditor,
                this
            )
        }

        backButton = binding.backButton
        backButton.setOnClickListener {
            findNavController().navigate(R.id.profileFragment)
        }

        return binding.root
    }
}

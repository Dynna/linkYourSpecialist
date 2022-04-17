package com.example.linkyourspecialistmobile.ui.navigationfragments

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.linkyourspecialistmobile.R
import com.example.linkyourspecialistmobile.databinding.FragmentProfileBinding


class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var userSharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userSharedPreferences = activity?.getSharedPreferences("UserData", 0)!!
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        setData()
        return binding.root
    }

    fun setData() {
        var birthday =
            userSharedPreferences.getString("birthday", "not found").toString().split("T")
        binding.username.text = userSharedPreferences.getString("username", "not found").toString()
        binding.email.text = userSharedPreferences.getString("email", "not found").toString()
        binding.birthday.text = birthday[0]
        binding.name.text = userSharedPreferences.getString("name", "not found").toString()
        binding.surname.text = userSharedPreferences.getString("surname", "not found").toString()
        binding.phone.text = userSharedPreferences.getString("phone", "not found").toString()
        binding.experience.text =
            userSharedPreferences.getString("experience", "not found").toString()
        binding.location.text = userSharedPreferences.getString("location", "not found").toString()
    }
}

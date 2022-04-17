package com.example.linkyourspecialistmobile.viewmodel

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import com.example.linkyourspecialistmobile.databinding.FragmentProfileBinding

class ProfileViewModel : ViewModel() {
    fun setData(binding: FragmentProfileBinding, userSharedPreferences: SharedPreferences) {
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

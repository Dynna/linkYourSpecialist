package com.example.linkyourspecialistmobile.viewmodel

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import com.example.linkyourspecialistmobile.databinding.FragmentUpdateProfileBinding

class UpdateProfileViewModel : ViewModel() {

    fun setDefaultData(
        binding: FragmentUpdateProfileBinding,
        userSharedPreferences: SharedPreferences
    ) {
        binding.username.setText(
            userSharedPreferences.getString("username", "not found").toString()
        )
        binding.email.setText(userSharedPreferences.getString("email", "not found").toString())
        binding.name.setText(userSharedPreferences.getString("name", "not found").toString())
        binding.surname.setText(userSharedPreferences.getString("surname", "not found").toString())
        var birthday =
            userSharedPreferences.getString("birthday", "not found").toString().split("T")
        binding.birthday.setText(birthday[0])
        binding.experience.setText(
            userSharedPreferences.getString("experience", "not found").toString()
        )
        binding.location.setText(
            userSharedPreferences.getString("location", "not found").toString()
        )
        binding.phone.setText(userSharedPreferences.getString("phone", "not found").toString())
    }
}

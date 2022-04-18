package com.example.linkyourspecialistmobile.viewmodel

import android.content.SharedPreferences
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.linkyourspecialistmobile.data.HomeRepository
import com.example.linkyourspecialistmobile.data.RegistrationResponseModel
import com.example.linkyourspecialistmobile.data.UpdateProfileRequestModel
import com.example.linkyourspecialistmobile.databinding.FragmentUpdateProfileBinding

class UpdateProfileViewModel : ViewModel() {

    private var homeRepository: HomeRepository? = null
    var updatedProfile: LiveData<RegistrationResponseModel?>? = null

    init {
        homeRepository = HomeRepository()
        updatedProfile = MutableLiveData()
    }

    fun updateProfile(
        authorizationHeader: String?,
        userid: String?,
        updateProfileRequestModel: UpdateProfileRequestModel,
        sharedPreferencesEditor: SharedPreferences.Editor,
        fragment: Fragment
    ) {
        updatedProfile =
            homeRepository?.updateProfile(authorizationHeader, userid, updateProfileRequestModel)

        updatedProfile?.observe(
            fragment, {
                if (it != null) {
                    Toast.makeText(
                        fragment.context,
                        "Profile updated successfully",
                        Toast.LENGTH_SHORT
                    ).show()
                    sharedPreferencesEditor.putString(
                        "username",
                        updatedProfile!!.value?.username.toString()
                    )
                    sharedPreferencesEditor.putString(
                        "email",
                        updatedProfile!!.value?.email.toString()
                    )
                    sharedPreferencesEditor.putString(
                        "name",
                        updatedProfile!!.value?.name.toString()
                    )
                    sharedPreferencesEditor.putString(
                        "surname",
                        updatedProfile!!.value?.surname.toString()
                    )
                    sharedPreferencesEditor.putString(
                        "birthday",
                        updatedProfile!!.value?.birthday.toString()
                    )
                    sharedPreferencesEditor.putString(
                        "experience",
                        updatedProfile!!.value?.experience.toString()
                    )
                    sharedPreferencesEditor.putString(
                        "phone",
                        updatedProfile!!.value?.phone.toString()
                    )
                    sharedPreferencesEditor.putString(
                        "location",
                        updatedProfile!!.value?.location.toString()
                    )
                    sharedPreferencesEditor.apply()

                } else {
                    Toast.makeText(
                        fragment.context,
                        "Error while updating profile",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        )
    }

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
        val birthday =
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

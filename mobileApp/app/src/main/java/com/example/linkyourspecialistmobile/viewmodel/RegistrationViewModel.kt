package com.example.linkyourspecialistmobile.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.linkyourspecialistmobile.data.HomeRepository
import com.example.linkyourspecialistmobile.data.RegistrationResponseModel
import com.example.linkyourspecialistmobile.data.RegistrationUserModel

class RegistrationViewModel : ViewModel() {
    private var homeRepository: HomeRepository? = null
    var registrationLiveData: LiveData<RegistrationResponseModel>? = null

    init {
        homeRepository = HomeRepository()
        registrationLiveData = MutableLiveData()
    }

    fun signup(postModel: RegistrationUserModel) {
        registrationLiveData = homeRepository?.signup(postModel)
    }
}

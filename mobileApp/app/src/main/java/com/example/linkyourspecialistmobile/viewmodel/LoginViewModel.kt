package com.example.linkyourspecialistmobile.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.linkyourspecialistmobile.data.HomeRepository
import com.example.linkyourspecialistmobile.data.LoginRequestModel
import com.example.linkyourspecialistmobile.data.LoginResponseModel
import com.example.linkyourspecialistmobile.data.RegistrationRequestModel

class LoginViewModel : ViewModel() {
    private var homeRepository: HomeRepository? = null
    var loginLiveData: LiveData<LoginResponseModel>? = null

    init {
        homeRepository = HomeRepository()
        loginLiveData = MutableLiveData()
    }

    fun login(loginRequestModel: LoginRequestModel) {
        loginLiveData = homeRepository?.login(loginRequestModel)
    }
}

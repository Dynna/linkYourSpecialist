package com.example.linkyourspecialistmobile.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.linkyourspecialistmobile.data.AvailabilityItemModel
import com.example.linkyourspecialistmobile.data.HomeRepository

class AvailabilityViewModel : ViewModel() {
    private var homeRepository: HomeRepository? = null
    var availabilityItems: LiveData<MutableList<AvailabilityItemModel>?>? = null

    init {
        homeRepository = HomeRepository()
        availabilityItems = MutableLiveData()
    }

    fun getAvailabilityItems(authorizationHeader: String?, userid: String?) {
        availabilityItems = homeRepository?.getAvailabilityItems(authorizationHeader, userid)
    }
}

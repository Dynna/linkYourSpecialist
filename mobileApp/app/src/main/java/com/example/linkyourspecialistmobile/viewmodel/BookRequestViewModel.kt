package com.example.linkyourspecialistmobile.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.linkyourspecialistmobile.data.AvailabilityItemModel
import com.example.linkyourspecialistmobile.data.BookRequestModel
import com.example.linkyourspecialistmobile.data.HomeRepository

class BookRequestViewModel : ViewModel() {
    private var homeRepository: HomeRepository? = null
    var bookRequests: LiveData<MutableList<BookRequestModel>?>? = null

    init {
        homeRepository = HomeRepository()
        bookRequests = MutableLiveData()
    }

    fun getBookRequests(authorizationHeader: String?, userid: String?) {
        bookRequests = homeRepository?.getBookRequests(authorizationHeader, userid)
    }
}

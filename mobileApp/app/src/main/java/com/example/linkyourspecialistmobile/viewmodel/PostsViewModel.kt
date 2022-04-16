package com.example.linkyourspecialistmobile.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.linkyourspecialistmobile.data.HomeRepository
import com.example.linkyourspecialistmobile.data.PostModelResponse

class PostsViewModel : ViewModel() {
    private var homeRepository: HomeRepository? = null
    var posts: LiveData<MutableList<PostModelResponse>?>? = null

    init {
        homeRepository = HomeRepository()
        posts = MutableLiveData()
    }

    fun getPosts(authorizationHeader: String?, userid: String?) {
        posts = homeRepository?.getPosts(authorizationHeader, userid)
    }
}

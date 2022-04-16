package com.example.linkyourspecialistmobile.network

import com.example.linkyourspecialistmobile.data.*
import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {
    @POST("/api/mobileUsers/register")
    fun signup(@Body registrationRequestModel: RegistrationRequestModel): Call<RegistrationResponseModel>

    @POST("/api/mobileUsers/login")
    fun login(@Body loginRequestModel: LoginRequestModel): Call<LoginResponseModel>

    @GET("/api/mobileUsers/posts")
    fun getPosts(
        @Header("authorization") authorizationHeader: String?,
        @Header("userid") userid: String?
    ): Call<MutableList<PostModelResponse>>

    @POST("/api/mobileUsers/newPost")
    fun createPost(
        @Header("authorization") authorizationHeader: String?,
        @Body newPostModel: NewPostModel
    ): Call<NewPostModel>
}

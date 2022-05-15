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

    @POST("/api/mobileUsers/updateUser")
    fun updateProfile(@Header("authorization") authorizationHeader: String?,
                      @Header("userid") userid: String?,
                      @Body updateProfileRequestModel: UpdateProfileRequestModel
    ): Call<RegistrationResponseModel>

    //availability requests
    @POST("/api/mobileUsers/availability/newItem")
    fun createAvailabilityItem(
        @Header("authorization") authorizationHeader: String?,
        @Body availabilityItemModel: AvailabilityItemModel
    ): Call<AvailabilityItemModel>

    @GET("/api/mobileUsers/availability/getItem")
    fun getAvailabilityItems(
        @Header("authorization") authorizationHeader: String?,
        @Header("userid") userid: String?
    ): Call<MutableList<AvailabilityItemModel>>

    @GET("/api/mobileUsers/bookRequests")
    fun getBookRequests(
        @Header("authorization") authorizationHeader: String?,
        @Header("userid") userid: String?
    ): Call<MutableList<BookRequestModel>>
}

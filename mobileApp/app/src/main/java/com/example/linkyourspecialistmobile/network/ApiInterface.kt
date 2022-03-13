package com.example.linkyourspecialistmobile.network

import com.example.linkyourspecialistmobile.data.RegistrationResponseModel
import com.example.linkyourspecialistmobile.data.RegistrationUserModel
import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {
    @POST("/api/mobileUsers/register")
    fun signup(@Body registrationUserModel: RegistrationUserModel): Call<RegistrationResponseModel>
}

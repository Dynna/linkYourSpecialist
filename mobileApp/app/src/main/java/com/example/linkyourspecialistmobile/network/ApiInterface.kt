package com.example.linkyourspecialistmobile.network

import com.example.linkyourspecialistmobile.data.LoginRequestModel
import com.example.linkyourspecialistmobile.data.LoginResponseModel
import com.example.linkyourspecialistmobile.data.RegistrationResponseModel
import com.example.linkyourspecialistmobile.data.RegistrationRequestModel
import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {
    @POST("/api/mobileUsers/register")
    fun signup(@Body registrationRequestModel: RegistrationRequestModel): Call<RegistrationResponseModel>

    @POST("/api/mobileUsers/login")
    fun login(@Body loginRequestModel: LoginRequestModel): Call<LoginResponseModel>
}

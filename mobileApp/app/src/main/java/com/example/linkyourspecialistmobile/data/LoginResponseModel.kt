package com.example.linkyourspecialistmobile.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class LoginResponseModel(
    @SerializedName("user")
    @Expose
    val user: RegistrationResponseModel?,

    @SerializedName("access_token")
    @Expose
    val access_token: String?
)

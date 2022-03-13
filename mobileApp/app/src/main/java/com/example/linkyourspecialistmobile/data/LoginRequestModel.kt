package com.example.linkyourspecialistmobile.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class LoginRequestModel(
    @SerializedName("email")
    @Expose
    var email: String? = "",

    @SerializedName("password")
    @Expose
    var password: String? = ""
)

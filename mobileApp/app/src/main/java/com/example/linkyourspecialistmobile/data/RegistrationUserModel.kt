package com.example.linkyourspecialistmobile.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RegistrationUserModel(
    @SerializedName("username")
    @Expose
    var username: String? = "",

    @SerializedName("email")
    @Expose
    var email: String? = "",

    @SerializedName("name")
    @Expose
    var name: String? = "",

    @SerializedName("surname")
    @Expose
    var surname: String? = "",

    @SerializedName("password")
    @Expose
    var password: String? = ""
)

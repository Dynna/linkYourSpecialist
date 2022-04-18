package com.example.linkyourspecialistmobile.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class UpdateProfileRequestModel(
    @SerializedName("username")
    @Expose
    val username: String?,

    @SerializedName("email")
    @Expose
    val email: String?,

    @SerializedName("name")
    @Expose
    val name: String?,

    @SerializedName("surname")
    @Expose
    val surname: String?,

    @SerializedName("birthday")
    @Expose
    val birthday: String?,

    @SerializedName("experience")
    @Expose
    val experience: String?,

    @SerializedName("phone")
    @Expose
    val phone: String?,

    @SerializedName("location")
    @Expose
    val location: String?
)

package com.example.linkyourspecialistmobile.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class BookRequestModel(
    @SerializedName("specialistID")
    @Expose
    var specialistID: String? = "",

    @SerializedName("clientID")
    @Expose
    var clientID: String? = "",

    @SerializedName("clientEmail")
    @Expose
    var clientEmail: String? = "",

    @SerializedName("availabilityItemID")
    @Expose
    var availabilityItemID: String? = "",

    @SerializedName("date")
    @Expose
    var date: String? = "",

    @SerializedName("startTime")
    @Expose
    var startTime: String? = "",

    @SerializedName("endTime")
    @Expose
    var endTime: String? = ""
)

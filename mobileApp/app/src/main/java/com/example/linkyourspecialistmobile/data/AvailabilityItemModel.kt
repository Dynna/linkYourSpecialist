package com.example.linkyourspecialistmobile.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class AvailabilityItemModel(
    @SerializedName("_id")
    @Expose
    var id: String? = null,

    @SerializedName("userID")
    @Expose
    var userID: String? = "",

    @SerializedName("date")
    @Expose
    var date: String? = "",

    @SerializedName("startTime")
    @Expose
    var startTime: String? = "",

    @SerializedName("endTime")
    @Expose
    var endTime: String? = "",

    @SerializedName("description")
    @Expose
    var description: String? = "",
)

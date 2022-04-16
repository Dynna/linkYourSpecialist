package com.example.linkyourspecialistmobile.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PostModelResponse(
    @SerializedName("_id")
    @Expose
    var postID: String? = "",

    @SerializedName("userID")
    @Expose
    var userID: String? = "",

    @SerializedName("category")
    @Expose
    var category: String? = "",

    @SerializedName("name")
    @Expose
    var name: String? = "",

    @SerializedName("description")
    @Expose
    var description: String? = ""
)

package com.pac.kotlin_mobile

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class News(
    @Expose
    @SerializedName("id") val id: Int,

    @Expose
    @SerializedName("title") val title: String,

    @Expose
    @SerializedName("image") val image: String,

    @Expose
    @SerializedName("detail") val detail: String,

    @Expose
    @SerializedName("user_id") val user_id: String
    ){}


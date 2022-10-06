package com.pac.kotlin_mobile

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Post(
    @Expose
    @SerializedName("id") val id: Int,

    @Expose
    @SerializedName("name") val name: String,

    @Expose
    @SerializedName("gender") val gender: String,

    @Expose
    @SerializedName("color") val color: String,

    @Expose
    @SerializedName("vaccine") val vaccine: String,

    @Expose
    @SerializedName("species") val species: String,

    ) {
}
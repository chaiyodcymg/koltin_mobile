package com.pac.kotlin_mobile

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class data_search(
    val search: ArrayList<Search>
)
data class Search(
    @Expose
    @SerializedName("email") val email: String,

    @Expose
    @SerializedName("firstname") val firstname: String,

    @Expose
    @SerializedName("id") val id: Int ,

    @Expose
    @SerializedName("image_profile") val image_profile: String,

    @Expose
    @SerializedName("lastname") val lastname: String,

    @Expose
    @SerializedName("password") val password: String,

    @Expose
    @SerializedName("role") val role: Int
)
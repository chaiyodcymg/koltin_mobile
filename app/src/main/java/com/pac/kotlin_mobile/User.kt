package com.pac.kotlin_mobile


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class User(
    @Expose
    @SerializedName("status") val status: Boolean,

    @Expose
    @SerializedName("text")val text: String,

    @Expose
    @SerializedName("AUTH")val AUTH: String,


    ){}

data class Register(
    @Expose
    @SerializedName("username") val username: Boolean,

    @Expose
    @SerializedName("text")val text: String,

    @Expose
    @SerializedName("AUTH")val AUTH: String,


    ){}

package com.pac.kotlin_mobile


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Login(
    @Expose
    @SerializedName("status") val status: Boolean,

    @Expose
    @SerializedName("text")val text: String,

    @Expose
    @SerializedName("AUTH")val AUTH: String,

    @Expose
    @SerializedName("role")val role: String,
    )

data class Register(
    @Expose
    @SerializedName("username") val username: Boolean,

    @Expose
    @SerializedName("text")val text: String,

    @Expose
    @SerializedName("AUTH")val AUTH: String,

    @Expose
    @SerializedName("role")val role: String,
    )
data class Update(
    @Expose
    @SerializedName("status") val status: Boolean,

    @Expose
    @SerializedName("text")val text: String, )

data class Profile(
    @Expose
    @SerializedName("email") val email: String,

    @Expose
    @SerializedName("name")val name: String,

    @Expose
    @SerializedName("firstname")val firstname: String,

    @Expose
    @SerializedName("lastname")val lastname: String,

    @Expose
    @SerializedName("image_profile")val image_profile: String,
)

object URL {
    val URL_API: String = "http://10.0.2.2:3000/"
}

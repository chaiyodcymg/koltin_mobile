package com.pac.kotlin_mobile

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Findhouse(

    @Expose
    @SerializedName("name") val name: String,

    @Expose
    @SerializedName("gender") val gender: String,

    @Expose
    @SerializedName("color") val color: String,

    @Expose
    @SerializedName("vaccine") val vaccine: String,

    @Expose
    @SerializedName("date_vaccine") val date_vaccine: String,

    @Expose
    @SerializedName("species") val species: String,

    @Expose
    @SerializedName("more_info") val more_info: String,

    @Expose
    @SerializedName("image") val image: String,

    @Expose
    @SerializedName("house_no") val house_no: String,

    @Expose
    @SerializedName("street") val street: String,

    @Expose
    @SerializedName("sub_district") val sub_district: String,

    @Expose
    @SerializedName("district") val district: String,

    @Expose
    @SerializedName("province") val province: String,

    @Expose
    @SerializedName("post_address") val post_address: String,

    @Expose
    @SerializedName("firstname") val firstname: String,

    @Expose
    @SerializedName("lastname") val lastname: String,

    @Expose
    @SerializedName("phone") val phone: String,

    @Expose
    @SerializedName("email") val email: String,

    @Expose
    @SerializedName("line_id") val line_id: String,

    @Expose
    @SerializedName("facebook") val facebook: String,

    @Expose
    @SerializedName("type") val type: Int,

    @Expose
    @SerializedName("status") val status: Int,

    @Expose
    @SerializedName("user_id") val user_id: Int,

    )

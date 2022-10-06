package com.pac.kotlin_mobile

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Cat(
    val color: String,
    val created_at: String,
    val date: String,
    val date_vaccine: String,
    val district: String,
    val email: String,
    val facebook: String,
    val firstname: String,
    val gender: String,
    val house_no: String,
    val id: Int,
    val image: String,
    val lastname: String,
    val line_id: String,
    val more_info: String,
    val name: String,
    val notice_ponit: String,
    val phone: String,
    val place_to_found: String,
    val post_address: String,
    val post_id: Int,
    val province: String,
    val species: String,
    val sub_district: String,
    val vaccine: String,


)

data class Cat_search(
    val id: Int,
    val color: String,
    val species: String,
    val name: String,
    val image: String,
    )

data class Postlist(
    @Expose
    @SerializedName("id") val id: Int,

    @Expose
    @SerializedName("name") val name: String,

    @Expose
    @SerializedName("color") val color: String,

    @Expose
    @SerializedName("species") val species: String,

    @Expose
    @SerializedName("vaccine") val vaccine: String,


    )





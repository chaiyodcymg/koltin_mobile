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
    val street: String,
    val notice_point: String,
    val type: Int


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
    @SerializedName("date") val date: String?,

    @Expose
    @SerializedName("notice_point") val notice_point: String?,

    @Expose
    @SerializedName("place_to_found") val place_to_found: String?,

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



    )
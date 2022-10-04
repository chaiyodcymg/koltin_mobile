package com.pac.kotlin_mobile

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface LostcatAPI {

    @FormUrlEncoded
    @POST("insertcatlost")
    fun insertpost(
        @Field("name") name: String,
        @Field("gender") gender: String,
        @Field("color") color: String,
        @Field("vaccine") vaccine: String,
        @Field("date_vaccine") date_vaccine: String,
        @Field("species") species: String,
        @Field("more_info") more_info: String,
        @Field("image") image: String,
        @Field("house_no") house_no: String,
        @Field("street") street: String,
        @Field("sub_district") sub_district: String,
        @Field("district") district: String,
        @Field("province") province: String,
        @Field("post_address") post_address: String,
        @Field("date") date: String,
        @Field("notice_point")  notice_point: String,
        @Field("place_to_found")  place_to_found: String,
        @Field("firstname") firstname: String,
        @Field("lastname") lastname: String,
        @Field("phone") phone: String,
        @Field("email") email: String,
        @Field("line_id") line_id: String,
        @Field("facebook") facebook: String,
        @Field("type") type: Int,
        @Field("status") status: Int,
        @Field("user_id") user_id: Int,
    ): Call<Lostcat>


}
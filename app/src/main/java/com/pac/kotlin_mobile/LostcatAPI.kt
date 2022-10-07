package com.pac.kotlin_mobile

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface LostcatAPI {

    @Multipart
    @POST("insertcatlost")
    fun insertpost(
        @Part image: MultipartBody.Part,
        @Part("name") name: RequestBody,
        @Part("gender") gender: RequestBody,
        @Part("color") color: RequestBody,
        @Part("vaccine") vaccine: RequestBody,
        @Part("date_vaccine") date_vaccine: RequestBody,
        @Part("species") species: RequestBody,
        @Part("more_info") more_info: RequestBody,
        @Part("house_no") house_no: RequestBody,
        @Part("street") street: RequestBody,
        @Part("sub_district") sub_district: RequestBody,
        @Part("district") district: RequestBody,
        @Part("province") province: RequestBody,
        @Part("post_address") post_address: RequestBody,
        @Part("date") date: RequestBody,
        @Part("notice_point")  notice_point: RequestBody,
        @Part("place_to_found")  place_to_found: RequestBody,
        @Part("firstname") firstname: RequestBody,
        @Part("lastname") lastname: RequestBody,
        @Part("phone") phone: RequestBody,
        @Part("email") email: RequestBody,
        @Part("line_id") line_id: RequestBody,
        @Part("facebook") facebook: RequestBody,
        @Part("type") type: RequestBody,
        @Part("status") status: RequestBody,
        @Part("user_id") user_id: RequestBody,
    ): Call<UploadResponse>

    @GET("home_lost")
    fun gethomeLost():Call<List<Lostcat>>

}
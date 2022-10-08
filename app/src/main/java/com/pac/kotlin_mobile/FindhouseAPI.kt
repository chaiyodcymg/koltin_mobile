package com.pac.kotlin_mobile

import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface FindhouseAPI {
    @GET("more_findhouse")
    fun retrievePost():Call<List<Findhouse>>

    @Multipart
    @POST("insertfindhouse")
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


}
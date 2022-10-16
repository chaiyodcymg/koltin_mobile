package com.pac.kotlin_mobile

import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call

import retrofit2.http.*
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface Cat_API {

    @GET("mypost/{id}")
    fun getMypost(
        @Path("id") id: Int
    ): Call<List<Cat>>


    @GET("search")
    fun search(
        @Query("data") data: String
    ): Call<List<Search>>


    @GET("allpost")
    fun getAllpost(): Call<List<Cat>>

    @PUT("soft_delete/{id}")
    fun softdelete(
        @Path("id") id:Int):Call<Cat>

    @PUT("deny_post/{id}")
    fun deny_post(
        @Path("id") id:Int):Call<Cat>

    @PUT("accept_post/{id}")
    fun accept_post(
        @Path("id") id:Int):Call<Cat>

    @DELETE("deletePost/{id}")
    fun deletePost(
        @Path("id") id: Int): Call<Cat>

    @FormUrlEncoded /// Update
    @PUT("updatePost")
    fun updatePost(
        @Part("id") id: RequestBody,
        @Part("name") name: RequestBody,
        @Part("gender") gender: RequestBody,
        @Part("color") color: RequestBody,
        @Part("vaccine") vaccine: RequestBody,
        @Part("date_vaccine") date: RequestBody,
        @Part("species") species: RequestBody,
        @Part("more_info") more_info: RequestBody,
        @Part image: MultipartBody.Part,
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
    ): Call<Cat>


}
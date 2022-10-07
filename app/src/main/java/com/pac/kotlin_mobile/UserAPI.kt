package com.pac.kotlin_mobile

import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*


interface UserAPI {


    @FormUrlEncoded
    @POST("login")
    fun Login(
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<Login>


    @FormUrlEncoded
    @POST("register")
    fun Register(
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("firstname") firstname: String,
        @Field("lastname") lastname: String
    ): Call<Register>



    @Multipart
    @POST("updateprofilewithImage")
    fun updateprofile_withImage(
        @Part image: MultipartBody.Part,
        @Part("id") id: RequestBody,
        @Part("email") email: RequestBody,
        @Part("firstname") firstname: RequestBody,
        @Part("lastname") lastname: RequestBody
    ): Call<UploadResponse>

    @FormUrlEncoded
    @POST("updateprofilenoImage")
    fun updateprofile_noImage(
        @Field("id") id: String,
        @Field("email") email: String,
        @Field("firstname") firstname: String,
        @Field("lastname") lastname: String
    ): Call<Update>
    @FormUrlEncoded
    @POST("profile")
    fun Profile(
        @Field("id") id: String,

    ): Call<Profile>

}
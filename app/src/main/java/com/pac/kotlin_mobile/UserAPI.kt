package com.pac.kotlin_mobile

import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*


interface UserAPI {
    @GET("allemp")
    fun retrieveStudent(): Call<List<User>>

    @FormUrlEncoded
    @POST("login")
    fun Login(
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<User>


    @FormUrlEncoded
    @POST("register")
    fun Register(
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("firstname") firstname: String,
        @Field("lastname") lastname: String
    ): Call<Register>



    @Multipart
    @POST("uploadimage")
    fun uploadImage(
        @Part image: MultipartBody.Part,
        @Part("desc") desc: RequestBody
    ): Call<UploadResponse>


}
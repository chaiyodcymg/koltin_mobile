package com.pac.kotlin_mobile

import retrofit2.Call
import retrofit2.http.*

interface Cat_API {
    @GET("mypost")
    fun getMypost(): Call<List<Cat>>

    @GET("search")
    fun search(
        @Query("data") data: String
        ): Call<List<Lostcat>>

    @FormUrlEncoded /// Update
    @PUT("updatePost/{id}")
    fun updatePost(
        @Path("id") id: Int,
        @Field("name") name: String,
       ): Call<Cat>

    @DELETE("deletePost/{id}")
    fun deletePost(
        @Path("id") id: Int): Call<Cat>


}
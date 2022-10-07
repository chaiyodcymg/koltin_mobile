package com.pac.kotlin_mobile

import retrofit2.Call

import retrofit2.http.*
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface Cat_API {
    @GET("search")
    fun search(
        @Query("data") data: String
    ): Call<List<Cat>>

    @GET("mypost/{id}")
    fun getMypost(
        @Path("id") id:Int):Call<List<Cat>>

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

}
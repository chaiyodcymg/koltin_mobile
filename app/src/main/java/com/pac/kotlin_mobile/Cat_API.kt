package com.pac.kotlin_mobile

import retrofit2.Call

import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

import retrofit2.http.*


interface Cat_API {
    @GET("mypost")
    fun getMypost(): Call<List<Cat>>

    @GET("search")
    fun search(
        @Query("data") data: String
    ): Call<List<Search>>


    @PUT("soft_delete/{id}")
    fun softdelete(
        @Path("id") id:Int):Call<Cat>

    @DELETE("deletePost/{id}")
    fun deletePost(
        @Path("id") id: Int): Call<Cat>

}
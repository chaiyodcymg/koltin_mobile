package com.pac.kotlin_mobile

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface Mypost_API {

    fun allmypost(
        @Query("data") data: String
    ): Call<List<Cat>>

    @PUT("delete_post/{id}")
    fun delete(
        @Path("id") id: Int): Call<Post>

}
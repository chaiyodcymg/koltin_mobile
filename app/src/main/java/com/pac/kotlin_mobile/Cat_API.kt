package com.pac.kotlin_mobile

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Cat_API {
    @GET("search")
    fun search(
        @Query("data") data: String

        ): Call<List<Lostcat>>
}
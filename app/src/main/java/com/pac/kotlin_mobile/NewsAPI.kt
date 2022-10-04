package com.pac.kotlin_mobile

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface NewsAPI {
    @FormUrlEncoded
    @POST("insertnews")
    fun insertNews(
        @Field("title") title: String,
        @Field("image") image: String,
        @Field("detail") detail: String,
        @Field("user_id") user_id: String
    ): Call<News>
}
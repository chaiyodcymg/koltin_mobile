package com.pac.kotlin_mobile

import retrofit2.Call
import retrofit2.http.*

interface NewsAPI {
    @GET("news")
    fun retrieveNews():Call<List<News>>

    @GET("home_news")
    fun gethomeNews():Call<News>

    @FormUrlEncoded
    @POST("insertnews")
    fun insertNews(
        @Field("title") title: String,
        @Field("image") image: String,
        @Field("detail") detail: String,
        @Field("user_id") user_id: String
    ): Call<News>

    @FormUrlEncoded
    @PUT("editnews/{id}")
    fun updateNews(
        @Path("id") id:Int,
        @Field("title") title: String,
        @Field("image") image: String,
        @Field("detail") detail: String,
        @Field("user_id") user_id: String
    ): Call<News>

    @PUT("softdelnews/{id}")
    fun softdeletenews(
        @Path("id") id:Int
    ):Call<News>


}

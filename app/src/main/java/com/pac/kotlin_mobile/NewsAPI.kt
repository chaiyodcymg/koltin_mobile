package com.pac.kotlin_mobile

import retrofit2.Call
import retrofit2.http.*

interface NewsAPI {
    @GET("news")
    fun retrieveNews():Call<List<News>>

    @FormUrlEncoded
    @POST("insertnews")
    fun insertNews(
        @Field("title") title: String,
        @Field("image") image: String,
        @Field("detail") detail: String,
        @Field("user_id") user_id: String
    ): Call<News>

    @FormUrlEncoded
    @PUT("editnews")
    fun updateNews(
        @Path("id") id:Int,
        @Field("image") image: String,
        @Field("detail") detail: String,
        @Field("user_id") user_id: String
    ): Call<News>

}

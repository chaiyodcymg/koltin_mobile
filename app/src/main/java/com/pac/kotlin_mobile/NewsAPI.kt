package com.pac.kotlin_mobile

import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface NewsAPI {
    @GET("news")
    fun retrieveNews():Call<List<News>>

    @GET("home_news")
    fun gethomeNews():Call<News>



    @Multipart
    @POST("insertnews")
    fun insertNews(
        @Part("title") title: RequestBody,
        @Part image: MultipartBody.Part,
        @Part("detail") detail: RequestBody,
        @Part("user_id") user_id: RequestBody
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

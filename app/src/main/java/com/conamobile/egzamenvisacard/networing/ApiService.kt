package com.conamobile.egzamenvisacard.networing

import com.conamobile.egzamenvisacard.model.CardModel
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @GET("cards/")
    fun getActivity(): Call<List<CardModel>>

    @POST("cards/")
    fun putActivity(@Body user: CardModel): Call<List<CardModel>>

    @DELETE("cards/{id}")
    fun deleteUser(@Path("id") id: Int): Call<CardModel>

    @PUT("cards/{id}")
    fun updateUser(@Path("id") id: Int, @Body user: CardModel): Call<CardModel>

}



//    @GET("photos/{id}")
//    fun singlePhotos(@Path("id")id:Int):Call<ResponseItem>
//
//    @POST("photos")
//    fun createPhotos(@Body post: ResponseItem):Call<ResponseItem>
//
//    @PUT("photos/{id}")
//    fun updatePhotos(@Path("id")id: Int,@Body post: ResponseItem):Call<ResponseItem>
//
//    @DELETE("photos/{id}")
//    fun deletePhotos(@Path("id")id:Int):Call<ResponseItem>

    //    @GET("search/photos?")
////    @GET("search/photos?page=2&query=tourism")
//    fun searchPhotos(@Query("query") search: String) : Call<Welcome>
////    fun searchPhotos() : Call<Welcome>
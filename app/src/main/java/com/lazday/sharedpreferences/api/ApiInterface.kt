package com.lazday.sharedpreferences.api

import com.lazday.sharedpreferences.response.*
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.POST
import okhttp3.ResponseBody
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET

interface ApiInterface {

//    @FormUrlEncoded
//    @POST("user")
//    fun daftar(
//        @Field("nama") nama: String,
//        @Field("jenkel") jenkel: String,
//        @Field("notelp") notelp: String,
//        @Field("username") username: String,
//        @Field("password") password: String
//    ): Call<ResponseModel>

    @FormUrlEncoded
    @POST("user/login")
    fun login(
        @Field("username") username: String,
        @Field("password") password: String
    ): Call<ResponUser>

//    @GET("dagangan")
//    fun getDagangan(
  //  ): Call<ResponseModel>
}
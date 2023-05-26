package com.atech.socieltags.api

import retrofit2.Call
import retrofit2.http.GET

interface Client {
    @GET(".")
    fun getHTML(): Call<String>
}
package com.atech.urlimageloader.client

import retrofit2.Call
import retrofit2.http.GET

interface Client {

    /**
     * Get HTML from url
     */
    @GET(".")
    fun getHTML(): Call<String>
}
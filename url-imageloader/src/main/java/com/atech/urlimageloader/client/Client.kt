package com.atech.urlimageloader.client

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface Client {

    /**
     * Get HTML from url
     */
    @GET
    fun getHTML(@Url param: String): Call<String>
}
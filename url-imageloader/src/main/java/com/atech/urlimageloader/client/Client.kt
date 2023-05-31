package com.atech.urlimageloader.client

import androidx.annotation.Keep
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Url

@Keep
interface Client {

    /**
     * Get HTML from url
     */
    @GET
    @Headers("Content-Type: text/html")
    suspend fun getHTML(@Url param: String): Response<String>

}
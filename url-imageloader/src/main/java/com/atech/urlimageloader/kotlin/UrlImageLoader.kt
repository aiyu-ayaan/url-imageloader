package com.atech.urlimageloader.kotlin

import com.atech.urlimageloader.client.RetrofitClient
import com.atech.urlimageloader.utils.makeValidUrl
import org.jsoup.Jsoup
import retrofit2.Call

open class UrlImageLoader {
    companion object {
        inline fun getImageUrl(
            url: String,
            crossinline onCompleted: (String?, Throwable?) -> Unit = { _, _ -> }
        ) {
            try {
                RetrofitClient.getInstance(url.makeValidUrl()).getClient()
                    .getHTML().enqueue(object : retrofit2.Callback<String> {
                        override fun onFailure(call: Call<String>, t: Throwable) {
                            onCompleted(null, t)
                        }

                        override fun onResponse(
                            call: Call<String>,
                            response: retrofit2.Response<String>
                        ) {
                            if (response.isSuccessful) {
                                val imageUrl = response.body()?.run {
                                    Jsoup.parse(this)
                                        .select("meta[property=og:image]")
                                        .first()
                                        ?.attr("content")
                                }
                               imageUrl?.let {
                                   onCompleted(it, null)
                               } ?: onCompleted(null, Throwable("Image url not found"))
                            } else
                                onCompleted(null, Throwable("Response is not successful"))
                        }
                    })
            } catch (e: Exception) {
                onCompleted(null, e)
            }
        }
    }

}
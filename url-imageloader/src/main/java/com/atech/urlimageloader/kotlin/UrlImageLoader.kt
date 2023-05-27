package com.atech.urlimageloader.kotlin

import com.atech.urlimageloader.client.RetrofitClient
import com.atech.urlimageloader.model.LinkDetails
import com.atech.urlimageloader.utils.makeValidUrl
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import retrofit2.Call

open class UrlImageLoader {
    companion object {
        /**
         * Get image url from website
         * @param url: url of the website
         * @param onCompleted: callback function
         * @see com.atech.urlimageloader.java.UrlImageLoaderJava for java version
         */
        inline fun getImageFromUrl(
            url: String, crossinline onCompleted: (String?, Throwable?) -> Unit = { _, _ -> }
        ) {
            try {
                RetrofitClient.getInstance(url.makeValidUrl()).getClient().getHTML()
                    .enqueue(object : retrofit2.Callback<String> {
                        override fun onFailure(call: Call<String>, t: Throwable) {
                            onCompleted(null, t)
                        }

                        override fun onResponse(
                            call: Call<String>, response: retrofit2.Response<String>
                        ) {
                            if (response.isSuccessful) {
                                val imageUrl = response.body()?.run {
                                    Jsoup.parse(this).select("meta[property=og:image]").first()
                                        ?.attr("content")
                                }
                                imageUrl?.let {
                                    onCompleted(it, null)
                                } ?: onCompleted(null, Throwable("Image url not found"))
                            } else onCompleted(null, Throwable("Response is not successful"))
                        }
                    })
            } catch (e: Exception) {
                onCompleted(null, e)
            }
        }


        /**
         * Get image url and title from website
         * @param url: url of the website
         * @param onCompleted: callback function
         * @see com.atech.urlimageloader.java.UrlImageLoaderJava for java version
         * @see LinkDetails for model class
         */
        inline fun getLinkDetailsUrl(
            url: String, crossinline onCompleted: (LinkDetails?, Throwable?) -> Unit = { _, _ -> }
        ) {
            try {
                RetrofitClient.getInstance(url.makeValidUrl()).getClient().getHTML()
                    .enqueue(object : retrofit2.Callback<String> {
                        override fun onFailure(call: Call<String>, t: Throwable) {
                            onCompleted(null, t)
                        }

                        override fun onResponse(
                            call: Call<String>, response: retrofit2.Response<String>
                        ) {
                            if (response.isSuccessful) {
                                val imageUrl = response.body()?.run {
                                    Jsoup.parse(this).select("meta[property=og:image]").first()
                                        ?.attr("content")
                                }
                                val title = response.body()?.run {
                                    Jsoup.parse(this).select("meta[property=og:title]").first()
                                        ?.attr("content")
                                }
                                val description = response.body()?.run {
                                    Jsoup.parse(this).select("meta[property=og:description]")
                                        .first()
                                        ?.attr("content")
                                }
                                onCompleted(
                                    LinkDetails(
                                        title = title,
                                        description = description,
                                        imageLink = imageUrl
                                    ), null
                                )
                            } else onCompleted(null, Throwable("Response is not successful"))
                        }
                    })
            } catch (e: Exception) {
                onCompleted(null, e)
            }
        }

        /**
         * Get details from website and return html
         * @param url: url of the website
         * @param onCompleted: callback function
         * @see com.atech.urlimageloader.java.UrlImageLoaderJava for java version
         */
        inline fun getCustomDetailsUrl(
            url: String,
            crossinline onCompleted: (Document?, Throwable?) -> Unit = { _, _ -> }
        ) {
            try {
                RetrofitClient.getInstance(url.makeValidUrl()).getClient().getHTML()
                    .enqueue(object : retrofit2.Callback<String> {
                        override fun onFailure(call: Call<String>, t: Throwable) {
                            onCompleted(null, t)
                        }

                        override fun onResponse(
                            call: Call<String>, response: retrofit2.Response<String>
                        ) {
                            if (response.isSuccessful) {
                                response.body()?.let {
                                    onCompleted(Jsoup.parse(it), null)
                                } ?: onCompleted(null, Throwable("Response is not successful"))
                            } else onCompleted(null, Throwable("Response is not successful"))
                        }
                    })
            } catch (e: Exception) {
                onCompleted(null, e)
            }
        }
    }

}
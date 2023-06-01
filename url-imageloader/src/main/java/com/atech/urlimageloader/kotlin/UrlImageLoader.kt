package com.atech.urlimageloader.kotlin

import androidx.annotation.Keep
import com.atech.urlimageloader.client.Client
import com.atech.urlimageloader.model.LinkDetails
import com.atech.urlimageloader.utils.makeValidUrl
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory

@Keep
open class UrlImageLoader {
    companion object {
        /**
         * Get image url from website
         * @param link: url of the website
         * @param onCompleted: callback function
         * @see com.atech.urlimageloader.java.UrlImageLoaderJava for java version
         */
        suspend inline fun getImageFromUrl(
            link: Pair<String, String>,
            crossinline onCompleted: (String?, Throwable?) -> Unit = { _, _ -> }
        ) {
            try {
                Retrofit.Builder()
                    .baseUrl(link.first.makeValidUrl())
                    .addConverterFactory(
                        ScalarsConverterFactory.create()
                    )
                    .build()
                    .create(Client::class.java)
                    .getHTML(link.second)
                    .let { response ->
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

            } catch (e: Exception) {
                onCompleted(null, e)
            }
        }


        /**
         * Get image url and title from website
         * @param link: url of the website
         * @param onCompleted: callback function
         * @see com.atech.urlimageloader.java.UrlImageLoaderJava for java version
         * @see LinkDetails for model class
         */
        suspend inline fun getLinkDetailsUrl(
            link: Pair<String, String>,
            crossinline onCompleted: (LinkDetails?, Throwable?) -> Unit = { _, _ -> }
        ) {
            try {
                Retrofit.Builder()
                    .baseUrl(link.first.makeValidUrl())
                    .addConverterFactory(
                        ScalarsConverterFactory.create()
                    )
                    .build()
                    .create(Client::class.java)
                    .getHTML(link.second)
                    .let { response ->
                        if (response.isSuccessful) {
                            val imageUrl = response.body()?.run {
                                Jsoup.parse(this).select("meta[property=og:image]").first()
                                    ?.attr("content")
                            }
                            val title = response.body()?.run {
                                Jsoup.parse(this).select("meta[property=og:title]").first()
                                    ?.attr("content") ?: Jsoup.parse(this).title()
                            }
                            val description = response.body()?.run {
                                Jsoup.parse(this).select("meta[property=og:description]")
                                    .first()?.attr("content") ?: Jsoup.parse(this).title()
                            }
                            val icon = response.body()?.run {
                                Jsoup.parse(this).select("link[rel=icon]").firstOrNull()
                                    ?.attr("href")
                                    ?: Jsoup.parse(this).select("link[rel=shortcut icon]")
                                        .firstOrNull()?.attr("href")
                            }

                            onCompleted(
                                LinkDetails(
                                    title = title,
                                    description = description,
                                    iconLink = icon,
                                    imageLink = imageUrl
                                ), null
                            )
                        } else onCompleted(null, Throwable("Response is not successful"))
                    }
            } catch (e: Exception) {
                onCompleted(null, e)
            }
        }

        /**
         * Get details from website and return html
         * @param link: url of the website
         * @param onCompleted: callback function
         * @see com.atech.urlimageloader.java.UrlImageLoaderJava for java version
         */
        suspend inline fun getCustomDetailsUrl(
            link: Pair<String, String>,
            crossinline onCompleted: (Document?, Throwable?) -> Unit = { _, _ -> }
        ) {
            try {
                Retrofit.Builder()
                    .baseUrl(link.first.makeValidUrl())
                    .addConverterFactory(
                        ScalarsConverterFactory.create()
                    )
                    .build()
                    .create(Client::class.java)
                    .getHTML(link.second)
                    .let { response ->
                        if (response.isSuccessful) {
                            response.body()?.let {
                                onCompleted(Jsoup.parse(it), null)
                            } ?: onCompleted(null, Throwable("Response is not successful"))
                        } else onCompleted(null, Throwable("Response is not successful"))
                    }

            } catch (e: Exception) {
                onCompleted(null, e)
            }
        }
    }

}
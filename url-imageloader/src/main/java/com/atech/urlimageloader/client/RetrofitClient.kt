package com.atech.urlimageloader.client

import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory

class RetrofitClient private constructor(
    baseUrl: String
) {


    private var retrofit: Retrofit? = null

    init {
        retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(
                ScalarsConverterFactory.create()
            )
            .build()
    }

    companion object {
        private var instance: RetrofitClient? = null
        /**
         * @param baseUrl: url of the website
         * @return RetrofitClient instance
         */
        fun getInstance(baseUrl: String): RetrofitClient {
            synchronized(this) {
                if (instance == null) {
                    instance = RetrofitClient(baseUrl)
                }
                return instance!!
            }
        }
    }

    fun getClient(): Client {
        return retrofit!!.create(Client::class.java)
    }
}
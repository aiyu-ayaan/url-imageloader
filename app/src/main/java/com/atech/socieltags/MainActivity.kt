package com.atech.socieltags

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.atech.socieltags.api.RetrofitClient
import com.atech.socieltags.databinding.ActivityMainBinding
import org.jsoup.Jsoup
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val TAG = "AAA"

class MainActivity : AppCompatActivity() {

    private val github = "https://www.github.com/aiyu-ayaan/"
    private val youtube = "https://youtu.be/ZmcBC9-wAXM/"
    private val instagram = "https://www.instagram.com/bit.app_"

    private val retrofitClient: RetrofitClient by lazy {
        RetrofitClient.getInstance(github)
    }
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        retrofitClient.getClient().getHTML().enqueue(
            object :
                Callback<String> {
                override fun onResponse(call: Call<String>, response: Response<String>) {
                    if (response.isSuccessful) {
                        val body = response.body()
//                       Log.d(TAG, "onResponse: $body")
                        val doc = Jsoup.parse(body)
                        val element = doc.select("meta[property=og:image]").first()
                        val url = element?.attr("content")
                        Log.d(TAG, "onResponse: $url")
                        binding.imageView.load(url) {
                            placeholder(android.R.drawable.ic_media_pause)
                            crossfade(true)
                        }
                    }
                }

                override fun onFailure(call: Call<String>, t: Throwable) {
                    Log.d(TAG, "onFailure: ${t.message}")
                }

            })
    }
}
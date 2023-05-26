package com.atech.socieltags

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.atech.socieltags.databinding.ActivityMainBinding
import com.atech.urlimageloader.kotlin.UrlImageLoader

private const val TAG = "AAA"

class MainActivity : AppCompatActivity() {

    private val github = "www.github.com/aiyu-ayaan/"
    private val youtube = "https://youtu.be/ZmcBC9-wAXM/"
    private val instagram = "https://www.instagram.com/bit.app_/"

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        UrlImageLoader.getImageUrl(github) { link, t ->
            if (t != null) {
                Log.d(TAG, "onCreate: ${t.message}")
                return@getImageUrl
            }
            binding.imageView.load(link)
        }
    }
}
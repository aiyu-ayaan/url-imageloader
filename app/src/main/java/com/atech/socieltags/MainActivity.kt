package com.atech.socieltags

import android.os.Bundle
import android.util.Log
import android.widget.Toast
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
        binding.buttonGetDetails.setOnClickListener {
            binding.textInputLayoutLink.editText?.apply {
                if (text.isEmpty()) {
                    binding.textInputLayoutLink.error = "Please enter a link"
                    return@apply
                }
                val link = text.toString()
                UrlImageLoader.getLinkDetailsUrl(link) { linkDetails, t ->
                    if (t != null) {
                        Toast.makeText(this@MainActivity, "${t.message}", Toast.LENGTH_SHORT).show()
                    }
                    linkDetails?.let {
                        binding.apply {
                            imageView.load(it.imageLink)
                            textViewTitle.text = it.title
                            textViewDescription.text = it.description
                            Log.d(TAG, "onCreate: ${it.iconLink}")
                        }
                    }
                }
            }
        }
    }
}
# url-imageloader

The Image Loader library is a powerful tool for fetching image URLs and providing image loading capabilities in your Android applications. It simplifies the process of retrieving image URLs from HTML content and handling the image loading operations.

## Features
- Fetch image URLs from HTML content
-  Support for Kotlin and Java versions
- Seamless integration with Retrofit for network requests
- Simple and intuitive API for fetching image URLs
- Error handling for network failures and missing image URLs

Add the library to your project using Gradle:

```gradle
repositories {
        maven{ url 'https://jitpack.io' }
    }
```
```gradle
dependencies {
      implementation 'com.github.aiyu-ayaan:url-imageloader:Tag'
}
```

## Usage
### For kotlin
```kotlin
ImageLoader.getImageUrl(url) { imageUrl, error ->
    if (error != null) {
        // Handle the error
    } else {
        // Use the image URL
    }
}
```

### For Java
```java
ImageLoader.getImageUrl(url, (imageUrl, error) -> {
    if (error != null) {
        // Handle the error
    } else {
        // Use the image URL
    }
});
```

## Code Example
`MainActivity.kt`

```kotlin
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
            binding.imageView.load(link) // Using Coil to load image
        }
    }
}
```

## Contributing 
If you would like to contribute to the library, please fork the repository and create a pull request.

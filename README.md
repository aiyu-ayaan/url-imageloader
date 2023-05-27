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
UrlImageLoader.getImageUrl(url) { imageUrl, error ->
    if (error != null) {
        // Handle the error
    } else {
        // Use the image URL
    }
}
```

### For Java
```java
UrlImageLoaderJava.getImageUrl(url, (imageUrl, error) -> {
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

## License
```
MIT License

Copyright (c) 2023 Ayaan

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```

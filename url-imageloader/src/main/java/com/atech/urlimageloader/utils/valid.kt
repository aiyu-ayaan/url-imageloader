package com.atech.urlimageloader.utils

import android.net.Uri
import com.atech.urlimageloader.model.LinkDetails


/**
 * Check if the url is valid or not
 * @return valid url
 */
fun String.makeValidUrl(): String {
    var s = this
    if (!s.startsWith("http://") && !s.startsWith("https://")) s = "https://$s"
    if (!s.endsWith("/")) s = "$s/"
    return s
}

/**
 * Extract query from url
 * @return base url and query url
 * @see com.atech.urlimageloader.kotlin.UrlImageLoader.getImageFromUrl
 */
fun String.extractQueryFromUrl(): Pair<String, String> {
    val uri = Uri.parse(this)
    val baseUri = uri.buildUpon().clearQuery().build()
    val queryUri = uri.buildUpon().scheme(null).authority(null).path(null).build()
    val base = baseUri.toString()
    val query = queryUri.toString()
    return base to query
}


/**
 * Check which property is null
 * @see LinkDetails
 */
fun LinkDetails.check() {
    if (this.title == null) throw Throwable("Title not found")
    if (this.description == null) throw Throwable("Description not found")
    if (this.iconLink == null) throw Throwable("Icon url not found")
    if (this.imageLink == null) throw Throwable("Image url not found")
}
package com.atech.urlimageloader.utils


/**
 * Check if the url is valid or not
 * @return valid url
 */
fun String.makeValidUrl(): String {
    var s = this
    if (!s.startsWith("http://") && !s.startsWith("https://"))
        s = "https://$s"
    if (!s.endsWith("/"))
        s = "$s/"
    return s
}

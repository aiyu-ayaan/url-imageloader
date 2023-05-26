package com.atech.urlimageloader.utils


/**
 * Check if the url is valid or not
 * @return valid url
 */
fun String.makeValidUrl(): String {
    return if (this.startsWith("http://") || this.startsWith("https://")) {
        this
    } else if (!this.endsWith("/")) {
        "https://$this/"
    } else {
        "https://$this/"
    }
}

package com.atech.urlimageloader.utils


fun String.makeValidUrl(): String {
    return if (this.startsWith("http://") || this.startsWith("https://")) {
        this
    } else if (!this.endsWith("/")) {
        "https://$this/"
    } else {
        "https://$this/"
    }
}

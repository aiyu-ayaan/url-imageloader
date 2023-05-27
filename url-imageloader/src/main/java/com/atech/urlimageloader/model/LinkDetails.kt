package com.atech.urlimageloader.model

import androidx.annotation.Keep

@Keep
data class LinkDetails(
    val title: String?,
    val description: String?,
    val iconLink: String?,
    val imageLink: String?
)
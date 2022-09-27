package com.pac.kotlin_mobile

data class UploadResponse(
    val error: Boolean,
    val message: String,
    val image: String?
)
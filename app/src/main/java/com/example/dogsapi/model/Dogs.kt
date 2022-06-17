package com.example.dogsapi.model

import com.squareup.moshi.Json

data class Dogs(
    @Json(name = "url")
    val url:String
)

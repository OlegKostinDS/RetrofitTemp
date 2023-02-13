package com.example.retrofittemp

import com.google.gson.annotations.SerializedName

data class FilmData(
    val trackName: String,
    val artistName: String,
    val trackTimeMillis: Int,
    val artworkUrl100: String

)
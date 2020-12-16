package com.piusanggoro.cataloquemovie.model

import com.google.gson.annotations.SerializedName

data class Movie(
        @SerializedName("id") val id : String,
        @SerializedName("original_title") val title : String,
        @SerializedName("vote_average") val vote : String,
        @SerializedName("poster_path") val poster : String,
        @SerializedName("overview") val overview : String,
        @SerializedName("release_date") val date_release : String

)

data class MovieResult(@SerializedName("results") val movies : List<Movie>)
package com.piusanggoro.cataloquemovie.service

import com.piusanggoro.cataloquemovie.model.Movie
import com.piusanggoro.cataloquemovie.model.MovieResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {
    @GET("movie/popular")
    fun getPopularMovies(@Query("api_key") apiKey : String) : Call<MovieResult>

    @GET("movie/{movie_id}")
    fun getMovieDetail(@Path("movie_id") id : String, @Query("api_key") apiKey: String) : Call<Movie>
}
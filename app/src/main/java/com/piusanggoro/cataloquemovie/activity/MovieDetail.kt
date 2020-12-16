package com.piusanggoro.cataloquemovie.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.piusanggoro.cataloquemovie.R
import com.piusanggoro.cataloquemovie.model.Movie
import com.piusanggoro.cataloquemovie.service.ApiClient
import kotlinx.android.synthetic.main.movie_detail.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieDetail : AppCompatActivity() {
    lateinit var apiKey : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.movie_detail)

        val intent = getIntent()
        val movieId = intent.extras.get("movie_id")
        apiKey = getString(R.string.api_key)

        val movieService = ApiClient.getClient()
        val call : Call<Movie> = movieService.getMovieDetail(movieId.toString(), apiKey)
        call.enqueue(object : Callback<Movie> {
            override fun onResponse(call: Call<Movie>?, response: Response<Movie>?) {
                val movie = response?.body()
                if (movie != null) {
                    var posterPath = StringBuilder()
                    posterPath.append(getString(R.string.poster_base_path)).append(movie.poster)
                    mvDtTitle.setText(movie.title)
                    mvDtStar.setText(movie.vote)
                    mvDaterelease.setText(movie.date_release)
                    mvDtOverview.setText(movie.overview)
                    Glide.with(applicationContext).load(posterPath.toString()).into(mvDtImage)
                }
            }

            override fun onFailure(call: Call<Movie>?, t: Throwable?) {
            }
        })
    }
}

package com.piusanggoro.cataloquemovie.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.piusanggoro.cataloquemovie.R
import com.piusanggoro.cataloquemovie.adapter.MovieAdapter
import com.piusanggoro.cataloquemovie.model.Movie
import com.piusanggoro.cataloquemovie.model.MovieResult
import com.piusanggoro.cataloquemovie.service.ApiClient
import com.piusanggoro.cataloquemovie.service.MovieService
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by piusanggoro on 06/12/18.
 */

class MainActivity : AppCompatActivity() {
    lateinit var apiKey : String
    var movies : MutableList<Movie> = mutableListOf()
    var adapter = MovieAdapter(movies)
    val movieService : MovieService = ApiClient.getClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        rvMovie.layoutManager = LinearLayoutManager(applicationContext)
        rvMovie.adapter = adapter

        apiKey = getString(R.string.api_key)
        getPopularMovies(apiKey)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.action_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }

    fun getPopularMovies(apiKey: String) {
        val call : Call<MovieResult> = movieService.getPopularMovies(apiKey)
        getMovieData(call)
    }

    fun getMovieData(call : Call<MovieResult>) {
        call.enqueue(object : Callback<MovieResult> {
            override fun onFailure(call: Call<MovieResult>?, t: Throwable?) {
                Toast.makeText(applicationContext, "${t.toString()}", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<MovieResult>?, response: Response<MovieResult>?) {
                if (response?.body() != null) {
                    movies = response.body()!!.movies.toMutableList()
                    adapter = MovieAdapter(movies)
                    rvMovie.adapter = adapter
                }
            }
        })
    }
}

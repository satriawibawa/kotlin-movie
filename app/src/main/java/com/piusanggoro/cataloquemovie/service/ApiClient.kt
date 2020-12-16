package com.piusanggoro.cataloquemovie.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {
    companion object {
        fun getClient() : MovieService {
            val baseUrl = "https://api.themoviedb.org/3/"
            val retrofit = Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            return retrofit.create(MovieService::class.java)
        }
    }
}
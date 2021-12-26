package com.androidxlab.dependencyinjection.data.api

import com.androidxlab.dependencyinjection.data.model.MovieList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    companion object {
      private const val MOVIE_PREFIX = "3/movie"
    }

    @GET("${MOVIE_PREFIX}/popular")
    suspend fun fetchPopularMovies(@Query("page") page: Int): MovieList

    @GET("${MOVIE_PREFIX}/now_playing")
    suspend fun fetchLatestMovies(@Query("page")page: Int = 1): MovieList

}
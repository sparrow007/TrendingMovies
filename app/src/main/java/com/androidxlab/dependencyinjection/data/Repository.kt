package com.androidxlab.dependencyinjection.data

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.androidxlab.dependencyinjection.data.model.MovieList
import com.androidxlab.dependencyinjection.data.model.MovieResult

interface Repository {

    suspend fun fetchPopularMovies(): LiveData<PagingData<MovieResult>>

    suspend fun fetchLatestMovies(): Result

}
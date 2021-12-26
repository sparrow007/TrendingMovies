package com.androidxlab.dependencyinjection.data.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.androidxlab.dependencyinjection.data.Repository
import com.androidxlab.dependencyinjection.data.Result
import com.androidxlab.dependencyinjection.data.api.ApiService
import com.androidxlab.dependencyinjection.data.model.MovieResult
import javax.inject.Inject

class DefaultRepository @Inject constructor(private val service: ApiService) : Repository {

    override suspend fun fetchPopularMovies(): LiveData<PagingData<MovieResult>> {

        return Pager(
            PagingConfig(pageSize = 10, enablePlaceholders = false)
        ){
            MovieDataSource(service)
        }.liveData
    }

    override suspend fun fetchLatestMovies(): Result {
        return try {
            val result = service.fetchLatestMovies()
            Log.e("MY TAG", "RESULT = ${result.result}")
            Result.SUCCESS(result)
        }catch (exception: Exception) {
            Result.ERROR(exception.message)
        }
    }
}
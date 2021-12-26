package com.androidxlab.dependencyinjection.data.network

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.androidxlab.dependencyinjection.data.api.ApiService
import com.androidxlab.dependencyinjection.data.model.MovieResult

/**
 * Movie DataSource is the simple implementation of the pagingSource
 */

class MovieDataSource (private val service: ApiService): PagingSource<Int, MovieResult>() {

    override fun getRefreshKey(state: PagingState<Int, MovieResult>): Int? {
        return  null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieResult> {
        val pageNumber = params.key ?: 1

        return try {
            val response = service.fetchPopularMovies(pageNumber)
            val result = response.result

            val nextKey = if (result.isNullOrEmpty()) {
                null
            }else {
                pageNumber + 1
            }
            LoadResult.Page(data = result.orEmpty(), prevKey = null, nextKey)
        }catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}
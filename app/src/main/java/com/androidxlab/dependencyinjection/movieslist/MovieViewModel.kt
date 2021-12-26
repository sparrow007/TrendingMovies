package com.androidxlab.dependencyinjection.movieslist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.androidxlab.dependencyinjection.data.Repository
import com.androidxlab.dependencyinjection.data.Result
import com.androidxlab.dependencyinjection.data.model.MovieList
import com.androidxlab.dependencyinjection.data.model.MovieResult
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieViewModel @Inject constructor(private val repository: Repository): ViewModel() {

    private val _movieData = MutableLiveData<PagingData<MovieResult>>()
    var movieData: LiveData<PagingData<MovieResult>> = _movieData

    private val _movieLatestData = MutableLiveData<MovieList?>()
    var movieLatestData: LiveData<MovieList?> = _movieLatestData

    private val _errorMsg = MutableLiveData<String>()
    var errorMsg: LiveData<String> = _errorMsg

    fun fetchPopularMovies() {
        viewModelScope.launch {
            movieData = repository.fetchPopularMovies().cachedIn(viewModelScope)
            repository.fetchLatestMovies()
        }
    }

    fun fetchLatestMovies() {
        viewModelScope.launch {
            val result = repository.fetchLatestMovies()
            when (result) {
                is Result.SUCCESS -> _movieLatestData.value = result.data

                is Result.ERROR -> _errorMsg.value = "Something went wrong"
            }
        }
    }
}
package com.androidxlab.dependencyinjection.data

import com.androidxlab.dependencyinjection.data.model.MovieList

sealed class Result (val data: MovieList? = null, val message: String? = null) {
    class SUCCESS ( data: MovieList): Result(data)
    class ERROR (message: String?): Result(message = message)
}

package com.androidxlab.dependencyinjection.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MovieList(
    @SerializedName("results")
    @Expose
    var result: List<MovieResult>? = null
)

data class MovieResult(
    @SerializedName("id")
    var id: String? = null,

    @SerializedName("title")
    var title: String? = null,

    @SerializedName("poster_path")
    var poster_path: String? = null,

    @SerializedName("release_date")
    var release_date: String? = null,

    @SerializedName("overview")
    var overview: String? = null,

    @SerializedName("vote_average")
    var vote_average: String? = null,
)

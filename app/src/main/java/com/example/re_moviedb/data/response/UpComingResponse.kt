package com.example.re_moviedb.data.response


import com.example.re_moviedb.data.model.Movie
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UpComingResponse(
    @Json(name = "page")
    var page: Int? = 0,
    @Json(name = "results")
    var results: List<Movie>? = listOf(),
    @Json(name = "total_pages")
    var totalPages: Int? = 0,
    @Json(name = "total_results")
    var totalResults: Int? = 0
)
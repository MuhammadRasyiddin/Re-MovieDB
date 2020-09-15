package com.example.re_moviedb.datasource.service

import com.example.re_moviedb.data.response.PopularResponse
import com.example.re_moviedb.data.response.UpComingResponse
import retrofit2.http.GET

interface MovieService {
    @GET("movie/upcoming")
    suspend fun getUpComingMovies(): UpComingResponse
    @GET("movie/popular")
    suspend fun getPopularMovies(): PopularResponse
}
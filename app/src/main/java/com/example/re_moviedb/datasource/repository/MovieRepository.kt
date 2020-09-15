package com.example.re_moviedb.datasource.repository

import com.example.re_moviedb.datasource.service.MovieService
import javax.inject.Inject

class MovieRepository @Inject constructor(private val network: MovieService) {
    suspend fun getUpComingMovies() = network.getUpComingMovies()
    suspend fun getPopularMovies() = network.getPopularMovies()
}
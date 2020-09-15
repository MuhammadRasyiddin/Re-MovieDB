package com.example.re_moviedb.ui.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.re_moviedb.datasource.repository.MovieRepository
import com.example.re_moviedb.utils.NetworkState
import kotlinx.coroutines.launch

class HomeViewModel @ViewModelInject constructor(private val movieRepository: MovieRepository) :
    ViewModel() {

    fun getUpComingMovies(): MutableLiveData<NetworkState> {
        val networkState = MutableLiveData(NetworkState.loading(true))
        viewModelScope.launch {
            try {
                val result = movieRepository.getUpComingMovies().results
                networkState.value = NetworkState.success(result)
            } catch (e: Exception) {
                networkState.value = NetworkState.error(e)
            }
        }
        return networkState
    }
    fun getPopularMovies() : MutableLiveData<NetworkState>{
        val networkState = MutableLiveData(NetworkState.loading(true))
        viewModelScope.launch {
            try {
                val result = movieRepository.getPopularMovies().results
                networkState.value = NetworkState.success(result)
            }catch (e: java.lang.Exception){
                networkState.value = NetworkState.error(e)
            }
        }
        return networkState
    }
}
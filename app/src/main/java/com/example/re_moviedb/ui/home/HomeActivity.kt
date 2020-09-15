package com.example.re_moviedb.ui.home

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.example.re_moviedb.R
import com.example.re_moviedb.data.model.Movie
import com.example.re_moviedb.databinding.ActivityHomeBinding
import com.example.re_moviedb.utils.NetworkState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_home.*
import kotlin.math.abs

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var homeDatabinding: ActivityHomeBinding
    private lateinit var upComingMovieAdapter: UpComingMovieAdapter
    private lateinit var popularMovieAdapter: PopularMovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        homeDatabinding = DataBindingUtil.setContentView(this, R.layout.activity_home)

        homeDatabinding.apply {
            lifecycleOwner = this@HomeActivity
            viewModel = this@HomeActivity.homeViewModel
        }
        getUpComing()
        getPopular()
    }

    private fun getPopular(){
        rv_popularMovie.layoutManager = LinearLayoutManager(this)
        homeViewModel.getPopularMovies().observe(this, Observer {
            when (it) {
                is NetworkState.Success -> {
                    pb_popular.visibility = View.GONE
                    rv_popularMovie.visibility = View.VISIBLE
                    popularMovieAdapter = PopularMovieAdapter()
                    @Suppress("UNCHECKED_CAST")
                    popularMovieAdapter.setData(it.data as List<Movie>)
                    rv_popularMovie.apply {
                        adapter = popularMovieAdapter
                    }
                }
                is NetworkState.Loading -> {
                    pb_popular.visibility = View.VISIBLE
                    rv_popularMovie.visibility = View.GONE
                }
                is NetworkState.Error -> {
                    pb_popular.visibility = View.GONE
                    rv_popularMovie.visibility = View.GONE
                    Toast.makeText(this, "Network Call Failed!", Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun getUpComing() {
        vp_upcoming.overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        vp_upcoming.clipToPadding = false
        vp_upcoming.clipChildren = false
        vp_upcoming.offscreenPageLimit = 3

        val compositePageTransformer = CompositePageTransformer()
        compositePageTransformer.addTransformer(MarginPageTransformer(36))
        compositePageTransformer.addTransformer { view: View, fl: Float ->
            val r = 1 - abs(fl)
            view.scaleY = 0.95f + r * 0.05f
        }
        homeViewModel.getUpComingMovies().observe(this, Observer {
            when (it) {
                is NetworkState.Success -> {
                    pb_upcoming.visibility = View.GONE
                    vp_upcoming.visibility = View.VISIBLE
                    upComingMovieAdapter = UpComingMovieAdapter()
                    @Suppress("UNCHECKED_CAST")
                    upComingMovieAdapter.setData(it.data as List<Movie>)
                    vp_upcoming.apply {
                        adapter = upComingMovieAdapter
                    }
                }
                is NetworkState.Loading -> {
                    pb_upcoming.visibility = View.VISIBLE
                    vp_upcoming.visibility = View.GONE
                }
                is NetworkState.Error -> {
                    pb_upcoming.visibility = View.GONE
                    vp_upcoming.visibility = View.GONE
                    Toast.makeText(this, "Network Call Failed!", Toast.LENGTH_LONG).show()
                }
            }
        })
    }
}
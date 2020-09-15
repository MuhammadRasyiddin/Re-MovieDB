package com.example.re_moviedb.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.example.re_moviedb.BuildConfig
import com.example.re_moviedb.R
import com.example.re_moviedb.data.model.Movie
import kotlinx.android.synthetic.main.card_up_coming.view.*

class UpComingMovieAdapter : RecyclerView.Adapter<UpComingMovieAdapter.ViewHolder>() {
    private lateinit var dataList: List<Movie>

    fun setData(data: List<Movie>) {
        dataList = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.card_up_coming, parent, false)
        )
    }

    override fun getItemCount() = dataList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(data: Movie) {
            itemView.iv_movie.load("${BuildConfig.API_IMAGE}${data.backdropPath}") {
                allowHardware(false)
                crossfade(true)
            }

            itemView.tv_movie_title.text = data.title
            itemView.tv_movie_year.text = data.releaseDate?.slice(0..3)
        }
    }
}
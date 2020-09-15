package com.example.re_moviedb.ui.home

import android.icu.number.Scale
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import coil.transform.CircleCropTransformation
import com.example.re_moviedb.BuildConfig
import com.example.re_moviedb.R
import com.example.re_moviedb.data.model.Movie
import kotlinx.android.synthetic.main.card_popular.view.*

class PopularMovieAdapter : RecyclerView.Adapter<PopularMovieAdapter.ViewHolder>() {
    private lateinit var dataList: List<Movie>

    fun setData(data: List<Movie>) {
        dataList = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.card_popular, parent, false)
        )
    }

    override fun onBindViewHolder(holder: PopularMovieAdapter.ViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    override fun getItemCount(): Int = dataList.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(data: Movie) {
            itemView.iv_poster.load("${BuildConfig.API_IMAGE}${data.backdropPath}") {
                allowHardware(false)
                crossfade(true)
                scale(coil.size.Scale.FILL)
            }
            itemView.tv_title.text = data.title
        }
    }
}
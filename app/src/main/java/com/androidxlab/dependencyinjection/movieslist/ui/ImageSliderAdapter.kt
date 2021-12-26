package com.androidxlab.dependencyinjection.movieslist.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.androidxlab.dependencyinjection.data.model.MovieResult
import com.androidxlab.dependencyinjection.databinding.ViewpagerImageListBinding
import com.bumptech.glide.Glide

class ImageSliderAdapter(private val trendingList: List<MovieResult>?) : RecyclerView.Adapter<ImageSliderAdapter.ViewHolder>() {

    private val IMAGE_PATH =  "https://image.tmdb.org/t/p/original"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val holder = ViewpagerImageListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(holder)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = trendingList?.get(position)
        Glide.with(holder.itemView.context).load(IMAGE_PATH + data?.poster_path).into(holder.itemBinding.imageViewMain)
    }

    override fun getItemCount(): Int {
        return trendingList?.size ?: 0
    }

    inner class ViewHolder(val itemBinding: ViewpagerImageListBinding) : RecyclerView.ViewHolder(itemBinding.root)
}
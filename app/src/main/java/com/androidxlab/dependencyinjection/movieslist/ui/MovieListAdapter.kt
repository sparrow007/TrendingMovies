package com.androidxlab.dependencyinjection.movieslist.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.androidxlab.dependencyinjection.data.model.MovieResult
import com.androidxlab.dependencyinjection.databinding.ListItemBinding
import com.bumptech.glide.Glide

class MovieListAdapter : PagingDataAdapter<MovieResult, MovieListAdapter.ViewHolder>(DiffUtilCallback) {

    private val IMAGE_PATH= "https://image.tmdb.org/t/p/original"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = getItem(position)
        data?.let {
            Glide.with(holder.itemView.context).load(IMAGE_PATH + it.poster_path).into(holder.itemBinding.imvMoviePoster)
        }
    }

    class ViewHolder(val itemBinding: ListItemBinding): RecyclerView.ViewHolder(itemBinding.root)

    object DiffUtilCallback : DiffUtil.ItemCallback<MovieResult>() {
        override fun areItemsTheSame(oldItem: MovieResult, newItem: MovieResult): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MovieResult, newItem: MovieResult): Boolean {
            return oldItem == newItem
        }
    }
}
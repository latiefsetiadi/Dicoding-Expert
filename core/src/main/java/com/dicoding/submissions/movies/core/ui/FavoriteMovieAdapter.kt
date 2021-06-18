package com.dicoding.submissions.movies.core.ui

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.submissions.movies.core.R
import com.dicoding.submissions.movies.core.databinding.ItemsMovieBinding
import com.dicoding.submissions.movies.core.domain.model.Movie

class FavoriteMovieAdapter: PagedListAdapter<Movie, FavoriteMovieAdapter.MovieViewHolder>(
    DIFF_CALBACK
) {

    companion object{
        private val DIFF_CALBACK = object : DiffUtil.ItemCallback<Movie>(){
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.movieId == newItem.movieId
            }

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem == newItem
            }

        }
    }

    inner class MovieViewHolder(private val binding: ItemsMovieBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie){
            with(binding){
                titleMovie.text = movie.title
                genreMovie.text = movie.date

                Glide.with(itemView.context)
                    .load("https://www.themoviedb.org/t/p/w600_and_h900_bestv2${movie.image}")
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_baseline_refresh).error(R.drawable.ic_baseline_broken_image_24))
                    .into(imagePosterMovie)

            }
            itemView.setOnClickListener {
                val intent = Intent(itemView.context, Class.forName("com.dicoding.submissions.movies.ui.detail_movie.DetailActivity"))
                intent.putExtra(itemView.context.resources.getString(R.string.EXTRA_ID), movie.movieId)
                itemView.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemsFavoriteMovieBinding = ItemsMovieBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MovieViewHolder(itemsFavoriteMovieBinding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = getItem(position)
        if (movie != null){
            holder.bind(movie)
        }
    }

}
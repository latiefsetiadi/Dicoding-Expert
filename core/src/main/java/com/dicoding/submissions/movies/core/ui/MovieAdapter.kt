package com.dicoding.submissions.movies.core.ui

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.submissions.movies.core.R
import com.dicoding.submissions.movies.core.databinding.ItemsMovieBinding
import com.dicoding.submissions.movies.core.domain.model.Movie

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {


    private var listMovies = ArrayList<Movie>()

    fun setMovies(movies: List<Movie>?){
        if (movies == null) return this.listMovies.clear()
        this.listMovies.clear()
        this.listMovies.addAll(movies)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemsMovieBinding = ItemsMovieBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return MovieViewHolder(itemsMovieBinding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = listMovies[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int {
        return listMovies.size
    }

    class MovieViewHolder(private val binding: ItemsMovieBinding) : RecyclerView.ViewHolder(binding.root) {
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
}
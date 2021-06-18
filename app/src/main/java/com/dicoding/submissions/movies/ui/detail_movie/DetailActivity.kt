package com.dicoding.submissions.movies.ui.detail_movie

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.submissions.movies.R
import com.dicoding.submissions.movies.core.data.vo.Resource
import com.dicoding.submissions.movies.databinding.ActivityDetailBinding
import com.dicoding.submissions.movies.core.domain.model.Movie
import org.koin.android.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {

    companion object{
        const val EXTRA_ID = "extra_id"
    }
    private lateinit var binding: ActivityDetailBinding
    private var state:Boolean = false
    private val viewModel: DetailMovieViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        stateLoading(true)

        supportActionBar?.title = resources.getString(R.string.bar_movie)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val extra = intent.extras
        if (extra != null){
            val movieId = extra.getString(EXTRA_ID)
            if (movieId != null){
                viewModel.setSelectedIdMovie(movieId)

                viewModel.movieDetail.observe(this,{movieDetail->
                    if (movieDetail != null){
                        when(movieDetail){
                            is Resource.Loading -> stateLoading(true)
                            is Resource.Success -> {
                                stateLoading(false)
                                movieDetail.data?.let { showDetail(it) }
                                if (movieDetail.data?.favorite == true) {
                                    state = false
                                    setFavoriteImageState(true)
                                }else{
                                    state = true
                                    setFavoriteImageState(false)
                                }
                            }
                            is Resource.Error -> {
                                stateLoading(false)
                                Toast.makeText(this,"Terjadi Kesalahan",Toast.LENGTH_LONG).show()
                            }
                        }
                    }
                })
            }
            binding.addFavorite.setOnClickListener {
                viewModel.setFavoriteMovie()
                if (state) Toast.makeText(this,"Ditambahkan ke Favorit",Toast.LENGTH_SHORT).show()
                else Toast.makeText(this,"Dihapus dari Favorit",Toast.LENGTH_SHORT).show()
            }
        }
    }

   private fun showDetail(movie: Movie){
       with(binding){
            titleText.text = String.format("%s (%s)", movie.title,movie.date)
            val title = titleText.text
            descText.text = movie.description
            genreText.text = movie.genre

            Glide.with(this@DetailActivity)
                .load("https://www.themoviedb.org/t/p/w600_and_h900_bestv2${movie.image}")
                .apply(RequestOptions.placeholderOf(R.drawable.ic_baseline_refresh).error(R.drawable.ic_baseline_broken_image_24))
                .into(imgPosterDetail)

            shareButton.setOnClickListener {
                val sendIntent: Intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TITLE, title)
                    putExtra(Intent.EXTRA_TEXT,descText.text)
                    type = "text/plain"
                }
                startActivity(sendIntent)
            }

        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home ->finish()
        }
        return super.onOptionsItemSelected(item)
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    private fun stateLoading(state:Boolean){
        if (state){
            binding.progressBar.visibility = View.VISIBLE
        }else{
            binding.progressBar.visibility = View.GONE
        }
    }
    private fun setFavoriteImageState(state: Boolean){
        if (state) binding.addFavorite.setImageResource(R.drawable.ic_favorite_available)
        else binding.addFavorite.setImageResource(R.drawable.ic_favorite_empty)
    }
}
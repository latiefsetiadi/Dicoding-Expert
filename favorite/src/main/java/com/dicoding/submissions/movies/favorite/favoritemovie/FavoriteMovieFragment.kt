package com.dicoding.submissions.movies.favorite.favoritemovie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.submissions.movies.core.ui.FavoriteMovieAdapter
import com.dicoding.submissions.movies.favorite.databinding.FragmentFavoriteMovieBinding
import com.dicoding.submissions.movies.favorite.di.favoriteModule
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules


class FavoriteMovieFragment : Fragment() {
    private var _binding: FragmentFavoriteMovieBinding? = null
    private val binding get() = _binding!!
    private val viewModel: FavoriteMovieViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentFavoriteMovieBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadKoinModules(favoriteModule)

        stateLoading(true)
        val movieAdapter = FavoriteMovieAdapter()

        viewModel.getFavoriteMovies.observe(this,{ movies ->
            if (movies!= null){
                stateLoading(false)
                stateEmpty(false)
                movieAdapter.submitList(movies)
                movieAdapter.notifyDataSetChanged()
                if (movies.isNullOrEmpty()) stateEmpty(true)
            }
        })
        with(binding){
            rvItemsMovie.layoutManager = LinearLayoutManager(context)
            rvItemsMovie.setHasFixedSize(true)
            rvItemsMovie.adapter = movieAdapter
        }

    }

    private fun stateLoading(state:Boolean){
        if (state){
            binding.progressBar.visibility = View.VISIBLE
        }else{
            binding.progressBar.visibility = View.GONE
        }
    }

    private fun stateEmpty(state:Boolean){
        if (state){
            binding.emptyLayout.layoutEmpty.visibility = View.VISIBLE
            binding.rvItemsMovie.visibility = View.INVISIBLE
        }else{
            binding.emptyLayout.layoutEmpty.visibility = View.INVISIBLE
            binding.rvItemsMovie.visibility = View.VISIBLE
        }
    }

}
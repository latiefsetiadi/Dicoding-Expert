package com.dicoding.submissions.movies.ui.film

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.submissions.movies.core.data.vo.Resource
import com.dicoding.submissions.movies.core.ui.MovieAdapter
import com.dicoding.submissions.movies.databinding.FragmentMovieBinding
import org.koin.android.viewmodel.ext.android.viewModel


class MovieFragment : Fragment() {

    private var _binding: FragmentMovieBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MovieViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        _binding = FragmentMovieBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


            val movieAdapter = MovieAdapter()
            viewModel.getMovies.observe(this,{ movies ->
                if (movies != null){
                    when(movies){
                        is Resource.Loading -> stateLoading(true)
                        is Resource.Success-> {
                            stateLoading(false)
                            movieAdapter.setMovies(movies.data)
                            movieAdapter.notifyDataSetChanged()
                        }
                        is Resource.Error -> {
                            stateLoading(false)
                            Toast.makeText(context,"Terjadi Kesalahan", Toast.LENGTH_LONG).show()
                        }
                    }
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
}
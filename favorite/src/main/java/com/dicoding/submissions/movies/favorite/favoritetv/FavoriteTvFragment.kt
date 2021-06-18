package com.dicoding.submissions.movies.favorite.favoritetv

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.submissions.movies.core.ui.FavoriteTvAdapter
import com.dicoding.submissions.movies.favorite.databinding.FragmentFavoriteTvBinding
import com.dicoding.submissions.movies.favorite.di.favoriteModule
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules


class FavoriteTvFragment : Fragment() {

    private var _binding: FragmentFavoriteTvBinding? = null
    private val binding get() = _binding!!
    private val viewModel: FavoriteTvViewModel by viewModel()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteTvBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadKoinModules(favoriteModule)

        stateLoading(true)
        val tvAdapter = FavoriteTvAdapter()

        viewModel.getFavoriteTv.observe(this,{tv->
            if (tv != null){
                stateLoading(false)
                stateEmpty(false)
                tvAdapter.submitList(tv)
                tvAdapter.notifyDataSetChanged()
               if (tv.isNullOrEmpty())stateEmpty(true)
                
            }
        })
        with(binding){
            rvItemsTv.layoutManager = LinearLayoutManager(context)
            rvItemsTv.setHasFixedSize(true)
            rvItemsTv.adapter = tvAdapter
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
            binding.rvItemsTv.visibility = View.INVISIBLE
        }else{
            binding.emptyLayout.layoutEmpty.visibility = View.INVISIBLE
            binding.rvItemsTv.visibility = View.VISIBLE
        }
    }

}
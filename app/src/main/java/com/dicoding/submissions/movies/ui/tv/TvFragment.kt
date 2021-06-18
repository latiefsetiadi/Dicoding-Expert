package com.dicoding.submissions.movies.ui.tv

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.submissions.movies.core.data.vo.Resource
import com.dicoding.submissions.movies.core.ui.TvAdapter
import com.dicoding.submissions.movies.databinding.FragmentTvBinding
import org.koin.android.viewmodel.ext.android.viewModel


class TvFragment : Fragment() {

    private var _binding: FragmentTvBinding? = null
    private val binding get() = _binding!!
    private val viewModel : TvViewModel by viewModel()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTvBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        stateLoading(true)

        val tvAdapter = TvAdapter()
        viewModel.getTv.observe(this,{ tv ->
            if (tv != null){
                when(tv){
                    is Resource.Loading -> stateLoading(true)
                    is Resource.Success -> {
                        stateLoading(false)
                        tvAdapter.setTv(tv.data)
                        tvAdapter.notifyDataSetChanged()
                    }
                    is Resource.Error -> {
                        stateLoading(false)
                        Toast.makeText(context, "Terjadi kesalahan", Toast.LENGTH_LONG).show()
                    }
                }
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


}
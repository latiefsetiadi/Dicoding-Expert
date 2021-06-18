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
import com.dicoding.submissions.movies.core.databinding.ItemsTvBinding
import com.dicoding.submissions.movies.core.domain.model.Tv

class FavoriteTvAdapter : PagedListAdapter<Tv, FavoriteTvAdapter.TvViewHolder>(DIFF_CALBACK) {

    companion object{
        private val DIFF_CALBACK = object : DiffUtil.ItemCallback<Tv>(){
            override fun areItemsTheSame(oldItem: Tv, newItem: Tv): Boolean {
                return oldItem.tvId == newItem.tvId
            }

            override fun areContentsTheSame(oldItem: Tv, newItem: Tv): Boolean {
                return oldItem == newItem
            }

        }
    }

    inner class TvViewHolder(private val binding: ItemsTvBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(tv : Tv){
            with(binding){
                titleTv.text = tv.title
                genreTv.text = tv.date

                Glide.with(itemView.context)
                    .load("https://www.themoviedb.org/t/p/w600_and_h900_bestv2${tv.image}")
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_baseline_refresh).error(R.drawable.ic_baseline_broken_image_24))
                    .into(imagePosterTv)
            }

            itemView.setOnClickListener {
                val intent = Intent(itemView.context, Class.forName("com.dicoding.submissions.movies.ui.detail_tv.DetailTvActivity"))
                intent.putExtra(itemView.context.resources.getString(R.string.EXTRA_ID), tv.tvId)
                itemView.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvViewHolder {
        val itemsFavoriteTvBinding = ItemsTvBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return TvViewHolder(itemsFavoriteTvBinding)
    }

    override fun onBindViewHolder(holder: TvViewHolder, position: Int) {
        val tv = getItem(position)
        if (tv != null){
            holder.bind(tv)
        }
    }
}
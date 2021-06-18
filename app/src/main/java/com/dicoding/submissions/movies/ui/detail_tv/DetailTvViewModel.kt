package com.dicoding.submissions.movies.ui.detail_tv

import androidx.lifecycle.*
import com.dicoding.submissions.movies.core.data.vo.Resource
import com.dicoding.submissions.movies.core.domain.model.Tv
import com.dicoding.submissions.movies.core.domain.usecase.MovieUseCase

class DetailTvViewModel(private val movieUseCase: MovieUseCase): ViewModel() {

    private val tvId = MutableLiveData<String>()

    fun setSelectedIdTv(tvId: String){
        this.tvId.value = tvId
    }

    var tvDetail : LiveData<Resource<Tv>> = Transformations.switchMap(tvId){ tvId ->
        movieUseCase.getDetailTv(tvId).asLiveData()
    }

    fun setFavoriteTv(){
        val detailTv = tvDetail.value
        if (detailTv != null){
            val tv = detailTv.data

            if (tv != null){
                val newState = !tv.favorite
                movieUseCase.setFavoriteTv(tv,newState)
            }
        }
    }
}
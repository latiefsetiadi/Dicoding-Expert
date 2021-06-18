package com.dicoding.submissions.movies.favorite

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.dicoding.submissions.movies.favorite.favoritemovie.FavoriteMovieFragment
import com.dicoding.submissions.movies.favorite.favoritetv.FavoriteTvFragment

class FavoriteSectionPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when(position){
            0 -> fragment = FavoriteMovieFragment()
            1 -> fragment = FavoriteTvFragment()
        }
        return fragment as Fragment
    }

}
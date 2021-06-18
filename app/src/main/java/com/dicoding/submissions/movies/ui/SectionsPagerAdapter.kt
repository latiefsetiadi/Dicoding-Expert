package com.dicoding.submissions.movies.ui

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.dicoding.submissions.movies.ui.film.MovieFragment
import com.dicoding.submissions.movies.ui.tv.TvFragment

class SectionsPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when(position){
            0 -> fragment = MovieFragment()
            1 -> fragment = TvFragment()
        }
        return fragment as Fragment
    }
}
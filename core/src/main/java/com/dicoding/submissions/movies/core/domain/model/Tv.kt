package com.dicoding.submissions.movies.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Tv (
    val tvId: String,
    val title: String,
    val description: String,
    val genre: String,
    val date: String,
    val image: String,
    val favorite: Boolean = false
        ) : Parcelable
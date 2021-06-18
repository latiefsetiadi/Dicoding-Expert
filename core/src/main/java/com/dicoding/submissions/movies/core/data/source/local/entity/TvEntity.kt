package com.dicoding.submissions.movies.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tventities")
data class TvEntity(
        @PrimaryKey
        @NonNull
        @ColumnInfo(name = "tvId")
        var tvId: String,

        @ColumnInfo(name = "title")
        var title: String,

        @ColumnInfo(name = "description")
        var description: String,

        @ColumnInfo(name = "genre")
        var genre: String,

        @ColumnInfo(name = "date")
        var date: String,

        @ColumnInfo(name = "image")
        var image: String,

        @ColumnInfo(name = "favorite")
        var favorite: Boolean = false
)
package com.dicoding.submissions.movies.core.data.source.remote.retrofit.datarespons

import com.google.gson.annotations.SerializedName

data class DetailResponse(

	@field:SerializedName("title")
	val title: String,

	@field:SerializedName("genres")
	val genres: List<GenresItem>,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("overview")
	val overview: String,

	@field:SerializedName("original_title")
	val originalTitle: String,

	@field:SerializedName("original_name")
	val originalName: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("poster_path")
	val posterPath: String,


	@field:SerializedName("release_date")
	val releaseDate: String,

	@field:SerializedName("first_air_date")
	val firstAirDate: String,

)



data class GenresItem(

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("id")
	val id: Int
)

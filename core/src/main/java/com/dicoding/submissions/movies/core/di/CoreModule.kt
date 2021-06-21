package com.dicoding.submissions.movies.core.di

import androidx.room.Room
import com.dicoding.submissions.movies.core.data.MovieRepository
import com.dicoding.submissions.movies.core.data.source.local.LocalDataSource
import com.dicoding.submissions.movies.core.data.source.local.room.MovieDatabase
import com.dicoding.submissions.movies.core.data.source.remote.RemoteDataSource
import com.dicoding.submissions.movies.core.data.source.remote.retrofit.ApiServices
import com.dicoding.submissions.movies.core.domain.repository.IMovieRepository
import com.dicoding.submissions.movies.core.utils.AppExecutors
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<MovieDatabase>().movieDao() }
    single {
        val passPhrase: ByteArray = SQLiteDatabase.getBytes("latiefdicoding".toCharArray())
        val factory = SupportFactory(passPhrase)
        Room.databaseBuilder(
            androidContext(),
            MovieDatabase::class.java,
            "Movies.db"
        ).fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }
}

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }
    single {
        val buildRetrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        buildRetrofit.create(ApiServices::class.java)
    }


}
val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<IMovieRepository> {
        MovieRepository(
            get(),
            get(),
            get()
        )
    }
}
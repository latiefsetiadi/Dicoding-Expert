package com.dicoding.submissions.movies

import android.app.Application
import com.dicoding.submissions.movies.core.di.databaseModule
import com.dicoding.submissions.movies.core.di.networkModule
import com.dicoding.submissions.movies.core.di.repositoryModule
import com.dicoding.submissions.movies.di.useCaseModule
import com.dicoding.submissions.movies.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}
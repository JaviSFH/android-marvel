package com.javnez.marvel.data.di

import com.javnez.marvel.core.MarvelNetworkInterceptor
import com.javnez.marvel.data.repository.MarvelOperations
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.OkHttpClient.Builder
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit.SECONDS
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class RepositoryModule {

    @Provides
    fun providesMarvelOperations(retrofit: Retrofit): MarvelOperations {
        return retrofit.create(MarvelOperations::class.java)

    }

    @Provides
    @Singleton
    fun providesRetrofitClient(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl(MarvelOperations.BASE_URL)
            .build()
    }

    @Provides
    @Singleton
    fun providesOkHttpClient(interceptor: Interceptor): OkHttpClient {
        return Builder()
            .addInterceptor(interceptor)
            .callTimeout(15, SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun providesOkHttpInterceptor(marvelNetworkInterceptor: MarvelNetworkInterceptor): Interceptor {
        return marvelNetworkInterceptor
    }
}
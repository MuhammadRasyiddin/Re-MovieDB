package com.example.re_moviedb.di

import com.example.re_moviedb.BuildConfig
import com.example.re_moviedb.datasource.service.MovieService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule {
    private fun provideHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder()
            .addInterceptor {
                val req =
                    it.request().newBuilder()
                        .addHeader("Content-Type", "application/json").build()
                return@addInterceptor it.proceed(req)
            }
            .addInterceptor {
                val req =
                    it.request().newBuilder().url(
                        it.request().url.newBuilder()
                            .addQueryParameter("api_key", BuildConfig.API_TOKEN)
                            .addQueryParameter("language", "en-US")
                            .addQueryParameter("region", "jp" ).build()
                    ).build()
                return@addInterceptor it.proceed(req)
            }
            .build()
    }

    private val retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.API_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .client(provideHttpClient())
        .build()


    @Provides
    @Singleton
    fun provideMovieService(): MovieService {
        return retrofit.create(MovieService::class.java)
    }
}
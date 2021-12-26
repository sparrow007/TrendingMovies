package com.androidxlab.dependencyinjection.di.modules

import com.androidxlab.dependencyinjection.data.api.ApiService
import com.androidxlab.dependencyinjection.utils.AuthInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.converter.gson.GsonConverterFactory

import retrofit2.Retrofit
import javax.inject.Singleton


@Module
object AppModule {

    //This URL cloud placed in build types so depends on the build it could change
    private const val BASE_URL = "https://api.themoviedb.org/"

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient) : Retrofit{
       return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideOkHttp(authInterceptor: AuthInterceptor): OkHttpClient {
       return OkHttpClient.Builder()
           .addInterceptor(authInterceptor)
           .build()
    }

    @Provides
    @Singleton
    fun provideAPIService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
}
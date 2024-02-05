package com.personalsprojects.androidcompose.di

import android.util.Log
import com.personalsprojects.androidcompose.data.network.NetworkDataSource
import com.personalsprojects.androidcompose.data.network.NetworkDataSourceImpl
import com.personalsprojects.androidcompose.data.network.api.MarvelApi
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun providesOkHttpClient(): OkHttpClient {
        Log.d("API", "Entra en el provides httpclient");

        val httpClient = OkHttpClient.Builder().addInterceptor { chain ->
            val originalRequest = chain.request()

            val urlWithParams = originalRequest.url.newBuilder()
                .addQueryParameter("ts", TS)
                .addQueryParameter("apikey", PUBLIC_KEY)
                .addQueryParameter("hash", HASH)
                .build()


            val request = originalRequest.newBuilder()
                .url(urlWithParams)
                .build()

            Log.d("HEROES", "URL ${request.url}");

            chain.proceed(request)
        }.build()

        return httpClient
    }

    @Provides
    fun providesMoshi(): Moshi {
        return Moshi.Builder()
            .addLast(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    fun providesRetrofit(okHttpClient: OkHttpClient, moshi: Moshi): Retrofit {
        return Retrofit.Builder().client(okHttpClient).baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi)).build()
    }

    @Provides
    fun providesMarvelApi(retrofit: Retrofit): MarvelApi {
        return retrofit.create(MarvelApi::class.java)
    }


    @Provides
    fun provideNetworkDataSource(networkDataSourceImpl: NetworkDataSourceImpl): NetworkDataSource {
        return networkDataSourceImpl
    }
}
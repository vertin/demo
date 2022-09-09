package com.check.demo.network

import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitFactory {

    fun <T> getApi(client: OkHttpClient, moshi: Moshi, apiService: Class<T>): T = Retrofit.Builder()
        .baseUrl("https://app.check24.de/")
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .addCallAdapterFactory(NetworkCallAdapterFactory())
        .client(client)
        .build()
        .create(apiService)
}
